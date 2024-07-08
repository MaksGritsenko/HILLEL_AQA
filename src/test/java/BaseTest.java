import org.example.driver_manager.DriverManager;
import org.example.pageopject.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected HomePage homePage;
    private DriverManager driverManager;

    @BeforeClass
    public void setUp() {
        driverManager = new DriverManager();
        homePage = driverManager.openHomePageByUrl();
    }

    @AfterClass
    public void tearDown() {
        driverManager.quitDriver();
    }
}