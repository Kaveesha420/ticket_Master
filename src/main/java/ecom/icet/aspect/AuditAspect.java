package ecom.icet.aspect;

import ecom.icet.Model.Entity.AuditLog;
import ecom.icet.Repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @AfterThrowing(pointcut = "@annotation(AuditFailure)", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Throwable ex) {
        Object[] args = joinPoint.getArgs();
        Long userId = (args.length > 1 && args[1] instanceof Long) ? (Long) args[1] : null;

        AuditLog log = new AuditLog();
        log.setAction("BOOKING_FAILURE");
        log.setUserId(userId);
        log.setDetails(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}