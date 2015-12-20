package com.bsu.kkrechko.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sony on 19.12.2015.
 */
public class PinboardPage extends Page {
    private final String BASE_URL = "https://www.pinterest.com/test5014/";
    private String currentPinboardName;

    @FindBy(xpath = "//h1")
    private WebElement pinboardNameText;

    @FindBy(xpath = "//div[@class='boardButtons']//button[1]")
    private WebElement buttonEditPinboard;

    @FindBy(xpath = "//div[@class='formFooterDelete']//button[1]")
    private WebElement buttonDeletePinboard;

    @FindBy(xpath = "//div[@class='ConfirmDialog Module inModal']//form//div[@class='formFooterButtons']//button[2]")
    private WebElement buttonConfirmDelete;

    public PinboardPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void openPage(String pinboardName)
    {
        driver.navigate().to(BASE_URL+pinboardName);
        currentPinboardName = pinboardName;
    }

    public String getPinboardName(){
        return pinboardNameText.getText();
    }


    public void deletePinboard(){
        buttonEditPinboard.click();
        buttonDeletePinboard.click();
        buttonConfirmDelete.click();
    }

}
