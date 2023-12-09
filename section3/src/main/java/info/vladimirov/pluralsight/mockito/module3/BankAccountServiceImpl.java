package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.AuditType;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;

import java.math.BigDecimal;

public class BankAccountServiceImpl implements BankAccountService {

    private DbConnector dbConnector;

    private ConversionRateService conversionRateService;

    private BankAuditService auditService;

    @Override
    public String getBankAccountName(String userName, String password) {
        auditService.audit(AuditType.INFO, "Get user bank account");
        return dbConnector.getBankAccount(userName).getName();
    }

    @Override
    public boolean depositMoney(String userName, BigDecimal money, AccountCurrency currency) {
        UserAccount userAccount = dbConnector.getBankAccount(userName);
        BigDecimal deposit = userAccount.getAccountBalance().get(currency);
        auditService.audit(AuditType.DEPOSIT, "Begin deposit " + money + " to account " + userAccount + " in currency " + currency);
        deposit.add(money);
        dbConnector.storeUpdate(userAccount);
        auditService.audit(AuditType.DEPOSIT, "Successfully deposited " + money + " to account " + userAccount + " in currency " + currency + ". New account balance is" + deposit);
        return true;
    }

    @Override
    public BigDecimal calculateAllBalance(String userName) {
        UserAccount userAccount = dbConnector.getBankAccount(userName);

        return userAccount.getAccountBalance().entrySet().stream().map(balance -> conversionRateService.getConvertedRate(balance.getKey(), balance.getValue())).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean transferMoney(String userName, String password, BigDecimal money, UserAccount recipient) {
        return false;
    }
}
