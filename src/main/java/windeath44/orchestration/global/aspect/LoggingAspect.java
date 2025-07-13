package windeath44.orchestration.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* windeath44.orchestration.Application..*.*(..))")
    public void applicationLoggingPointCut() {};

    @Around("applicationLoggingPointCut()")
    public Object applicationLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Entering {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.debug("Exiting {}", joinPoint.getSignature().getName());
        return result;
    }
}
