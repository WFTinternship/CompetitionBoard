package com.workfront.intern.cb.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends GenericPage {

    public HomePage() {
        //getWebDriver().get("http://localhost:8080");
    }

    public WebElement clickLogin() throws InterruptedException {
        WebElement loginButton = getWebDriver().findElement(By.cssSelector("#login_button"));
        loginButton.click();
        return getLoginPopup();
    }

    public WebElement getLoginPopup() throws InterruptedException {
        Thread.sleep(2000);
        WebElement loginPopup = getWebDriver().findElement(By.cssSelector(".login_dialog"));
        return loginPopup;
    }

    public WebElement getLogoutButton() {
        return getWebDriver().findElement(By.id("logout_button"));
    }
}
