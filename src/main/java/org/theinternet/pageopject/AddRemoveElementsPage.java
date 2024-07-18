package org.theinternet.pageopject;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    private By getPageTitle() {
        return By.xpath("//div//h3[text() = 'Add/Remove Elements']");
    }

    private By getAddButton() {
        return By.xpath("//button[@onclick = 'addElement()']");
    }

    private By getDeteleButton() {
        return By.xpath("//button[@onclick = 'deleteElement()']");
    }


    public boolean isTitleCorrect() {
        return driver.findElement(getPageTitle()).getText().equals("Add/Remove Elements");
    }

    public void clickAddElement(int counter) {
        for (int i = 0; i < counter; i++) {
            driver.findElement(getAddButton()).click();
        }
    }

    public List<WebElement> getRemoveButtonElements() {
        return driver.findElements(getDeteleButton());
    }

    public boolean clickOnDeleteButton(int counter) {
        List<WebElement> removeButtonElements = getRemoveButtonElements();
        for (int i = 0; i < counter; i++) {
            removeButtonElements.get(i).click();
        }
        return driver.findElements(getDeteleButton()).isEmpty();
    }
}
