package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.util.date.DateUtil;

import java.text.ParseException;
import java.util.Date;

public class DateFieldValue extends JamaFieldValue {

    private Date value;

    @Override
    public Date getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        try {
            this.value = DateUtil.parseDate(value);
        } catch (ParseException e) {
            throw new RestClientException("Unable to parse date: " + value);
        }

    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        checkType(Date.class, value);
        this.value = (Date) value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
