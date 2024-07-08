package org.example.driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageopject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.example.testdata.Url.HOME_URL;

public class DriverManager {

    private WebDriver driver;

    public DriverManager() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public HomePage openHomePageByUrl() {
        driver.get(HOME_URL.getUrl());
        return new HomePage(driver);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
