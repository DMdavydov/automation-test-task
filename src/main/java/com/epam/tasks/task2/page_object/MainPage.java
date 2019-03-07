package com.epam.tasks.task2.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private By marketLink = By.xpath("//a[@data-id='market']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MarketPage openMarket(){
        driver.findElement(marketLink).click();
        return new MarketPage(driver);
    }
}
