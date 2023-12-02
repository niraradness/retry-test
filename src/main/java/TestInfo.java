public class TestInfo {
    private final Class<?> testClass;
    private final java.lang.reflect.Method testMethod;

    public TestInfo(Class<?> testClass, java.lang.reflect.Method testMethod) {
        this.testClass = testClass;
        this.testMethod = testMethod;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public java.lang.reflect.Method getTestMethod() {
        return testMethod;
    }
}