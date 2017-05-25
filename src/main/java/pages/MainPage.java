package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class MainPage {

    private static final By MAILBOX_LOGIN = By.id("mailbox__login");
    private static final By MAILBOX_PASSWORD = By.id("mailbox__password");
    private static final By MAILBOX_AUTH_BUTTON = By.id("mailbox__auth__button");
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private void setLogin(String login) {
        driver.findElement(MAILBOX_LOGIN).clear();
        driver.findElement(MAILBOX_LOGIN).sendKeys(login);
    }

    private void setPassword(String password) {
        driver.findElement(MAILBOX_PASSWORD).sendKeys(password);
    }

    public String getAuthButtonText() {
        return driver.findElement(MAILBOX_AUTH_BUTTON).getAttribute("value");
    }

    private void clickLoginButton() {
        driver.findElement(MAILBOX_AUTH_BUTTON).click();
    }

    public void loginAs(String userName, String password) {
        setLogin(userName);
        setPassword(password);
        clickLoginButton();
    }
}