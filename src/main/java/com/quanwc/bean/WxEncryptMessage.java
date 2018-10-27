package com.quanwc.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.quanwc.adapter.CDataAdapter;

import lombok.Data;

/**
 * Weixin encrypt Bean
 * @author quanwenchao
 * @date 2018/10/27 10:06:45
 */
@Data
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxEncryptMessage {

    @XmlJavaTypeAdapter(value = CDataAdapter.class)
    private String ToUserName;
    @XmlJavaTypeAdapter(value = CDataAdapter.class)
    private String Encrypt;
}
