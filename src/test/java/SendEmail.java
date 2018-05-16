import driver.Driver;
import org.openqa.selenium.NoSuchElementException;
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

    private GoogleEmailPage googleEmailPage;
    private GoogleSignInPage googleSignInPage;

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

    @BeforeClass
    public void Before()  {
        Driver.openHomePage();
    }

    @BeforeMethod
    public void BeforeMethod() {
        Driver.clearAllCookies();
        Driver.refreshThePage();
    }

    @Test(priority = 1)
    @Description("Verify the ability to send emails")
    public void sendEmailTest() throws NoSuchElementException {

        googleEmailPage = new GoogleEmailPage();
        googleSignInPage = new GoogleSignInPage();

        googleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(googleEmailPage.getLabelText(), MAIL_LABEL);
        googleEmailPage.sendEmailMessage();
        googleEmailPage.isSendElementPresent();
        googleEmailPage.logOut(); //

        Driver.clearAllCookies();
        Driver.refreshThePage();

        googleSignInPage.loginAs(SECOND_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(googleEmailPage.checkInComeMessageExistence(), MARK_AS_READ);
        googleEmailPage.logOut(); //
    }

    @Test(priority = 2)
    @Description("Verify that sent email appears in Sent Mail folder")
    public void checkOutComeFolderTest() throws NoSuchElementException {

        googleEmailPage = new GoogleEmailPage();
        googleSignInPage = new GoogleSignInPage();

        googleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        googleEmailPage.sendEmailMessage();
        googleEmailPage.isSendElementPresent();

        Driver.openPage(SENT_URL);
        Assert.assertEquals(googleEmailPage.checkOutComeMessageExistence(), MARK_AS_UNREAD);
        googleEmailPage.logOut(); //
    }

    @Test(priority = 3)
    @Description("Verify that deleted email is listed in Trash")
    public void checkTrashFolderTest() throws NoSuchElementException {

        googleEmailPage = new GoogleEmailPage();
        googleSignInPage = new GoogleSignInPage();

        googleSignInPage.loginAs(FIRST_USER_LOGIN, USER_PASSWORD);
        Assert.assertEquals(googleEmailPage.getLabelText(), MAIL_LABEL);

        Driver.openPage(SENT_URL);
        googleEmailPage.deleteMessageToTrashFolder();
        googleEmailPage.isDeleteElementPresent();

        Driver.openPage(TRASH_URL);
        Assert.assertEquals(googleEmailPage.checkDeletedMessageExistence(), DELETE_FOREVER);
        googleEmailPage.logOut(); //
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}