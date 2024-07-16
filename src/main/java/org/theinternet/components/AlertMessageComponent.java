package org.theinternet.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertMessageComponent {

    WebDriver driver;

    public AlertMessageComponent(WebDriver driver) {
        this.driver = driver;
    };

    private By getAlertMessage() {
        return By.id("flash");
    }

    public String checkAlertMessageIsCorrect() {
        return driver.findElement(getAlertMessage()).getText().trim();
    }
}
