package com.learn.filters;

import com.learn.util.CommonUtil;
import com.learn.util.RequestUtil;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:52
 */
@Provider
@Priority(3000)
public class RequestFilter implements ContainerRequestFilter {
    private static Logger log = LoggerFactory.getLogger(RequestFilter.class);
    private static Logger postLog = LoggerFactory.getLogger("postInfo");


    @Context
    HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String cookieStr = CommonUtil.cookieStr(requestContext.getCookies());
        if (!CommonUtil.isEmpty(cookieStr)) {
            log.info("REQUEST COOKIE:{}", cookieStr);
        }

        log.debug("REQUEST HEADER:{}", requestContext.getHeaders());
        if (requestContext.getMethod().equals("POST")
                && requestContext.getHeaderString("Content-Type") != null
                && !requestContext.getHeaderString("Content-Type").contains("multipart/form-data")) {
            postLog.info("SOURCE:{},METHOD:{},URL:{},POST CONTENT:{}", requestContext.getHeaders().get(RequestUtil.HEADER_SOURCE), requestContext.getMethod(), requestContext.getUriInfo().getRequestUri().toString(), this.getPostContent(requestContext));
        }

        log.info("SOURCE:{},METHOD:{},URL:{}", requestContext.getHeaders().get(RequestUtil.HEADER_SOURCE), requestContext.getMethod(), requestContext.getUriInfo().getRequestUri().toString());
    }

    private String getPostContent(ContainerRequestContext request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = request.getEntityStream();
        try {
            ReaderWriter.writeTo(in, out);
            byte[] requestEntity = out.toByteArray();
            request.setEntityStream(new ByteArrayInputStream(requestEntity));
            return new String(requestEntity);
        } catch (IOException ex) {
            log.error("getPostContent error!", ex);
        }

        return "";
    }

}
