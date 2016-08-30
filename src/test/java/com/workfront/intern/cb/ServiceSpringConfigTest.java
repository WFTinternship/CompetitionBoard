package com.workfront.intern.cb;

import com.workfront.intern.cb.dao.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.workfront")
@Profile("test")
public class ServiceSpringConfigTest {

    @Bean
    public DataSource getDataSource() {
        return DBManager.getDataSource();
    }
}
