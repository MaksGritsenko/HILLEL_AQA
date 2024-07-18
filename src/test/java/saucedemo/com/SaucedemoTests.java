package saucedemo.com;

import base.BaseTest;
import org.testng.annotations.Test;
import saucedemo.com.pageobject.InventoryPage;

import static org.testng.Assert.*;
import static testdata.Url.*;
import static testdata.Users.VALID_SAUCEDEMO_USER;

public class SaucedemoTests extends BaseTest {

    private InventoryPage inventoryPage;

    @Test(testName = "Check autorisation page is opened", priority = 1)
    public void sauceTest() {
        autorisationPage = driverManager.openAutorisationPageByUrl();
        assertTrue(autorisationPage.checkAutorisationMainTitleIsCorrect(), "Autorisation page title is not correct");
        assertTrue(autorisationPage.isUrlDisplayed(AUTORISATION_SAUCEDEMO_URL.getUrl()), "Autorisation page url is not correct");
    }

    @Test(testName = "Check login with valid credentials", priority = 2)
    public void checkLoginWithValidCredentials() {
        autorisationPage.setCredentials(VALID_SAUCEDEMO_USER.getUsername(), VALID_SAUCEDEMO_USER.getPassword());
        inventoryPage = autorisationPage.clickOnConfirmButton();
        assertTrue(inventoryPage.isTitleCorrect(), "Title is not correct");
        assertTrue(inventoryPage.isUrlDisplayed(INVENTORY_SAUCEDEMO_URL.getUrl()), "Inventory page url is not correct");
        assertTrue(inventoryPage.isAddToCartButtonNameCorrect(), "Add to cart button is not correct");
    }

    @Test(testName = "Check add to trash", priority = 3)
    public void checkAddToTrash() {
        inventoryPage.clickOnAddToCartButton();
        assertTrue(inventoryPage.isRemoveButtonNameCorrect(), "Remove button is not correct");
        assertTrue(inventoryPage.isShippingCounterDisplayed(), "Shipping counter is not displayed");
        assertEquals(inventoryPage.getShippingCounter(), 1, "Counter is not changed");
    }

    @Test(testName = "Check remove from trash", priority = 4)
    public void checkRemoveFromTrash() {
        inventoryPage.clickOnRemoveButton();
        assertTrue(inventoryPage.isAddToCartButtonNameCorrect(), "Add to cart button is not correct");
        assertTrue(inventoryPage.isShippingCounterEmpty(), "Shipping counter is not empty");
    }
}
