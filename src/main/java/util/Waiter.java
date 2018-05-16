package util;

import driver.Driver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(Driver.getDriver().get(),10);
    }
}
