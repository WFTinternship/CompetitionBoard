package com.workfront.intern.cb.selenium.test;

import com.workfront.intern.cb.selenium.pages.GenericPage;
import com.workfront.intern.cb.selenium.pages.HomePage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class HomePageTest {
    static private HomePage homePage;

    @BeforeClass
    static public void setUp() {
        homePage = new HomePage();
        GenericPage.init();
    }

    @AfterClass
    static public void tearDown() {
        homePage.getWebDriver().close();
    }

    @Test
    public void loginButtonClick() throws InterruptedException {
        WebElement loginPopup = homePage.clickLogin();
        Assert.assertNotNull("Login Popup is not displayed", loginPopup);
    }
}