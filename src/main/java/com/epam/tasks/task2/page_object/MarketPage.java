package com.epam.tasks.task2.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarketPage {

    private By electronic = By.xpath("//span[text()='Электроника']");
    private By mobilePhones = By.xpath("(//a[contains(@href, 'catalog--mobil')])[last()]");
    private By headphones = By.xpath("(//a[contains(@href, 'catalog--naush')])[last()]");

    private final WebDriver driver;

    public MarketPage(WebDriver driver) {
        this.driver = driver;
    }

    public MarketPage openElectronic(){
        driver.findElement(electronic).click();
        return this;
    }

    public CatalogPage openPhonePage() {
        driver.findElement(mobilePhones).click();
        return new CatalogPage(driver);
    }

    public CatalogPage openHeadphonePage() {
        driver.findElement(headphones).click();
        return new CatalogPage(driver);
    }
}
