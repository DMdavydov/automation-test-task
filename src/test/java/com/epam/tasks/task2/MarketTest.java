package com.epam.tasks.task2;

import com.epam.tasks.task2.browser.Browser;
import com.epam.tasks.task2.page_object.CatalogPage;
import com.epam.tasks.task2.page_object.MainPage;
import com.epam.tasks.task2.page_object.MarketPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.qameta.allure.Allure.step;

public class MarketTest {
    private static final Logger log = Logger.getLogger(MarketTest.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = Browser.init();
        wait = new WebDriverWait(driver, 5);
        driver.get("http://yandex.ru");
        //assertThat(driver.getTitle()).isEqualTo("Яндекс");
        log.info("Yandex is opened");
    }

    @AfterMethod
    public void close() {
        driver.quit();
        log.info("Driver closed");
    }

    @Test
    public void phoneTest() {
        MainPage mainPage = new MainPage(driver);

        step("Open market");
        MarketPage marketPage = mainPage.openMarket();
        step("Open electronic");
        marketPage.openElectronic();

        step("Open phones");
        CatalogPage catalogPage = marketPage.openPhonePage();
        step("Set filter");
        WebElement firstPhone = catalogPage.getAllPhones().get(0);
        catalogPage.selectCheckBox("Samsung");
        catalogPage.setPriceFrom("40000");

        wait.until(ExpectedConditions.stalenessOf(firstPhone));

        step("Open first phone");
        firstPhone = catalogPage.getAllPhones().get(0);
        String phoneNameExpected = firstPhone.getText();
        firstPhone.click();

        WebElement phoneNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(phoneNameActual.getText()).isEqualTo(phoneNameExpected);
    }

    @Test
    public void headphoneTest() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        step("Open market");
        MarketPage marketPage = mainPage.openMarket();
        step("Open electronic");
        marketPage.openElectronic();

        step("Open headphones");
        CatalogPage catalogPage = marketPage.openHeadphonePage();
        step("Set filter");
        WebElement firstHeadphones = catalogPage.getAllHeadphones().get(0);
        catalogPage.selectCheckBox("Beats");
        catalogPage.setPriceFrom("17000");
        catalogPage.setPriceTo("25000");

        wait.until(ExpectedConditions.stalenessOf(firstHeadphones));

        step("Open first headphone");
        firstHeadphones = catalogPage.getAllHeadphones().get(0);
        String headphonesNameExpected = firstHeadphones.getText();
        firstHeadphones.click();

        WebElement headphonesNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(headphonesNameActual.getText()).isEqualTo(headphonesNameExpected);
    }
}
