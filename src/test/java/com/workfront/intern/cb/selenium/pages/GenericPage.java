package com.workfront.intern.cb.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class GenericPage {
    static private WebDriver webDriver;

//    GenericPage() {
////        webDriver = new FirefoxDriver();
////        webDriver.get("http://localhost:8080");
//        webDriver = new ChromeDriver();
//        webDriver.get("http://localhost:8080");
//    }

    static public void init() {
        File file = new File("C:/bin/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        try {
            webDriver = new ChromeDriver();
            webDriver.get("http://localhost:8084");
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Selenium web driver failed: %s", ex.getMessage()));
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
