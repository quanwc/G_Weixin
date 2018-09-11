package com.quanwc.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.internal.bind.marshaller.DataWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * xml util
 * @author quanwenchao
 * @date 2018/9/11 15:50:34
 */
@Slf4j
public class XmlUtils {

    private static final String UTF8 = "UTF-8";

    /**
     * xml转object
     * @param clazz
     * @param xml
     * @param <T>
     * @return
     */
    public static <T> T xmlToObject(Class<T> clazz, String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            T message = (T) unmarshaller.unmarshal(reader);
            return message;
        } catch (JAXBException ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
            throw new IllegalArgumentException("Message convert error!");
        }
    }

    /**
     * object转xml
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToXml(T object) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, UTF8);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            DataWriter dataWriter = new DataWriter(stringWriter, UTF8, new CharacterEscapeHandler() {
                @Override
                public void escape(char[] chars, int start, int len, boolean b, Writer writer) throws IOException {
                    writer.write(chars, start, len);
                }
            });

            jaxbMarshaller.marshal(object, dataWriter);
            return stringWriter.toString();

        } catch (JAXBException ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
            throw new IllegalArgumentException("Message convert error!");
        }
    }
}