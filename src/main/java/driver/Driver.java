package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class Driver {

    private static final String BASE_URL = "https://mail.google.com/";
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void openHomePage() {
        getDriver().get().navigate().to(BASE_URL);
    }

    public static ThreadLocal<WebDriver> getDriver(){
        if (driver.get() == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
            WebDriver d = new ChromeDriver();
            d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            d.manage().window().maximize();

            driver.set(d);
        }

        return driver;
    }

    public static void closeDriver() {
        driver.get().close();
        driver.set(null);
    }

    public static void clearAllCookies() {
        getDriver().get().manage().deleteAllCookies();
    }

    public static void refreshThePage() {
        getDriver().get().navigate().refresh();
    }

    public static void openPage(String URL) {
        getDriver().get().navigate().to(URL);
    }

    public static void acceptAlert() {
        getDriver().get().switchTo().alert().accept();
    }
}
