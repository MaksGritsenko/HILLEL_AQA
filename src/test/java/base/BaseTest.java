package base;

import driver_manager.DriverManager;
import org.theinternet.pageopject.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import saucedemo.com.pageobject.AutorisationPage;

public class BaseTest {

    protected HomePage homePage;
    protected DriverManager driverManager;
    protected AutorisationPage autorisationPage;

    @BeforeClass
    public void setUp() {
        driverManager = new DriverManager();
    }

    @AfterClass
    public void tearDown() {
        driverManager.quitDriver();
    }
}