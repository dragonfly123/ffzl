package org.dragonfei.http.httpxml.enordecode.response;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Created by longfei on 16-7-7.
 */
public class HttpXmlResponse {
    private FullHttpResponse httpResponse;
    private Object result;

    public HttpXmlResponse(FullHttpResponse response,Object result){
        this.httpResponse = response;
        this.result = result;
    }

    public FullHttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(FullHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
