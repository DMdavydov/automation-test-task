package com.epam.tasks.task2.page_object;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final Logger log = Logger.getLogger(MarketPage.class);
    private By marketLink = By.xpath("//a[@data-id='market']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MarketPage openMarket(){
        driver.findElement(marketLink).click();
        log.info("Market page is opened");
        return new MarketPage(driver);
    }
}
