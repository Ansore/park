package com.park.exception;

/**
 * 处理运行期异常
 *
 * Created by ansore on 16-9-12.
 */
public class ParkException extends RuntimeException {

    private int statusCode = 100;
    private String statusInfo = "服务器错误";

    public ParkException(StatusEnum statusEnum) {
        this.statusCode = statusEnum.getStatusCode();
        this.statusInfo = statusEnum.getStatusInfo();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
