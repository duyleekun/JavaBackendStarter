package vn.tripath.backend_demo.exceptions;

import lombok.Data;

@Data
public class ResponseErrorException extends Exception {

    private CommonErrorResponse response;

    public ResponseErrorException(CommonErrorResponse response) {
        super(response.getMessage());
        this.response = response;
    }
}
