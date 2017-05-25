import by.issoft.BaseTest;
import by.issoft.annotation.ExternalParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EmailPage;
import pages.GEmailPage;
import pages.GMainPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class LogoutGTest extends BaseTest{

    private static final String BASE_URL = "https://www.google.com/gmail/";
    private static final String LOGIN = "seleniumtests10@gmail.com";
    private static final String PASSWORD = "060788avavav";
    private static final String GMAIL_LABEL = "Gmail";
    private static final String SPAN_TEXT = "NEXT";
    private WebDriver driver;
    private GMainPage mainPage;
    private GEmailPage emailPage;

    @BeforeClass
    public void Before() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void logoutTest() throws InterruptedException {

        mainPage = new GMainPage(driver);
        emailPage = new GEmailPage(driver);

        mainPage.loginAs(LOGIN, PASSWORD);
        Assert.assertEquals(emailPage.getLabelText(), GMAIL_LABEL);
        emailPage.logout();
        Assert.assertEquals(mainPage.getSpanText(), SPAN_TEXT, "Login field is not presented");
        mainPage.changeDefaultAccount();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}