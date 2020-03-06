package vn.tripath.backend_demo.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CommonErrorResponse {
    private String message;
    private Map<String,String> detailError;
    private String code;

    public static ResponseErrorException errorException(ResponseCode responseCode) {
        return new ResponseErrorException(CommonErrorResponse.builder().code(responseCode.name()).message(responseCode.getMessage()).build());
    }

    public static ResponseErrorException errorException(ResponseCode responseCode, String message) {
        return new ResponseErrorException(CommonErrorResponse.builder().code(responseCode.name()).message(message).build());
    }

    public static void pointNotEnoughResponse() throws ResponseErrorException {
        throw new ResponseErrorException(CommonErrorResponse.builder().code(ResponseCode.NOT_ENOUGH_POINTS.name()).message(ResponseCode.NOT_ENOUGH_POINTS.getMessage()).build());
    }

    public static void otherError() throws ResponseErrorException {
        throw new ResponseErrorException(CommonErrorResponse.builder().code(ResponseCode.SYSTEM_ERROR.name()).build());
    }

    public static void error(ResponseCode responseCode) throws ResponseErrorException {
        throw new ResponseErrorException(CommonErrorResponse.builder().code(responseCode.name()).message(responseCode.getMessage()).build());
    }

    public static void error(ResponseCode responseCode, String message) throws ResponseErrorException {
        throw new ResponseErrorException(CommonErrorResponse.builder().code(responseCode.name()).message(message).build());
    }

    public static void notExist(Object target) throws ResponseErrorException {
        throw new ResponseErrorException(CommonErrorResponse.builder().code(ResponseCode.VALUE_VALIDATION.name()).message(target + " is not existed").build());
    }

    public static void checkLimit(long maxLimit, long current) throws ResponseErrorException {
        if (current >= maxLimit)
            throw new ResponseErrorException(CommonErrorResponse.builder().code(ResponseCode.OVER_LIMITATION.name()).message("Max is " + maxLimit + "--------" + "Current is " + current).build());

    }

    public static void checkLimit(long minLimit, long maxLimit, long current) throws ResponseErrorException {
        if (current >= maxLimit)
            throw new ResponseErrorException(CommonErrorResponse.builder().code(ResponseCode.OVER_LIMITATION.name()).message("Max is " + maxLimit + "--------" + "Min is " + minLimit + "--------" + "Current is " + current).build());
    }
}
