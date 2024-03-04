package info.vladimirov.pluralsight.mockito.module5;

import info.vladimirov.pluralsight.mockito.module5.model.AuditType;

public abstract class BankAuditServiceImpl implements BankAuditService{

    enum SomeEnum {
        a, b;
    }

    private final String auditUser;
    public BankAuditServiceImpl(String user, int number, SomeEnum someEnum) {
        this.auditUser = user;
    }

    private BankAuditServiceImpl() {
        auditUser ="";
    }

    @Override
    public void audit(AuditType type, String auditMessage) {

    }
}
