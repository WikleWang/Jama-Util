package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.core.po.users.User;
import com.amt.jama.util.jama.JamaInstance;

public class UserFieldValue extends JamaFieldValue {

    private User value;

    public UserFieldValue() {
    }

    public UserFieldValue(JamaInstance jamaInstance) {
        this.setJamaInstance(jamaInstance);
    }

    @Override
    public User getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int userId = Integer.parseInt(value);
        try {
            this.value = getJamaInstance().getUser(userId);
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }


    public void setValue(Object value) throws TypeMismatchException {
        checkType(User.class, value);
        this.value = (User) value;
    }


    @Override
    public String toString() {
        return this.value.toString();
    }
}

