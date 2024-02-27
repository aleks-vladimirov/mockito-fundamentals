package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.BankAuditService;
import info.vladimirov.pluralsight.mockito.module3.model.AuditType;

public class BankAuditServiceImpl implements BankAuditService {


    @Override
    public void audit(AuditType type, String auditMessage) {

    }
}
