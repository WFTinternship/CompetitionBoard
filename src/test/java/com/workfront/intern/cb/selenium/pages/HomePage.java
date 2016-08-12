package com.workfront.intern.cb.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends GenericPage {

    public HomePage() {
        //getWebDriver().get("http://localhost:8080");
    }

    public WebElement clickLogin() throws InterruptedException {
        WebElement logInFormBtn = getWebDriver().findElement(By.name("logInMenuBtn"));
        logInFormBtn.click();
        WebElement logInBtn = getWebDriver().findElement(By.cssSelector("a[href='#login']"));
        logInBtn.click();

        return getLoginForm();
    }

    public WebElement getLoginForm() throws InterruptedException {
        Thread.sleep(2000);
        return getWebDriver().findElement(By.cssSelector(".form"));
    }

    public WebElement getLogoutButton() {
        return getWebDriver().findElement(By.id("logout_button"));
    }
}