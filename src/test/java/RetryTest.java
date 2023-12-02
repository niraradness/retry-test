import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RetryTest extends BaseTest {

    private static int fail = 0;

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        // I tried another approaches here, still the same result
    }
    @Test
    public void testPass() {
        System.out.println("Running testPass");
        Assertions.assertTrue(true);
    }

    @Test
    public void testPass2() {
        System.out.println("Running testPass2");
        Assertions.assertTrue(true);
    }

    @Test
    public void testFail() {
        fail += 1;
        System.out.println("Running testFail " + fail);
        if (getRandomBoolean()) {
            throw new RuntimeException("Simulating failure");
        }
    }

    @Test
    public void testFail2() {
//        fail += 1;
        System.out.println("Running testFail2 " + fail);
        if (getRandomBoolean()) {
            throw new RuntimeException("Simulating failure");
        }
    }

}
