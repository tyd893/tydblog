package com.my.tydblog.enums;

/**
 * Author:     zhanglingfei
 * Date:     2019/2/13 20:31
 * Description: ${DESCRIPTION}
 */
public enum  RestResponseEnum {
    SUCCESS(1,"success"),
    ERROR(2,"error");

    private final int value;
    /**/
    private final String reasonPhrase;

    RestResponseEnum(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }


    public int getStatus() {
        return value;
    }

    public String getDesc() {
        return reasonPhrase;
    }
}
