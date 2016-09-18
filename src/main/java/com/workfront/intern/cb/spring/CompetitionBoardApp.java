package com.workfront.intern.cb.spring;

import com.workfront.intern.cb.dao.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CompetitionBoardApp {

    @Bean
    public DataSource getDataSource() {
        return DBManager.getDataSource();
    }
}