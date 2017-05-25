import by.issoft.BaseTest;
import by.issoft.annotation.ExternalParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EmailPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class LoginTest extends BaseTest{

    private static final String BASE_URL = "https://mail.ru";
    private static final String COMPOSE_BUTTON_TEXT = "Написать письмо";
    private WebDriver driver;
    private MainPage mainPage;
    private EmailPage emailPage;

    @BeforeClass
    public void Before() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test(dataProvider = "fromCSV")
    @ExternalParameters({ "login", "password" })
    public void loginTest(String getLogin, String getPassword) {

        mainPage = new MainPage(driver);
        emailPage = new EmailPage(driver);

        mainPage.loginAs(getLogin, getPassword);
        Assert.assertEquals(emailPage.getComposeButtonText(), COMPOSE_BUTTON_TEXT);
        emailPage.clickLogoutButton();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}