package com.quanwc.enumeration;

/**
 * 猕猴桃规格大小的枚举
 * @author quanwenchao
 * @date 2018/10/11 23:55:02
 */
public enum KiwiSizeEnum {
    FIVE(5), TEN(10);

    private Integer kiwiSize;

    KiwiSizeEnum(Integer kiwiSize) {
        this.kiwiSize = kiwiSize;
    }

    public Integer getKiwiSize() {
        return kiwiSize;
    }
}
