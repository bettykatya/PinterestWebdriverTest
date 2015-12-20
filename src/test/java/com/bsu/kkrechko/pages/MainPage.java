package com.bsu.kkrechko.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{
    private final String BASE_URL = "https://www.pinterest.com/";

    @FindBy(xpath = "//button[@class='Module UserNavigateButton merged']")
    private WebElement buttonOpenUserPage;

    @FindBy(xpath = "//input")
    private WebElement inputSearch;

    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage(){
        driver.navigate().to(BASE_URL);
    }


    public void clickUserPage(){
        buttonOpenUserPage.click();
    }

    public void searchText(String text){
        inputSearch.sendKeys(text);
        inputSearch.sendKeys(Keys.ENTER);

    }

    public boolean pinIsFound(){
        String text = "Не удалось найти Пины по запросу";
        try{
            WebElement element =  driver.findElement(By.xpath("//div[@class='noResults']"));
        }
        catch(NoSuchElementException e){
            return true;
        }
        return false;
    }

}
