package com.learn.filters;


import com.learn.exception.ErrorMessage;
import com.learn.exception.IllegalRequestException;
import com.learn.model.base.Result;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:52
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Context
    HttpServletResponse response;

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        Result result = new Result();
        //将返回对象包装为Result 结构
        Object obj = responseContext.getEntity();
        if (obj == null) {
            throw new IllegalRequestException(ErrorMessage.RESPONSE_NULL_DATA);
        }
        result.setCode(responseContext.getStatus());
        result.setResult(obj);

        if (responseContext.getStatus() == 204) {
            responseContext.setStatus(200);
        }

        if (responseContext.getStatus() >= 1000) {
            responseContext.setStatus(403);
        }


        //设定返回头application/json
//        responseContext.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        //获取返回值

        responseContext.setEntity(result);
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.addHeader("Access-Control-Max-Age", "1209600");
        if (responseContext.getStatus() == 200) {
            result.setMessage("server success");
        } else {
            result.setMessage("server error");
        }
    }
}
