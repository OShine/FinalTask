package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class Driver {

    private static final String BASE_URL = "https://mail.google.com/";
    //private static final String BASE_URL = "https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
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


}
