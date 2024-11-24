package com.example.javaflutterappium.tests;

import com.example.javaflutterappium.ExtentReportManager;
import com.example.javaflutterappium.base.AppiumSetup;
import com.example.javaflutterappium.page.HomePage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportManager.class)
public class AutomationTest {
    private RemoteWebDriver appiumDriver;
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        AppiumSetup appiumSetup = new AppiumSetup();
        appiumDriver = appiumSetup.initializeDriver();
        homePage = new HomePage(appiumDriver);
    }

    @Test
    public void testAppium() {
        homePage.realizarTeste();
    }

    @AfterClass
    public void teardown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
