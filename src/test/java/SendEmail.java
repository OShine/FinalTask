import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GoogleEmailPage;
import pages.GoogleSignInPage;
import ru.yandex.qatools.allure.annotations.Description;

/**
 * Created by DenisShklyannik on 27.05.2017.
 */
public class SendEmail {

    private static final String BASE_URL = "https://www.google.com/gmail/";
    private static final String MAIL_LABEL = "Gmail";
    private static final String FIRST_USER_LOGIN = "seleniumtests10@gmail.com";
    private static final String SECOND_USER_LOGIN = "seleniumtests30@gmail.com";
    private static final String USER_PASSWORD = "060788avavav";
    private static final String MARK_AS_READ = "Mark as read";
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

    @Test
    @Description("Verify the ability to send emails")
    public void sendEmailTest() throws InterruptedException, NoSuchElementException {

        GoogleSignInPage GoogleSignInPage = new GoogleSignInPage(driver);
        GoogleEmailPage GoogleEmailPage = new GoogleEmailPage(driver);

        GoogleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.getLabelText(), MAIL_LABEL);
        GoogleEmailPage.sendEmailMessage();
        GoogleEmailPage.isElementPresent(driver);
        GoogleEmailPage.logout();

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        GoogleSignInPage.loginAs(SECOND_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.getLabelText(), MAIL_LABEL);
        Assert.assertEquals(GoogleEmailPage.checkMessageExistence(), MARK_AS_READ);
    }

    @Test
    @Description("Verify that sent email appears in Sent Mail folder")
    public void sendEmailFolderTest() throws InterruptedException, NoSuchElementException {

        GoogleSignInPage GoogleSignInPage = new GoogleSignInPage(driver);
        GoogleEmailPage GoogleEmailPage = new GoogleEmailPage(driver);

        GoogleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.getLabelText(), MAIL_LABEL);
        GoogleEmailPage.sendEmailMessage();
        GoogleEmailPage.isElementPresent(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
