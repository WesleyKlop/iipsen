package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TempException extends Exception {

    @Override
    public void printStackTrace() {
        var stream = getClass().getResourceAsStream("/string/tempFile.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
