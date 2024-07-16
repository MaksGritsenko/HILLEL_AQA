package theinternettest;

import base.BaseTest;
import org.theinternet.pageopject.LoginPage;
import org.theinternet.pageopject.SecurePage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static testdata.AlertMessage.*;
import static testdata.Url.*;
import static testdata.Users.INVALID_USER;
import static testdata.Users.VALID_USER;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private SecurePage securePage;

    @Test(testName = "Check home page is opened", priority = 1)
    public void checkHomePageIsOpened() {
        assertTrue(homePage.checkHomeTitleIsCorrect(), "Home page title is not correct");
        assertTrue(homePage.isUrlDisplayed(HOME_URL.getUrl()), "Home page is not displayed");
    }

    @Test(testName = "Check login page is opened", priority = 2)
    public void checkLoginPageIsDisplayed() {
        loginPage = homePage.clickOnFormAuthenticationButton();
        assertTrue(loginPage.checkLoginImageIsDisplayed(), "Login image is not displayed");
        assertTrue(loginPage.checkLoginMainTitleIsCorrect(), "Login main title is not correct");
        assertTrue(loginPage.isUrlDisplayed(LOGIN_URL.getUrl()), "The URL does not match what was expected");
    }

    @Test(testName = "Check login with valid credentials", priority = 3)
    public void checkLoginWithValidCredentials() {
        loginPage.setCredentials(VALID_USER.getUsername(), VALID_USER.getPassword());
        securePage = (SecurePage) loginPage.clickOnConfirmButton(false);
        assertTrue(securePage.isUrlDisplayed(SECURE_URL.getUrl()), "The URL does not match what was expected");
        Assert.assertEquals(securePage.getAlertMessage().checkAlertMessageIsCorrect(), SUCCESS_LOGIN_MESSAGE.getMessage(), "Success message is not correct");
        loginPage = securePage.clickOnLogOutButton();
        assertTrue(loginPage.checkLoginImageIsDisplayed(), "Login image is not displayed");
        assertTrue(loginPage.checkLoginMainTitleIsCorrect(), "Login main title is not correct");
        assertTrue(loginPage.isUrlDisplayed(LOGIN_URL.getUrl()), "The URL does not match what was expected");
        assertEquals(loginPage.getAlertMessage().checkAlertMessageIsCorrect(), SUCCESS_LOGOUT_MESSAGE.getMessage(), "Success message is not correct");
    }

    @Test(testName = "Check login with invalid credentials", priority = 4, dataProvider = "invalid credentials")
    public void checkLoginWithInvalidCredentials(String username, String password, String expectedMessage) {
        loginPage.setCredentials(username, password);
        loginPage.clickOnConfirmButton(true);
        assertEquals(loginPage.getAlertMessage().checkAlertMessageIsCorrect(), expectedMessage, "Alert message is not correct");
    }

    @DataProvider(name = "invalid credentials")
    public Object[][] getTestData() {
        return new Object[][]{
                {VALID_USER.getUsername(), INVALID_USER.getPassword(), WARNING_PASSWORD_MESSAGE.getMessage()},
                {INVALID_USER.getUsername(), VALID_USER.getPassword(), WARNING_USERNAME_MESSAGE.getMessage()},
        };
    }
}
