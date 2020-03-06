package vn.tripath.backend_demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum  ResponseCode {
    SUCCESS,
    EMAIL_NOT_EXIST("Email is not existed."),
    EMAIL_EXISTED("Email is registered."),
    CURRENT_PASSWORD_INCORRECT("Current password is incorrect."),
    PASSWORD_FORMAT_INVALID("Password format is invalid"),
    AUTHENTICATION_FAILED("User not authenticated."),
    SNS_TYPE_AUTH_MISMATCH("User didn't logged in by this SNS account"),
    SYSTEM_ERROR("System error."),
    DATA_INTEGRITY_VILATION,
    NOT_ENOUGH_POINTS("User does not have enough points"),
    MUST_FOLLOW_ATLEAST_ONE_TAG("Must follow at least one tagable"),
    VERIFY_CODE_MISMATCH,
    EVENT_REGISTERED,
    USER_WITHDRAWN,
    USER_SUSPENDED,
    OVER_LIMITATION,
    VALUE_VALIDATION,
    EVENT_NOT_EXIST,
    TITLE_DUPLICATED,
    VIDEO_URL_INVALID,
    DEPARTMENT_CONTAINING_USERS,
    BUNDLE_ID_INVALID,
    PACKAGE_NAME_INVALID,
    APP_VERSION_FORMAT_INVALID,
    ACCESS_DENIED;

    String message;

    public String getMessage() {
        return message;
    }
}
