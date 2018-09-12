package com.quanwc.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Weixin Bean
 * @author quanwenchao
 * @date 2018/8/18 18:50:13
 */
@Data
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxMessage {

    /**
     * ToUserName
     */
    private String ToUserName;

    /**
     * FromUserName
     */
    private String FromUserName;

    /**
     * 消息创建时间
     */
    private Long CreateTime;

    /**
     * 消息类型：text、image、video、event等
     */
    private String MsgType;

    /**
     * 文本消息内容
     */
    private String Content;

    /**
     * 消息id，64位整型
     */
    private String MsgId;

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

    /**
     * 标题
     */
    private String Title;

    /**
     * 描述
     */
    private String Description;

    /**
     * 音乐链接
     */
    private String MusicURL;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String PicUrl;

    /**
     *	点击图文消息跳转链接
     */
    private String Url;

    /**
     * 事件类型，CLICK
     */
    private String Event;

    /**
     * 事件KEY值
     */
    private String EventKey;

    /**
     *	地理位置纬度
     */
    private Double Latitude;

    /**
     * 地理位置经度
     */
    private Double Longitude;

    /**
     * 地理位置精度
     */
    private Double Precision;
}
