package com.amt.jama.core.fieldvalue;

import com.amt.jama.connect.exception.RestClientException;
import com.amt.jama.connect.exception.TypeMismatchException;
import com.amt.jama.core.po.releases.Release;
import com.amt.jama.util.jama.JamaInstance;

/**
 * @author WangWei
 */
public class ReleaseFieldValue extends JamaFieldValue{

    private Release value;

    public ReleaseFieldValue(){}

    public ReleaseFieldValue(JamaInstance jamaInstance) {
        setJamaInstance(jamaInstance);
    }

    @Override
    public Release getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value != null) {
            Integer releaseId = Integer.parseInt(value);
            try {
                this.value = getJamaInstance().getRelease(releaseId);
            } catch (Exception e) {
                throw new RestClientException(e);
            }
        }
    }

    @Override
    public void setValue(Object value) throws TypeMismatchException {
        checkType(Release.class, value);
        this.value = (Release) value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
