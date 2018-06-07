import by.issoft.BaseTest;
import by.issoft.annotation.ExternalParameters;
import driver.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.GoogleEmailPage;
import pages.GoogleSignInPage;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class LoginAndLogout extends BaseTest{

    private GoogleEmailPage googleEmailPage;
    private GoogleSignInPage googleSignInPage;

    private static final String MAIL_LABEL = "Gmail";
    private static final String SPAN_TEXT = "NEXT";

    @BeforeClass
    public void Before()  {
        Driver.openHomePage();
    }

    @Test(dataProvider = "fromCSV")
    @ExternalParameters({ "login", "password" })
    public void loginAndLogoutTest(String getLogin, String getPassword) throws NoSuchElementException {

        googleEmailPage = new GoogleEmailPage();
        googleSignInPage = new GoogleSignInPage();

        googleSignInPage.loginAs(getLogin, getPassword);
        Assert.assertEquals(googleEmailPage.getLabelText(), MAIL_LABEL);
        googleEmailPage.logout();
        Assert.assertEquals(googleSignInPage.getSpanText(), SPAN_TEXT, "Login field is not presented");

    }

    @AfterMethod
    public void AfterMethod() {
        Driver.clearAllCookies();
        Driver.refreshThePage();
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}