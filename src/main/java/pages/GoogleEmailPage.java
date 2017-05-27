package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class GoogleEmailPage {

    private static final By GMAIL_LABEL = By.xpath(".//*[@id=':i']/span");
    private static final By LOGIN_SPAN = By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a");
    private static final By LOGOUT_BUTTON = By.xpath(".//*[@id='gb_71']");
    private static final By WRITE_MESSAGE_BUTTON = By.xpath("//div[contains(text(),'НАПИСАТЬ')]");
    private static final By TO_MESSAGE_FIELD = By.xpath(".//textarea[contains(@aria-label, 'Кому')]");
    private static final By THEME_MESSAGE_FIELD = By.name("subjectbox");
    private static final By SEND_MESSAGE_BUTTON = By.xpath("//div[text()='Отправить']");
    private static final By LOADING = By.xpath("//div[contains(text(),'Письмо')]");
    private static final By EMAIL_THEME_TEXT = By.xpath("//div [@class='y6']/span[contains(.,'New Test Message')]");
    private static final By MARK_AS_READ_TEXT = By.xpath("//div[contains(text(),'Mark as read')]");
    private static final String SECOND_USER_LOGIN = "seleniumtests30@gmail.com";
    private static final String THEME = "New Test Message";

    private WebDriver driver;

    public GoogleEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLabelText() {
        WebElement label = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(GMAIL_LABEL));
        return label.getText();
    }

    public void clickLoginSpanButton() {
        WebElement login_span = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(LOGIN_SPAN));
        login_span.click();
    }

    public void clickLogoutButton() {
        WebElement logout_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(LOGOUT_BUTTON));
        logout_button.click();
    }

    public void logout() {
        clickLoginSpanButton();
        clickLogoutButton();
    }

    public void clickWriteMessageButton() {
        WebElement write_message = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(WRITE_MESSAGE_BUTTON));
        write_message.click();
    }

    public void enterToMessage() {
        WebElement to_message_field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(TO_MESSAGE_FIELD));
        to_message_field.clear();
        to_message_field.sendKeys(SECOND_USER_LOGIN);
    }

    public void enterThemeMessage() {
        WebElement theme_message_field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(THEME_MESSAGE_FIELD));
        theme_message_field.clear();
        theme_message_field.sendKeys(THEME);
    }

    public void clickSendMessageButton() {
        WebElement send_message_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(SEND_MESSAGE_BUTTON));
        send_message_button.click();
    }

    public void sendEmailMessage() {
        clickWriteMessageButton();
        enterToMessage();
        enterThemeMessage();
        clickSendMessageButton();

    }

    public boolean isElementPresent(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try
        {
            driver.findElement(LOADING);
            return true;
        }
        catch(NoSuchElementException e)
        {
            return false;
        }
        finally
        {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    public String checkMessageExistence() {
        WebElement message_is_exist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_THEME_TEXT));
        Actions builder = new Actions(driver);
        builder.contextClick(message_is_exist).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement message_is_exist_text = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(MARK_AS_READ_TEXT));
        return message_is_exist_text.getText();
    }

}