package com.jikguradar.global.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    private static final String SUCCESS_CODE = "SUCCESS";
    private static final String SUCCESS_MESSAGE = "요청이 성공적으로 처리되었습니다.";

    private final boolean success;
    private final String code;
    private final String message;
    private final T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static BaseResponse<Void> success() {
        return new BaseResponse<>(true, SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, SUCCESS_CODE, message, data);
    }

    public static BaseResponse<Void> error(String code, String message) {
        return new BaseResponse<>(false, code, message, null);
    }
}

