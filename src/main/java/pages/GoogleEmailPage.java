package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by DenisShklyannik on 26.03.2017.
 */
public class GoogleEmailPage {

    private static final By GMAIL_LABEL = By.xpath(".//*[@id=':i']/span");
    private static final By LOGIN_SPAN = By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a");
    private static final By LOGOUT_BUTTON = By.xpath(".//*[@id='gb_71']");

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

}