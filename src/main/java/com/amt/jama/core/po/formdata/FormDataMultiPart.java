package com.amt.jama.core.po.formdata;

import com.amt.jama.core.po.bodypart.BodyPart;
import com.amt.jama.core.po.content.ContentDisposition;
import com.amt.jama.core.po.mediatype.MediaType;
import com.amt.jama.core.po.message.MessageBodyWorkers;
import com.amt.jama.core.po.multipart.MultiPart;
import com.amt.jama.core.po.parameter.ParameterizedHeader;
import com.amt.jama.core.po.providers.Providers;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FormDataMultiPart {
    private ContentDisposition contentDisposition;
    private Object entity;
    private Map<String, List<String>> headers;
    private MediaType mediaType;
    private MessageBodyWorkers messageBodyWorkers;
    private MultiPart parent;
    private Providers providers;
    private List<BodyPart> bodyParts;
    private Map<String,List<FormDataBodyPart>> fields;
    private Map<String,List<ParameterizedHeader>> parameterizedHeaders;
}
