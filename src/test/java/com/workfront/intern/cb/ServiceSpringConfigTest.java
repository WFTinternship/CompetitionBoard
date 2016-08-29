package com.workfront.intern.cb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "com.workfront")
@Profile("testDb")
public class ServiceSpringConfigTest {


}
