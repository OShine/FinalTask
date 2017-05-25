package by.issoft;

import by.issoft.annotation.ExternalParameters;
import com.beust.jcommander.internal.Lists;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class BaseTest {

    @DataProvider(name = "fromCSV")
    public Object[][] provideTestData(final Method reflectMethod) throws IOException {
        ExternalParameters requestedParams = reflectMethod.getAnnotation(ExternalParameters.class);
        List<String> params = Arrays.asList(requestedParams.value());
        InputStream in = getClass().getResourceAsStream("/data.csv");
        List<List<String>> result = FileResource.provideData(in);

        int i = 0;
        List<Integer> columnIndexes = getRequiredColumns(result.get(0), params);
        result.remove(0);
        Object[][] data = new Object[result.size()][params.size()];
        for (List<String> f : result) {
            List<Object> row = Lists.newArrayList();
            f.forEach(line -> {
                if (columnIndexes.contains(f.indexOf(line))) {
                    row.add(line);
                }
            });
            data[i] = row.stream().toArray(String[]::new);
            i++;
        }
        return data;
    }


    private List<Integer> getRequiredColumns(List<String> firstFileLine, List<String> params) {
        List<Integer> result = Lists.newArrayList();
        firstFileLine.forEach(str -> {
            if (params.contains(str)) {
                result.add(firstFileLine.indexOf(str));
            }
        });
        return result;
    }
}
