package com.bsu.kkrechko.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {
    private final String BASE_URL = "https://www.pinterest.com/login/";

    @FindBy(xpath = "//input[@name='username_or_email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//button[@class='Module UserNavigateButton merged']")
    private WebElement linkLoggedInUser;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void login(String userEmail, String password)
    {
        inputEmail.sendKeys(userEmail);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
    }

    public String getLoggedInUserName()
    {
        return linkLoggedInUser.getText();
    }

}
