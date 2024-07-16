package org.theinternet.pageopject;

import base.BasePage;
import org.theinternet.components.AlertMessageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {

    private AlertMessageComponent alertMessage;

    public SecurePage(WebDriver driver) {
        super(driver);
        alertMessage = new AlertMessageComponent(driver);
    }

    public AlertMessageComponent getAlertMessage() {
        return alertMessage;
    }

    private By getLogOutButton() {
        return By.xpath("//a[@href = '/logout']");
    }

    public LoginPage clickOnLogOutButton() {
        driver.findElement(getLogOutButton()).click();
        return new LoginPage(driver);
    }

}
