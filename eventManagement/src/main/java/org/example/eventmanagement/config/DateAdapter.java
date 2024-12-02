package org.example.eventmanagement.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public Date unmarshal(String v) throws Exception {
        return new SimpleDateFormat(DATE_FORMAT).parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return new SimpleDateFormat(DATE_FORMAT).format(v);
    }
}
