package com.workfront.intern.cb.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GenericPage {
    static private WebDriver webDriver;

//    GenericPage() {
////        webDriver = new FirefoxDriver();
////        webDriver.get("http://localhost:8080");
//        webDriver = new ChromeDriver();
//        webDriver.get("http://localhost:8080");
//    }

    static public void init () {
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8083");
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
