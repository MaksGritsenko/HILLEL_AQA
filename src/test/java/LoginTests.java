import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private static WebDriverWait webDriverWait;

    private static WebElement LOGIN_IMAGE;
    private static WebElement ALERT_MESSAGE;
    private static WebElement USERNAME_FIELD;
    private static WebElement CONFIRM_BUTTON;
    private static WebElement PASSWORD_FIELD;
    private static WebElement LOG_OUT_BUTTON;
    private static WebElement LOGIN_MAIN_TITLE;
    private static WebElement FORM_AUTHENTICATION_BUTTON;

    private static final String VALID_USERNAME = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://the-internet.herokuapp.com/");
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(testName = "Check login page is displayed", priority = 1)
    public void checkLoginPageIsDisplayed() {
        FORM_AUTHENTICATION_BUTTON = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@href='/login']"))));
        FORM_AUTHENTICATION_BUTTON.click();
        LOGIN_MAIN_TITLE = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Login Page']"))));
        LOGIN_IMAGE = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img"))));
        Assert.assertEquals(LOGIN_MAIN_TITLE.getText(), "Login Page", "Title is not correct");
        Assert.assertTrue(LOGIN_IMAGE.isDisplayed(), "Image is not displayed on page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login", "The URL does not match what was expected");
    }

    @Test(testName = "Check login with valid credentials", priority = 2)
    public void checkLoginWithValidCredentials() {
        USERNAME_FIELD = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        PASSWORD_FIELD = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        CONFIRM_BUTTON = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type = 'submit']"))));
        USERNAME_FIELD.sendKeys(VALID_USERNAME);
        PASSWORD_FIELD.sendKeys(VALID_PASSWORD);
        CONFIRM_BUTTON.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure", "The URL does not match what was expected");
        ALERT_MESSAGE = driver.findElement(By.id("flash"));
        LOG_OUT_BUTTON = driver.findElement(By.xpath("//a[@href = '/logout']"));
        Assert.assertTrue(ALERT_MESSAGE.getText().trim().contains("You logged into a secure area!"), "Success message is not correct");
        Assert.assertTrue(LOG_OUT_BUTTON.isDisplayed(), "LogOut button is not displayed");
        LOG_OUT_BUTTON.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login", "The URL does not match what was expected");
        ALERT_MESSAGE = driver.findElement(By.id("flash"));
        Assert.assertTrue(ALERT_MESSAGE.getText().trim().contains("You logged out of the secure area!"), "Success message is not correct");
    }

    @Test(testName = "Check login with invalid credentials", priority = 3, dataProvider = "invalid credentials")
    public void checkLoginWithInvalidCredentials(String username, String password) {
        USERNAME_FIELD = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        PASSWORD_FIELD = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        CONFIRM_BUTTON = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type = 'submit']"))));
        USERNAME_FIELD.sendKeys(username);
        PASSWORD_FIELD.sendKeys(password);
        CONFIRM_BUTTON.click();
        ALERT_MESSAGE = driver.findElement(By.id("flash"));
        System.out.println(ALERT_MESSAGE.getText().trim());
        Assert.assertTrue(ALERT_MESSAGE.getText().trim().contains("Your username is invalid!") || ALERT_MESSAGE.getText().trim().contains("Your password is invalid!"), "Alert message is not correct");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "invalid credentials")
    public Object[][] getTestData() {
        return new Object[][]{
                {"tomsmith", ""},
                {"", "SuperSecretPassword!"},
        };
    }
}
