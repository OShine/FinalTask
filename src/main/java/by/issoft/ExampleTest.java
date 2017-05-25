package by.issoft;

import by.issoft.annotation.ExternalParameters;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {
    @Test(dataProvider = "fromCSV")
    @ExternalParameters({ "parameter", "x", "y" })
    public void testShowCoordinates(String name, String x, String y) {
        System.out.println(String.format("Parameter '%s' -> x=%s ; y=%s", name, x, y));
    }


    @Test(dataProvider = "fromCSV")
    @ExternalParameters({ "parameter", "text" })
    public void testShowDetails(String name, String text) {
        System.out.println(String.format("Parameter '%s' -> text=%s", name, text));
    }
}
