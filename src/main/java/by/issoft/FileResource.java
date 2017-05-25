package by.issoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class FileResource {

    private static final String CSV_SEPARATOR = ",";

    public static List<List<String>> provideData(final InputStream is) throws IOException {
        List<List<String>> result = new ArrayList<>();
        List<String> row = null;
        String line = "";
        StringTokenizer token = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, CSV_SEPARATOR);
            row = new ArrayList<String>();
            while (token.hasMoreTokens()) {
                row.add(token.nextToken());
            }
            result.add(row);
        }
        br.close();
        return result;
    }
}
