package com.workfront.intern.cb.selenium.pages;

import com.workfront.intern.cb.selenium.pages.GenericPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPopup extends GenericPage {

    public void typeUsername(String username) throws InterruptedException {
        WebElement usernameField = getWebDriver().findElement(By.id("username"));
        Thread.sleep(1000);
        usernameField.sendKeys(username);
    }

    public void typePassword(String password) {
        WebElement passwordField = getWebDriver().findElement(By.name("password"));
        passwordField.sendKeys(password);
    }

    public void clickSignin() {
        WebElement signinButton = getWebDriver().findElement(By.id("login"));
        signinButton.click();
    }
}
