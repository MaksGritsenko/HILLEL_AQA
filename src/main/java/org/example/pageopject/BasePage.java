package org.example.pageopject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.example.testdata.Url.HOME_URL;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlDisplayed(String url) {
        return driver.getCurrentUrl().equals(url);
    }
}
