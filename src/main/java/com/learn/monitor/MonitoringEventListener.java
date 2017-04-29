package com.learn.monitor;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午22:40
 */
public class MonitoringEventListener implements ApplicationEventListener {
    protected static Logger log = LoggerFactory.getLogger(MonitoringEventListener.class);
    private static Logger httpSlowLog = LoggerFactory.getLogger("httpSlowLog");
    private static final int SLOW_LOG_THRESHOLD_MILLISECOND = 500;

    @Inject
    private ServiceLocator serviceLocator;

    @Override
    public ReqEventListener onRequest(RequestEvent requestEvent) {
        switch (requestEvent.getType()) {
            case START:
                return new ReqEventListener();

        }
        return null;
    }

    @Override
    public void onEvent(ApplicationEvent event) {
        final ApplicationEvent.Type type = event.getType();
        switch (type) {
            case INITIALIZATION_START:
                break;
            case RELOAD_FINISHED:
            case INITIALIZATION_FINISHED:
                break;
            case DESTROY_FINISHED:
                break;

        }
    }

    private class ReqEventListener implements RequestEventListener {
        private volatile long requestTimeStart;
        private volatile long methodTimeStart;

        public ReqEventListener() {
            this.requestTimeStart = System.currentTimeMillis();
        }

        @Override
        public void onEvent(RequestEvent event) {
            final long now = System.currentTimeMillis();
            switch (event.getType()) {
                case RESOURCE_METHOD_START:
                    this.methodTimeStart = now;
                    break;
                case RESOURCE_METHOD_FINISHED:
                    long executeTime = now - methodTimeStart;
                    final ResourceMethod method = event.getUriInfo().getMatchedResourceMethod();
                    if (method != null) {
                        final Class<?> clazz = method.getInvocable().getHandler().getHandlerClass();
                        String[] x = clazz.getName().split("\\.");
                        if (x.length >= 1) {
                            log.info("{}.{},{}", x[x.length - 1], method.getInvocable().getHandlingMethod().getName(), executeTime);
                            if (executeTime > SLOW_LOG_THRESHOLD_MILLISECOND) {
                                httpSlowLog.info("{} {} {}", event.getUriInfo().getRequestUri(), method.getInvocable().getHandlingMethod().getName(), executeTime);
                            }
                        }
                    }
                    break;
                case EXCEPTION_MAPPING_FINISHED:
                    break;
                case FINISHED:
//                    final  ResourceMethod method2 = event.getUriInfo().getMatchedResourceMethod();
//                    if (method2!=null){
//                        log.info("class:{},method:{},request execution time:{}",method2.getInvocable().getHandler().getHandlerClass().getName(),method2.getInvocable().getHandlingMethod().getName(),now - requestTimeStart);
//                    }
//                    else {
//                        log.error("statistics method finish null");
//                    }
                    break;

            }
        }
    }
}
