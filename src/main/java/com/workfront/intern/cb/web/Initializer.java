package com.workfront.intern.cb.web;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.io.File;

@Component
public class Initializer implements WebApplicationInitializer, ApplicationContextAware {
    private static Logger LOG = Logger.getLogger(Initializer.class);

    /***/
    private static ApplicationContext APPLICATION_CONTEXT;
    private static ServletContext SERVLET_CONTEXT;
    private static String SERVER_ROOT_PATH;
    private static String RESOURCES_PATH;
    private static String FILES_PATH;
    private static boolean REDIRECT_UNAUTHORIZED_REQUESTS_TO_HOME;

    public static final File tempDir = new File(System.getProperty("java.io.tmpdir"));

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
        init();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        SERVLET_CONTEXT = servletContext;
    }

    public void init() {
        SERVER_ROOT_PATH = SERVLET_CONTEXT.getRealPath("/");

        RESOURCES_PATH = SERVER_ROOT_PATH + File.separator + "resources";
        FILES_PATH = RESOURCES_PATH + File.separator + "files";
        File filesDir = new File(FILES_PATH);
        if (!filesDir.exists()) {
            if (!filesDir.mkdirs()) {
                LOG.error("-- could not create files directory");
            }
        }

        REDIRECT_UNAUTHORIZED_REQUESTS_TO_HOME = Boolean.valueOf(
                SERVLET_CONTEXT.getInitParameter("redirectToHomeOnUnauthorizedRequest")
        );

        LOG.info("-- context initialized");
    }

    ///////////////////////////////////////////////

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static ServletContext getServletContext() {
        return SERVLET_CONTEXT;
    }

    public static String getServerRootPath() {
        return SERVER_ROOT_PATH;
    }

    public static String getResourcesPath() {
        return RESOURCES_PATH;
    }

    public static String getFilesPath() {
        return FILES_PATH;
    }

    public static boolean isRedirectUnauthorizedRequestsToHome() {
        return REDIRECT_UNAUTHORIZED_REQUESTS_TO_HOME;
    }
}