package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Logout;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class GoogleSignInPage extends Page implements Logout {

    private static final By MAILBOX = By.xpath(".//*[@id='identifierId']");
    private static final By MAILBOX_NEXT_BUTTON = By.xpath(".//*[@id='identifierNext']/content/span");
    private static final By PASSWORD_PASSWORD = By.xpath(".//*[@id='password']/div[1]/div/div[1]/input");
    private static final By PASSWORD_NEXT_BUTTON = By.xpath(".//*[@id='passwordNext']");
    private static final By SPAN_TEXT = By.xpath(".//*[@id='passwordNext']/content/span");
    private static final String TITLE = "";

    private WebDriver driver;

    public GoogleSignInPage() {
        super(TITLE);
        driver = Driver.getDriver().get();
    }

    private void setLogin(String login) {

        WebElement mailbox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(MAILBOX));
        mailbox.clear();
        mailbox.sendKeys(login);
        WebElement mailbox_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(MAILBOX_NEXT_BUTTON));
        mailbox_button.click();
    }

    private void setPassword(String password) {
        WebElement set_password = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(PASSWORD_PASSWORD));
        set_password.sendKeys(password);
    }

    private void clickLoginButton() {
        WebElement password_next_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(PASSWORD_NEXT_BUTTON));
        password_next_button.click();
    }

    public void loginAs(String userName, String password) {
        setLogin(userName);
        setPassword(password);
        clickLoginButton();
    }
    public String getSpanText() {
        WebElement span_text = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(SPAN_TEXT));
        return span_text.getText();
    }

}