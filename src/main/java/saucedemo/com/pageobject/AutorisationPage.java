package saucedemo.com.pageobject;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.theinternet.pageopject.SecurePage;

public class AutorisationPage extends BasePage {

    public AutorisationPage(WebDriver driver) {
        super(driver);
    }

    private By getAutorisationMainTitle() {
        return By.xpath("//div[text()='Swag Labs']");
    }

    private By getUserNameField() {
        return By.id("user-name");
    }

    private By getPasswordField() {
        return By.id("password");
    }

    private By getConfirmButton() {
        return By.id("login-button");

    }

    public void setCredentials(String username, String password) {
        driver.findElement(getUserNameField()).sendKeys(username);
        driver.findElement(getPasswordField()).sendKeys(password);
    }

    public boolean checkAutorisationMainTitleIsCorrect() {
        return driver.findElement(getAutorisationMainTitle()).getText().equals("Swag Labs");
    }

    public InventoryPage clickOnConfirmButton() {
        driver.findElement(getConfirmButton()).click();
        return new InventoryPage(driver);
    }
}
