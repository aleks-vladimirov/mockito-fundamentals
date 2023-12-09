package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.AuditType;

public interface BankAuditService {

    void audit(AuditType type, String auditMessage);
}
