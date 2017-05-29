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

    private static final String BASE_URL = "https://mail.google.com/mail/";
    private static final String SENT_URL = BASE_URL + "u/0/#sent";
    private static final String TRASH_URL = BASE_URL + "/u/0/#trash";
    private static final String MAIL_LABEL = "Gmail";
    private static final String FIRST_USER_LOGIN = "seleniumtests10@gmail.com";
    private static final String SECOND_USER_LOGIN = "seleniumtests30@gmail.com";
    private static final String USER_PASSWORD = "060788avavav";
    private static final String MARK_AS_READ = "Mark as read";
    private static final String MARK_AS_UNREAD = "Отметить как непрочитанное";
    private static final String DELETE_FOREVER = "Удалить навсегда";
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

    @Test(priority = 1)
    @Description("Verify the ability to send emails")
    public void sendEmailTest() throws InterruptedException, NoSuchElementException {

        GoogleSignInPage GoogleSignInPage = new GoogleSignInPage(driver);
        GoogleEmailPage GoogleEmailPage = new GoogleEmailPage(driver);

        GoogleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.getLabelText(), MAIL_LABEL);
        GoogleEmailPage.sendEmailMessage();
        GoogleEmailPage.isSendElementPresent(driver);
        GoogleEmailPage.logout();

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        GoogleSignInPage.loginAs(SECOND_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.checkInComeMessageExistence(), MARK_AS_READ);
        GoogleEmailPage.logout();
    }

    @Test(priority = 2)
    @Description("Verify that sent email appears in Sent Mail folder")
    public void checkOutComeFolderTest() throws InterruptedException, NoSuchElementException {

        GoogleSignInPage GoogleSignInPage = new GoogleSignInPage(driver);
        GoogleEmailPage GoogleEmailPage = new GoogleEmailPage(driver);

        GoogleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        GoogleEmailPage.sendEmailMessage();
        GoogleEmailPage.isSendElementPresent(driver);

        driver.get(SENT_URL);
        Assert.assertEquals(GoogleEmailPage.checkOutComeMessageExistence(), MARK_AS_UNREAD);
        GoogleEmailPage.logout();
    }

    @Test(priority = 3)
    @Description("Verify that deleted email is listed in Trash")
    public void checkTrashFolderTest() throws InterruptedException, NoSuchElementException {

        GoogleSignInPage GoogleSignInPage = new GoogleSignInPage(driver);
        GoogleEmailPage GoogleEmailPage = new GoogleEmailPage(driver);

        GoogleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(GoogleEmailPage.getLabelText(), MAIL_LABEL);

        driver.get(SENT_URL);
        GoogleEmailPage.deleteMessageToTrashFolder();
        GoogleEmailPage.isDeleteElementPresent(driver);

        driver.get(TRASH_URL);
        Assert.assertEquals(GoogleEmailPage.checkDeletedMessageExistence(), DELETE_FOREVER);
        GoogleEmailPage.logout();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
