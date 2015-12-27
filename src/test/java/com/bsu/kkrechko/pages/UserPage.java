package com.bsu.kkrechko.pages;

import com.bsu.kkrechko.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by sony on 19.12.2015.
 */
public class UserPage extends Page {
    private final String BASE_URL = "https://www.pinterest.com/test5014/";
    private final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

    @FindBy(xpath = "//a[@class='BoardCreateRep ModalTrigger Module']")
    private WebElement linkNewPinboard;

    @FindBy(id = "boardEditName")
    private WebElement inputPinboardName;

    @FindBy(id = "boardEditDescription")
    private WebElement inputPinboardDescription;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonCreatePinboard;

    @FindBy(xpath = "//li//div[@class='Module PinCount']//span[1]")
    private WebElement pinNumberLabel;

    public UserPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage(){
        driver.navigate().to(BASE_URL);
    }

    public void createNewPinboard(String pinboardFullName, String pinboardDescription){
        linkNewPinboard.click();
        inputPinboardName.sendKeys(pinboardFullName);
        inputPinboardDescription.sendKeys(pinboardDescription);
        buttonCreatePinboard.click();
    }

    public void openPinboardpage(String pinboardName){
        String pinboardURL = driver.getCurrentUrl() + pinboardName.toLowerCase();
        driver.navigate().to(pinboardURL);
    }

    public boolean pinboardIsFound(String pinboardName){

        try{
            WebElement element =  driver.findElement(By.xpath("//div[contains(text()="+pinboardName+")]"));
        }
        catch(NoSuchElementException e){
            return false;
        }
        return true;
    }

    public int getNumberPins(){
        int temp = Integer.parseInt(pinNumberLabel.getText());
        return temp;
    }
}
