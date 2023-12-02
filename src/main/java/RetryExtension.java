import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetryExtension implements AfterTestExecutionCallback {

    private static final List<TestInfo> failedTests = new ArrayList<>();
    private static final String CSV_FILE_PATH = "failed_tests.csv";

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            failedTests.add(new TestInfo(
                    context.getRequiredTestClass(),
                    context.getRequiredTestMethod()
            ));
        }
    }

    @AfterAll
    public static void afterAll() {
        // Generate CSV file with failed tests information
        generateCsvFile();
    }

    private static void generateCsvFile() {
        System.out.println("Generating " + CSV_FILE_PATH);
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            for (TestInfo testInfo : failedTests) {
                writer.write(testInfo.getTestClass().getName() + "#" + testInfo.getTestMethod().getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
