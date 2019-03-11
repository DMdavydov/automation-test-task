package com.epam.tasks.task2;

import com.epam.tasks.task2.page_object.MainPage;
import com.epam.tasks.task2.page_object.MarketPage;
import com.epam.tasks.task2.page_object.CatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class MarketTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 5);

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void phoneTest() throws InterruptedException {
        driver.get("http://yandex.ru");

        MainPage mainPage = new MainPage(driver);

        MarketPage marketPage = mainPage.openMarket();
        marketPage.openElectronic();

        CatalogPage catalogPage = marketPage.openPhonePage();
        catalogPage.selectCheckBox("Samsung");
        catalogPage.setPriceFrom("40000");
        Thread.sleep(3000);

        WebElement firstPhone = catalogPage.getAllPhones().get(0);
        String phoneNameExpected = firstPhone.getText();
        firstPhone.click();

        WebElement phoneNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(phoneNameActual.getText()).isEqualTo(phoneNameExpected);
    }

    @Test
    public void headphoneTest() throws InterruptedException {
        driver.get("http://yandex.ru");

        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        MarketPage marketPage = mainPage.openMarket();
        marketPage.openElectronic();

        CatalogPage catalogPage = marketPage.openHeadphonePage();
        catalogPage.selectCheckBox("Beats");
        catalogPage.setPriceFrom("17000");
        catalogPage.setPriceTo("25000");
        Thread.sleep(3000);

        WebElement firstHeadphones = catalogPage.getAllHeadphones().get(0);
        String headphonesNameExpected = firstHeadphones.getText();
        firstHeadphones.click();

        WebElement headphonesNameActual = driver.findElement(By.xpath("//h1"));
        assertThat(headphonesNameActual.getText()).isEqualTo(headphonesNameExpected);
    }
}
