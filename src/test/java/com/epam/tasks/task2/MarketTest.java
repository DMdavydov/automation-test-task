package com.epam.tasks.task2;

import com.epam.tasks.task2.browser.Browser;
import com.epam.tasks.task2.page_object.MainPage;
import com.epam.tasks.task2.page_object.MarketPage;
import com.epam.tasks.task2.page_object.CatalogPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class MarketTest {
    private static final Logger log = Logger.getLogger(MarketTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = Browser.init();
        wait = new WebDriverWait(driver, 5);
        driver.get("http://yandex.ru");
        assertThat(driver.getTitle()).isEqualTo("Яндекс");
        log.info("Yandex is opened");
    }

    @AfterMethod
    public void close() {
        driver.quit();
        log.info("Driver closed");
    }

    @Test
    public void phoneTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        MarketPage marketPage = mainPage.openMarket();
        marketPage.openElectronic();

        CatalogPage catalogPage = marketPage.openPhonePage();
        WebElement firstPhone = catalogPage.getAllPhones().get(0);
        catalogPage.selectCheckBox("Samsung");
        catalogPage.setPriceFrom("40000");

        wait.until(ExpectedConditions.stalenessOf(firstPhone));

        firstPhone = catalogPage.getAllPhones().get(0);
        String phoneNameExpected = firstPhone.getText();
        firstPhone.click();

        WebElement phoneNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(phoneNameActual.getText()).isEqualTo(phoneNameExpected);
    }

    @Test
    public void headphoneTest() throws InterruptedException {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        MarketPage marketPage = mainPage.openMarket();
        marketPage.openElectronic();

        CatalogPage catalogPage = marketPage.openHeadphonePage();
        WebElement firstHeadphones = catalogPage.getAllHeadphones().get(0);
        catalogPage.selectCheckBox("Beats");
        catalogPage.setPriceFrom("17000");
        catalogPage.setPriceTo("25000");

        wait.until(ExpectedConditions.stalenessOf(firstHeadphones));

        firstHeadphones = catalogPage.getAllHeadphones().get(0);
        String headphonesNameExpected = firstHeadphones.getText();
        firstHeadphones.click();

        WebElement headphonesNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(headphonesNameActual.getText()).isEqualTo(headphonesNameExpected);
    }
}
