package com.quanwc.enumeration;

/**
 * 消息类型枚举
 * @author quanwenchao
 * @date 2018/9/11 19:14:45
 */
public enum MessageTypeEnum {
    TEXT("text"),
    IMAGE("image"),
    VOICE("voice"),
    VIDEO("video"),
    SHORTVIDEO("shortvideo"),
    LOCATION("location"),
    LINK("link"),
    FILE("file"),

    EVENT("event"),
    UNSUBSCRIBE("unsubscribe"),
    SUBSCRIBE("subscribe"),
    VIEW("VIEW");

    private final String messageType;

    MessageTypeEnum(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
