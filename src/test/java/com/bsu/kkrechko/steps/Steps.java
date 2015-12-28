package com.bsu.kkrechko.steps;

import com.bsu.kkrechko.pages.LoginPage;
import com.bsu.kkrechko.pages.MainPage;
import com.bsu.kkrechko.pages.PinboardPage;
import com.bsu.kkrechko.pages.UserPage;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Steps {
    private final Logger logger = Logger.getLogger(Steps.class);
    private WebDriver driver;
    private int previousPinsNumber=0;
    private int currentPinsNumber=0;

    public void initBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        logger.info("Browser started");
    }

    public void closeDriver(){
        driver.quit();
        logger.info("Browser stoped");
    }

    public void loginPinterest(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(email,password);
        logger.info("Login pinterest");
    }

    public boolean isLoggedIn(String username)
    {
        LoginPage loginPage = new LoginPage(driver);
        return (loginPage.getLoggedInUserName().trim().toLowerCase().equals(username));
    }

    public void createNewPinboard(String pinboardName, String pinboardDescription){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickUserPage();
        UserPage userPage = new UserPage(driver);
        userPage.createNewPinboard(pinboardName,pinboardDescription);
        logger.info("New pinboard " + pinboardName + " is created");
    }

    public String getPinboardName(String pinboardName){
        PinboardPage pinboardPage = new PinboardPage(driver);
        pinboardPage.openPage(pinboardName);
        return pinboardPage.getPinboardName().toLowerCase();
    }

    public void deletePinboard(String pinboardName){
        PinboardPage pinboardPage = new PinboardPage(driver);
        pinboardPage.openPage(pinboardName);
        pinboardPage.deletePinboard();
        logger.info("Pinboard " + pinboardName + " is deleted");
    }

    public boolean pinboardFound(String pinboardName){
        UserPage userPage = new UserPage(driver);
        userPage.openPage();
        return userPage.pinboardIsFound(pinboardName);
    }

    public boolean pinWithTextFound(String text){
        MainPage mainPage = new MainPage(driver);
        mainPage.searchText(text);
        logger.info("Seacrh \" " + text + " \" was performed");
        return mainPage.pinIsFound();
    }

    public boolean addRandomPin(){
        UserPage up = new UserPage(driver);
        up.openPage();
        previousPinsNumber = up.getNumberPins();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.addPin();
        mainPage.openPage();
        mainPage.clickUserPage();
        UserPage userPage = new UserPage(driver);
        currentPinsNumber = userPage.getNumberPins();
        if(currentPinsNumber == previousPinsNumber+1){
            return true;
        }
        else {
            return false;
        }
    }
}
