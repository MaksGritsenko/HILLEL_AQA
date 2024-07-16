package saucedemo.com.pageobject;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartButton() {
        return By.id("add-to-cart-sauce-labs-backpack");
    }

    private By getShippingCounterElement() {
        return By.xpath("//span[@class = 'shopping_cart_badge']");
    }

    private By getRemoveButton() {
        return By.id("remove-sauce-labs-backpack");
    }

    private By getInventoryTitle() {
        return By.xpath("//div[@class= 'app_logo']");
    }

    public boolean isAddToCartButtonNameCorrect() {
        return driver.findElement(getAddToCartButton()).getText().equals("Add to cart");
    }

    public boolean isRemoveButtonNameCorrect() {
        return driver.findElement(getRemoveButton()).getText().equals("Remove");
    }


    public void clickOnAddToCartButton() {
        driver.findElement(getAddToCartButton()).click();
    }

    public void clickOnRemoveButton() {
        driver.findElement(getRemoveButton()).click();
    }

    public boolean isTitleCorrect () {
        return driver.findElement(getInventoryTitle()).getText().equals("Swag Labs");
    }

    public int getShippingCounter() {
         return Integer.parseInt(driver.findElement(getShippingCounterElement()).getText());
    }

    public boolean isShippingCounterDisplayed() {
        return driver.findElement(getShippingCounterElement()).isDisplayed();
    }

    public boolean isShippingCounterEmpty() {
        return driver.findElements(getShippingCounterElement()).isEmpty();
    }
}
