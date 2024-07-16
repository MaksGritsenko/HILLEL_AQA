package driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.theinternet.pageopject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import saucedemo.com.pageobject.AutorisationPage;

import java.util.concurrent.TimeUnit;

import static testdata.Url.HOME_URL;

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

    public AutorisationPage openAutorisationPageByUrl() {
        driver.get("https://www.saucedemo.com/");
        return new AutorisationPage(driver);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
