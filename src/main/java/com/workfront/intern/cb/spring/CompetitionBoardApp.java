package com.workfront.intern.cb.spring;

import com.workfront.intern.cb.dao.DBManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.workfront")
public class CompetitionBoardApp {
    public static final String SPRING_CONTEXT_KEY = "springContextKey";

    public static void init(ServletContext servletContext) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CompetitionBoardApp.class);
        servletContext.setAttribute(SPRING_CONTEXT_KEY, applicationContext);
    }

    public static ApplicationContext getApplicationContext(ServletContext servletContext) {
        return (ApplicationContext) servletContext.getAttribute(SPRING_CONTEXT_KEY);
    }

    @Bean
    public DataSource getDataSource() {
        return DBManager.getDataSource();
    }
}