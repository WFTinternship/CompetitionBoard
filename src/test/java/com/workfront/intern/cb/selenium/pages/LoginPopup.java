package com.workfront.intern.cb.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPopup extends GenericPage {

    public void typeUsername(String username) throws InterruptedException {
        WebElement usernameField = getWebDriver().findElement(By.id("userNameSignIn"));
        Thread.sleep(1000);
        usernameField.sendKeys(username);
    }

    public void typePassword(String password) {
        WebElement passwordField = getWebDriver().findElement(By.name("passwordSignIn"));
        passwordField.sendKeys(password);
    }

    public void clickSignin() {
        WebElement signinButton = getWebDriver().findElement(By.id("logInButton"));
        signinButton.click();
    }
}
