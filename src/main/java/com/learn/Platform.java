package com.learn;

import com.learn.monitor.MonitoringEventListener;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午22:39
 */
public class Platform extends ResourceConfig {
    public Platform() {
        register(JacksonFeature.class);
        register(MonitoringEventListener.class);
        register(MultiPartFeature.class);
    }
}