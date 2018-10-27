package com.quanwc.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML CDATA Adapter
 * @author quanwenchao
 * @date 2018/10/27 10:09:02
 */
public class CDataAdapter extends XmlAdapter<String, String> {

    private static final String CDATA_FORMAT = "<![CDATA[%s]]>";
    private static final String CDATA_REGEX = "<!\\[CDATA\\[(.*?)\\]\\]>";

    @Override
    public String unmarshal(String str) throws Exception {
        return String.format(CDATA_FORMAT, str);
    }

    @Override
    public String marshal(String str) throws Exception {
        return str.replaceAll(CDATA_REGEX, "$1");
    }
}
