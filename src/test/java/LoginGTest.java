import by.issoft.BaseTest;
import by.issoft.annotation.ExternalParameters;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.GEmailPage;
import pages.GMainPage;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class LoginGTest extends BaseTest{

    private static final String BASE_URL = "https://www.google.com/gmail/";
    private static final String GMAIL_LABEL = "Gmail";
    private static final String SPAN_TEXT = "NEXT";
    private WebDriver driver;

    @BeforeClass
    public void Before() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @BeforeMethod
    public void BeforeMethod() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test(dataProvider = "fromCSV")
    @ExternalParameters({ "login", "password" })
    public void loginTest(String getLogin, String getPassword) throws InterruptedException, NoSuchElementException {

        GMainPage mainPage = new GMainPage(driver);
        GEmailPage emailPage = new GEmailPage(driver);

        mainPage.loginAs(getLogin, getPassword);
        Assert.assertEquals(emailPage.getLabelText(), GMAIL_LABEL);
        emailPage.logout();
        Assert.assertEquals(mainPage.getSpanText(), SPAN_TEXT, "Login field is not presented");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}