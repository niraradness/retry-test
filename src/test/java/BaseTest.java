import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RetryExtension.class)
public class BaseTest {

    @AfterAll
    public static void tearDown() {
        RetryExtension.afterAll();
    }
}
