package org.theinternet.pageopject;

import base.BasePage;
import org.theinternet.components.AlertMessageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private AlertMessageComponent alertMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        alertMessage = new AlertMessageComponent(driver);
    }

    public AlertMessageComponent getAlertMessage() {
        return alertMessage;
    }

    private By getLoginMainTitle() {
        return By.xpath("//h2[text()='Login Page']");
    }

    private By getLoginImage() {
        return By.xpath("//img");
    }

    private By getUserNameField() {
        return By.id("username");
    }

    private By getPasswordField() {
        return By.id("password");
    }

    private By getConfirmButton() {
        return By.xpath("//button[@type = 'submit']");
    }

    public void setCredentials(String username, String password) {
        driver.findElement(getUserNameField()).sendKeys(username);
        driver.findElement(getPasswordField()).sendKeys(password);
    }

    public BasePage clickOnConfirmButton(final boolean isLoginPage) {
        driver.findElement(getConfirmButton()).click();
        return isLoginPage ? this : new SecurePage(driver);
    }

    public boolean checkLoginImageIsDisplayed() {
        return driver.findElement(getLoginImage()).isDisplayed();
    }

    public boolean checkLoginMainTitleIsCorrect() {
        return driver.findElement(getLoginMainTitle()).getText().contains("Login Page");
    }
}
