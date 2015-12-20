package com.bsu.kkrechko;

import com.bsu.kkrechko.steps.Steps;
import com.bsu.kkrechko.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumWebDriverTest {
    private Steps steps;
    private final String USER_EMAIL = "pinterest.test@yandex.com";
    private final String PASSWORD = "pinterest123";
    private final String USER_NAME = "test";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test(description = "Login to Github")
    public void oneCanLoginPinterest(){
        steps.loginPinterest(USER_EMAIL, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(USER_NAME));
    }

    @Test
    public void oneCanCreatePinboard(){
        steps.loginPinterest(USER_EMAIL, PASSWORD);
        String pinboardName = "testName" + Utils.getRandomString(5);
        String pinboardDescription = "testDescription" + Utils.getRandomString(5);
        steps.createNewPinboard(pinboardName, pinboardDescription);
        Assert.assertEquals(pinboardName.toLowerCase(), steps.getPinboardName(pinboardName).toLowerCase());
    }

    @Test
    public void oneCanDeletePinboard(){
        String pinboardName = "testName" + Utils.getRandomString(5);
        String pinboardDescription = "testDescription" + Utils.getRandomString(5);
        steps.loginPinterest(USER_EMAIL, PASSWORD);
        steps.createNewPinboard(pinboardName, pinboardDescription);
        steps.deletePinboard(pinboardName);
        Assert.assertFalse(steps.pinboardFound(pinboardName));
    }

    @Test
    public void oneCanFindPin(){
        steps.loginPinterest(USER_EMAIL, PASSWORD);
        Assert.assertTrue(steps.pinWithTextFound("swift"));
    }

    @Test
    public void oneCanPin(){

    }

/*    @Test
    public void oneCanPinYoutubeVideo(){

    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }*/

}
