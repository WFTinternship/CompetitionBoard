package com.workfront.intern.cb;

import com.workfront.intern.cb.dao.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.workfront")
public class ServiceSpringConfigTest {

    @Bean
    @Profile("test")
    public DataSource getDataSource() {
        return DBManager.getTestDataSource();
    }
}
