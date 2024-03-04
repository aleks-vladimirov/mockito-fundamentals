package info.vladimirov.pluralsight.mockito.module5;

import info.vladimirov.pluralsight.mockito.module5.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module5.model.AuditType;
import info.vladimirov.pluralsight.mockito.module5.model.UserAccount;

import javax.inject.Inject;
import java.math.BigDecimal;

public class BankAccountService1Impl implements BankAccountService1 {



    private DbConnector dbConnector;


    private ConversionRateService conversionRateService;

    private BankAuditService auditService;

    private BankAccountService1Impl(DbConnector dbConnector, ConversionRateService conversionRateService, BankAuditService auditService) {
        this.dbConnector = dbConnector;
        this.conversionRateService = conversionRateService;
        this.auditService = auditService;
    }



    @Override
    public String getBankAccountName(String userName, String password) {
        auditService.audit(AuditType.INFO, "Get user bank account");
        return dbConnector.getBankAccount(userName).getName();
    }

    @Override
    public boolean depositMoney(String userName, BigDecimal money) {
        UserAccount userAccount = dbConnector.getBankAccount(userName);
        BigDecimal deposit = userAccount.getAccountBalance().get(AccountCurrency.USD);
        auditService.audit(AuditType.DEPOSIT, "Begin deposit " +
                money + " to account " + userAccount );
        deposit.add(money);
        dbConnector.storeUpdate(userAccount);
        auditService.audit(AuditType.DEPOSIT, "Successfully deposited " + money +
                " to account " + userAccount +  ". New account balance is" + deposit);
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
