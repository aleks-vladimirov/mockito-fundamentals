package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.*;
import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.AuditType;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class BankAccountServiceImpl implements BankAccountService {

    private DbConnector dbConnector;

    private ConversionRateService conversionRateService;

    private BankAuditService auditService;

    public BankAccountServiceImpl(DbConnector dbConnector,
                                  ConversionRateService conversionRateService,
                                  BankAuditService auditService) {

        this.dbConnector = dbConnector;
        this.conversionRateService = conversionRateService;
        this.auditService = auditService;
    }

    @Override
    public UserAccount getBankAccount(String userName, String password) {
        auditService.audit(AuditType.INFO, "Get user bank account");
        return dbConnector.getBankAccount(userName);
    }

    @Override
    public boolean depositMoney(String userName, Double money, AccountCurrency currency) {
        UserAccount userAccount = dbConnector.getBankAccount(userName);
        Double deposit = userAccount.getAccountBalance(currency);
        auditService.audit(AuditType.DEPOSIT, "Begin deposit " + money +
                " to account " + userAccount + " in currency " + currency);

        deposit += money;

        userAccount.setDeposit(currency, deposit);
        dbConnector.storeUpdate(userAccount);
        auditService.audit(AuditType.DEPOSIT, "Successfully deposited " + money +
                " to account " + userAccount + " in currency " + currency +
                ". New account balance is" + deposit);
        return true;
    }

    @Override
    public Double calculateAllBalance(String userName) {
        UserAccount userAccount = dbConnector.getBankAccount(userName);

        return userAccount.getAccountBalance().entrySet().stream().map(
                        balance ->
                                conversionRateService.getConvertedRate(balance.getKey(), balance.getValue()))
                .reduce(Double::sum).get();
    }

    @Override
    public <T extends BankCustomer> boolean  transferMoney(String userName,
                          Double transferredCash, T recipient,
                          MoneyLaundryCheckerService<T> moneyLaundryChecker) {

        UserAccount originalAccount = dbConnector.getBankAccount(userName);
        String transactionId = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy-hh.mm.ss.nnn"));
        boolean accountCheck = moneyLaundryChecker.handleValidateAccount(transactionId,
                userAccount -> {
                         if(!userAccount.getClass().isInstance(recipient)) {
                             auditService.audit(AuditType.DEPOSIT,
                                     "You can't transfer money between different account types");
                             return false;
                         } else {
                             return true;
                         }
                });

        final AccountCurrency currency = new AccountCurrency("USD");
        Double availableBalance = originalAccount.getAccountBalance(currency);

        if((availableBalance - transferredCash < 0.0 ) || accountCheck) {
            return false;
        }
        originalAccount.setDeposit(currency, availableBalance - transferredCash);
        recipient.setDeposit(currency,
                recipient.getAccountBalance(currency) + transferredCash);
        return true;
    }

    public List<Future> batchTransfer(Map<UserAccount, Double> transfers) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future> futureList = new ArrayList<>();

        for (Map.Entry<UserAccount, Double> transfer : transfers.entrySet()) {
            futureList.add(executorService.submit(new TransferMoney(
                    transfer.getKey(), transfer.getValue(), "USD")));
        }
        return futureList;
    }

    public class TransferMoney implements Callable<Boolean> {
        private final UserAccount userAccount;
        private final Double moneyToTransfer;
        private final String currency;

        TransferMoney(UserAccount userAccount, Double deposit, String currency) {
            this.userAccount = userAccount;
            this.moneyToTransfer = deposit;
            this.currency = currency;
        }

        @Override
        public Boolean call() throws Exception {
            //calculate the account balance
            Thread.sleep(100);
            long beginTime = System.currentTimeMillis();
            System.out.println("Thread started with name" + Thread.currentThread().getName());
            Double remainingBalance = userAccount.getAccountBalance
                    (new AccountCurrency(currency)) - moneyToTransfer;

            if (remainingBalance < 0) {
                Thread.sleep(200);
                userAccount.setRestricted(true);
                return false;
            } else {
                userAccount.setDeposit(new AccountCurrency(currency), remainingBalance);
            }
            System.out.println("Thread ended with name " + Thread.currentThread().getName() +
                    " duration of thread execution was " + (System.currentTimeMillis() - beginTime));
            return true;
        }
    }
}
