package org.theinternet.pageopject;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By getFormAuthenticationButton() {
        return By.xpath("//*[@href='/login']");
    }

    private By getHomeTitle() {
        return By.xpath("//h1[@class = 'heading']");
    }

    public boolean checkHomeTitleIsCorrect() {
        return driver.findElement(getHomeTitle()).getText().contains("Welcome to the-internet");
    }

    public LoginPage clickOnFormAuthenticationButton() {
        driver.findElement(getFormAuthenticationButton()).click();
        return new LoginPage(driver);
    }
}
