package bestreads;

public class Main {

    public String cucumberTestValue;

    public static void main(String[] args) {
        Main test = new Main();
        test.testMethod();
        System.out.println(test.cucumberTestValue);
    }

    //Just for setting up the testing will be deleted later
    public void testMethod() {
        this.cucumberTestValue = "Hello world!";
    }
}
