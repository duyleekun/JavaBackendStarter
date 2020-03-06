package vn.tripath.backend_demo.aspects;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@Log
public class ExecutionTimeAspect {
    @Around("within(vn.tripath.backend_demo.controllers..*)")
    public Object logResolver(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        if (result instanceof CompletableFuture) {
            CompletableFuture castedResult = (CompletableFuture) result;
            castedResult.whenCompleteAsync((r, e) -> {
                printDuration(start, point);
            });
        } else {
            printDuration(start, point);
        }
        return result;
    }

    private void printDuration(long start, ProceedingJoinPoint point) {
        long duration = System.currentTimeMillis() - start;
        if (duration > 10) {
            log.info(String.format("%s#%s took %s ms",
                    point.getSignature().getDeclaringTypeName(),
                    ((MethodSignature) point.getSignature()).getMethod().getName(),
                    duration,
                    Thread.currentThread().getId()));
        }
    }
}
