package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;

abstract class Page {

    private final String TITLE;
    private WebDriver driver;

    Page(String title) {
        TITLE = title;
        driver = Driver.getDriver().get();
    }

    public boolean isDisplayed() {
        return driver.getTitle().contains(TITLE);
    }
}
