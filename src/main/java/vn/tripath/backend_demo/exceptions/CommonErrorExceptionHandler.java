package vn.tripath.backend_demo.exceptions;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
@Log
public class CommonErrorExceptionHandler {
    @ExceptionHandler({javax.validation.ConstraintViolationException.class, TransactionSystemException.class})
    public CommonErrorResponse handleValidationError(Exception e) {
        Set<ConstraintViolation<?>> violationSet = null;
        if (e instanceof javax.validation.ConstraintViolationException) {
            violationSet = ((javax.validation.ConstraintViolationException) e).getConstraintViolations();
        } else if (e instanceof TransactionSystemException) {
            try {
                violationSet = ((javax.validation.ConstraintViolationException) e.getCause().getCause()).getConstraintViolations();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        Map<String, String> detailError = new HashMap<>();
        if (violationSet != null)
            for (ConstraintViolation violation : violationSet) {
                detailError.put(String.valueOf(violation.getPropertyPath()), violation.getMessage());
            }

        return CommonErrorResponse.builder().code(ResponseCode.VALUE_VALIDATION.name()).detailError(detailError).build();
    }

    @ExceptionHandler({ResponseErrorException.class})
    public CommonErrorResponse handleErrorResponse(ResponseErrorException e) {
        return e.getResponse();
    }

    @ExceptionHandler({AuthenticationException.class})
    public CommonErrorResponse handleErrorResponse(Exception e) {
        return CommonErrorResponse.builder().code(ResponseCode.ACCESS_DENIED.name()).message(e.getMessage()).build();
    }

}
