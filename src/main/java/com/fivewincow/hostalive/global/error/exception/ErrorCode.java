package com.fivewincow.hostalive.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


    ALREADY_CREATED_HOST_NAME(400, "이미 생성된 호스트 이름 입니다."),
    ALREADY_CREATED_HOST_IP(400, "이미 생성된 호스트 IP 입니다."),
    HOST_MAX_EXISTS(400, "호스트는 최대 100개까지만 등록 가능합니다."),
    HOST_NOT_EXISTS(400, "호스트 정보가 존재하지 않습니다.")
    ;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;
}
