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

import static org.example.testdata.AlertMessage.*;
import static org.example.testdata.Users.INVALID_USER;
import static org.example.testdata.Users.VALID_USER;

public class LoginTests {

    private WebDriver driver;
    private static WebDriverWait webDriverWait;

    private static WebElement loginImage;
    private static WebElement alertMessage;
    private static WebElement userNameField;
    private static WebElement confirmButton;
    private static WebElement passwordField;
    private static WebElement logOutButton;
    private static WebElement loginMainTitle;
    private static WebElement formAuthenticationButton;

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
        formAuthenticationButton = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@href='/login']"))));
        formAuthenticationButton.click();
        loginMainTitle = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Login Page']"))));
        loginImage = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img"))));
        Assert.assertEquals(loginMainTitle.getText(), "Login Page", "Title is not correct");
        Assert.assertTrue(loginImage.isDisplayed(), "Image is not displayed on page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login", "The URL does not match what was expected");
    }

    @Test(testName = "Check login with valid credentials", priority = 2)
    public void checkLoginWithValidCredentials() {
        userNameField = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        passwordField = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        confirmButton = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type = 'submit']"))));
        userNameField.sendKeys(VALID_USER.getUsername());
        passwordField.sendKeys(VALID_USER.getPassword());
        confirmButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure", "The URL does not match what was expected");
        alertMessage = driver.findElement(By.id("flash"));
        logOutButton = driver.findElement(By.xpath("//a[@href = '/logout']"));
        Assert.assertEquals(alertMessage.getText().trim(), SUCCESS_LOGIN_MESSAGE.getMessage(), "Success message is not correct");
        Assert.assertTrue(logOutButton.isDisplayed(), "LogOut button is not displayed");
        logOutButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login", "The URL does not match what was expected");
        alertMessage = driver.findElement(By.id("flash"));
        Assert.assertEquals(alertMessage.getText().trim(), SUCCESS_LOGOUT_MESSAGE.getMessage(), "Success message is not correct");
    }

    @Test(testName = "Check login with invalid credentials", priority = 3, dataProvider = "invalid credentials")
    public void checkLoginWithInvalidCredentials(String username, String password, String expectedMessage) {
        userNameField = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        passwordField = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        confirmButton = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type = 'submit']"))));
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmButton.click();
        alertMessage = driver.findElement(By.id("flash"));
        Assert.assertEquals(alertMessage.getText().trim(), expectedMessage, "Alert message is not correct");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "invalid credentials")
    public Object[][] getTestData() {
        return new Object[][]{
                {VALID_USER.getUsername(), INVALID_USER.getPassword(), WARNING_PASSWORD_MESSAGE.getMessage()},
                {INVALID_USER.getUsername(), VALID_USER.getPassword(), WARNING_USERNAME_MESSAGE.getMessage()},
        };
    }
}
