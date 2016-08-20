package com.workfront.intern.cb.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        CompetitionBoardApp.init(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
