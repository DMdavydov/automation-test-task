package com.epam.tasks.task2.browser;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final Logger log = Logger.getLogger(Browser.class);
    private static WebDriver driver;
    public static WebDriver init(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Driver initialized");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return driver;
    }

    public void getScreenshot(String result) throws IOException
    {
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("files/fail_screenshots/" + result + "_"
                + UUID.randomUUID() + "screenshot.png"));
    }

}
