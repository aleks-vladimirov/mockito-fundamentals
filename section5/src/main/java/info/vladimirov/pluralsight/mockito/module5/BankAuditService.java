package info.vladimirov.pluralsight.mockito.module5;

import info.vladimirov.pluralsight.mockito.module5.model.AuditType;

public interface BankAuditService {

    void audit(AuditType type, String auditMessage);
}
