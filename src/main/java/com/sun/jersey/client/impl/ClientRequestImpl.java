package com.sun.jersey.client.impl;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientRequestAdapter;
import com.sun.jersey.core.header.OutBoundHeaders;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.core.MultivaluedMap;

public final class ClientRequestImpl extends ClientRequest implements ClientRequestAdapter {
    private Map<String, Object> properties;
    private URI uri;
    private String method;
    private Object entity;
    private final MultivaluedMap<String, Object> metadata;
    private ClientRequestAdapter adapter;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ClientRequestImpl(URI uri, String method) {
        this(uri, method, (Object)null, (MultivaluedMap)null);
    }

    public ClientRequestImpl(URI uri, String method, Object entity) {
        this(uri, method, entity, (MultivaluedMap)null);
    }

    public ClientRequestImpl(URI uri, String method, Object entity, MultivaluedMap<String, Object> metadata) {
        this.uri = uri;
        this.method = method;
        this.entity = entity;
        this.metadata = (MultivaluedMap)(metadata != null ? metadata : new OutBoundHeaders());
        this.adapter = this;
        logger.info(method + " by url : " + uri + (entity == null ? "" : ", with data : " + entity.toString()));
    }

    public Map<String, Object> getProperties() {
        if (this.properties == null) {
            this.properties = new HashMap();
        }

        return this.properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public URI getURI() {
        return this.uri;
    }

    public void setURI(URI uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getEntity() {
        return this.entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public MultivaluedMap<String, Object> getMetadata() {
        return this.getHeaders();
    }

    public MultivaluedMap<String, Object> getHeaders() {
        return this.metadata;
    }

    public ClientRequestAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(ClientRequestAdapter adapter) {
        this.adapter = (ClientRequestAdapter)(adapter != null ? adapter : this);
    }

    public ClientRequest clone() {
        return new ClientRequestImpl(this.uri, this.method, this.entity, clone(this.metadata));
    }

    private static MultivaluedMap<String, Object> clone(MultivaluedMap<String, Object> md) {
        MultivaluedMap<String, Object> clone = new OutBoundHeaders();
        Iterator i$ = md.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<String, List<Object>> e = (Entry)i$.next();
            clone.put(e.getKey(), new ArrayList((Collection)e.getValue()));
        }

        return clone;
    }

    public OutputStream adapt(ClientRequest request, OutputStream out) throws IOException {
        return out;
    }
}