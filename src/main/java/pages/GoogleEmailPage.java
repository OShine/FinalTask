package pages;

import driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class GoogleEmailPage extends Page{

    private static final By GMAIL_LABEL = By.xpath(".//*[@id=':i']/span");
    private static final By LOGIN_SPAN = By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a");
    private static final By LOGOUT_BUTTON = By.xpath(".//*[@id='gb_71']");
    private static final By WRITE_MESSAGE_BUTTON = By.xpath("//div[contains(text(),'COMPOSE')]");
    private static final By TO_MESSAGE_FIELD = By.xpath(".//textarea[contains(@aria-label, 'To')]");
    private static final By THEME_MESSAGE_FIELD = By.name("subjectbox");
    private static final By SEND_MESSAGE_BUTTON = By.xpath("//div[text()='Send']");
    private static final By LOADING = By.xpath(".//*[@id='link_vsm']");
    private static final By CHAIN = By.xpath("//div/span[contains(text(),'The conversation has been moved to the Bin.')]");
    private static final By EMAIL_THEME_TEXT = By.xpath("//div [@class='y6']/span[contains(.,'New Test Message')]");
    private static final By MARK_AS_READ_TEXT = By.xpath("//div[contains(text(),'Mark as read')]");
    private static final By MARK_AS_UNREAD_TEXT = By.xpath("//div[contains(text(),'Mark as unread)]");
    private static final By DELETE_FOREVER_ACTION_TEXT = By.xpath("//div[contains(text(),'Delete forever')]");
    private static final By DELETE_ACTION_TEXT= By.xpath("//div[contains(text(),'Delete')]");
    private static final By CONFIRMATION_BUTTON = By.xpath(".//*[@name='ok']");
    private static final String TITLE = "Inbox";
    private static final String SECOND_USER_LOGIN = "seleniumtests30@gmail.com";
    private static final String THEME = "New Test Message";

    private WebDriver driver;

    public GoogleEmailPage() {
        super(TITLE);
        driver = Driver.getDriver().get();
    }

    public String getLabelText() {
        WebElement label = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(GMAIL_LABEL));
        return label.getText();
    }

    private void clickLoginSpanButton() {
        WebElement login_span = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(LOGIN_SPAN));
        login_span.click();
    }

    private void clickLogoutButton() {
        WebElement logout_button = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        logout_button.click();
    }

    public void logout() {
        clickLoginSpanButton();
        clickLogoutButton();
    }

    private void clickWriteMessageButton() {
        WebElement write_message = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(WRITE_MESSAGE_BUTTON));
        write_message.click();
    }

    private void enterToMessage() {
        WebElement to_message_field = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(TO_MESSAGE_FIELD));
        to_message_field.clear();
        to_message_field.sendKeys(SECOND_USER_LOGIN);
    }

    private void enterThemeMessage() {
        WebElement theme_message_field = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(THEME_MESSAGE_FIELD));
        theme_message_field.clear();
        theme_message_field.sendKeys(THEME);
    }

    private void clickSendMessageButton() {
        WebElement send_message_button = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(SEND_MESSAGE_BUTTON));
        send_message_button.click();
    }

    public void sendEmailMessage() {
        clickWriteMessageButton();
        enterToMessage();
        enterThemeMessage();
        clickSendMessageButton();

    }

    public boolean isSendElementPresent() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    public boolean isDeleteElementPresent() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try
        {
            driver.findElement(CHAIN);
            return true;
        }
        catch(NoSuchElementException e)
        {
            return false;
        }
        finally
        {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    public String checkInComeMessageExistence() {
        WebElement income_message_is_exist = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_THEME_TEXT));
        Actions builder = new Actions(driver);
        builder.contextClick(income_message_is_exist).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement income_message_is_exist_text = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(MARK_AS_READ_TEXT));
        return income_message_is_exist_text.getText();
    }

    public String checkOutComeMessageExistence() {
        WebElement outcome_message_is_exist = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_THEME_TEXT));
        Actions builder = new Actions(driver);
        builder.contextClick(outcome_message_is_exist).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement outcome_message_is_exist_text = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(MARK_AS_UNREAD_TEXT));
        return outcome_message_is_exist_text.getText();
    }

    public String checkDeletedMessageExistence() {
        WebElement delete_message_is_exist = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_THEME_TEXT));
        Actions builder = new Actions(driver);
        builder.contextClick(delete_message_is_exist).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement delete_message_is_exist_text = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(DELETE_FOREVER_ACTION_TEXT));
        return delete_message_is_exist_text.getText();
    }

    public void deleteMessageToTrashFolder() {
        WebElement delete_message_is_exist = (new WebDriverWait(driver, 500)).until(ExpectedConditions.presenceOfElementLocated(EMAIL_THEME_TEXT));
        Actions builder = new Actions(driver);
        builder.contextClick(delete_message_is_exist).sendKeys(Keys.ARROW_DOWN).perform();
        WebElement delete_message_delete_action_text = (new WebDriverWait(driver, 500)).until(ExpectedConditions.presenceOfElementLocated(DELETE_ACTION_TEXT));
        delete_message_delete_action_text.click();
        WebElement confirmation_button = (new WebDriverWait(driver, 500)).until(ExpectedConditions.presenceOfElementLocated(CONFIRMATION_BUTTON));
        confirmation_button.click();
        new WebDriverWait(driver, 3000).until(ExpectedConditions.invisibilityOfElementLocated(By.className("Kj-JD")));
    }

}