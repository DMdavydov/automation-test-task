package com.epam.tasks.task2.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPage {

    private By priceFrom = By.id("glpricefrom");
    private By priceTo = By.id("glpriceto");
    private By phones = By.xpath("//a[contains(text(), 'Смартфон')]");
    private By headphones = By.xpath("//a[contains(text(), 'Наушники')]");
    private String checkBox = "//span[text()='%s']";

    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setPriceFrom(String input){
        driver.findElement(priceFrom).sendKeys(input);
    }

    public void setPriceTo(String input){
        driver.findElement(priceTo).sendKeys(input);
    }

    public List<WebElement> getAllPhones(){
        return driver.findElements(phones);
    }

    public List<WebElement> getAllHeadphones(){
        return driver.findElements(headphones);
    }

    public void selectCheckBox(String name){
        if (!driver.findElement(By.xpath(String.format(checkBox, name))).isSelected())
            driver.findElement(By.xpath(String.format(checkBox, name))).click();
    }

    public void deselectCheckBox(String name){
        if (driver.findElement(By.xpath(String.format(checkBox, name))).isSelected())
            driver.findElement(By.xpath(String.format(checkBox, name))).click();
    }
}
