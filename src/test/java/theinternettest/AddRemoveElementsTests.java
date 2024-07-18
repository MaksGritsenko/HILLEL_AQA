package theinternettest;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.theinternet.pageopject.AddRemoveElementsPage;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static testdata.Url.ADD_REMOVE_ELEMENTS;
import static testdata.Url.HOME_URL;

public class AddRemoveElementsTests extends BaseTest {

    private int counter = 3;
    private List<WebElement> elements;
    private AddRemoveElementsPage addRemoveElementsPage;

    @Test(testName = "Check home page is opened", priority = 1)
    public void checkHomePageIsOpened() {
        homePage = driverManager.openHomePageByUrl();
        assertTrue(homePage.checkHomeTitleIsCorrect(), "Home page title is not correct");
        assertTrue(homePage.isUrlDisplayed(HOME_URL.getUrl()), "Home page is not displayed");
    }

    @Test(testName = "Check add and remove elements page is opened", priority = 2)
    public void checkAddAndRemoveElementsPageIsOpened() {
        addRemoveElementsPage = homePage.clickOnAddRemoveElementsButton();
        assertTrue(addRemoveElementsPage.isTitleCorrect(), "Add and Remove elements page title is not correct");
        assertTrue(addRemoveElementsPage.isUrlDisplayed(ADD_REMOVE_ELEMENTS.getUrl()), "Add and Remove elements url is not correct");
    }

    @Test(testName = "Check click on add button", priority = 3)
    public void checkClickOnAddButton() {
        addRemoveElementsPage.clickAddElement(counter);
        elements = addRemoveElementsPage.getRemoveButtonElements();
        System.out.println(elements.size());
        assertTrue(elements.size() == counter, "The number of buttons does not match the number of presses");
    }

    @Test(testName = "Check click on delete button", priority = 4)
    public void checkClickOnDeleteButton() {
        assertTrue(addRemoveElementsPage.clickOnDeleteButton(counter), "Delete button is displayed on page");
    }
}
