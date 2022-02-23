package bestreads;

public class Main {
    public String cucumberTestValue;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
        Main test = new Main();
        test.testMethod();
	    System.out.println(test.cucumberTestValue);

	    ReadingTips tips = new ReadingTips();

	    tips.addTip("http://foo.bar", "First tip");
	    tips.addTip("http://bar.baz", "Second tip");

	    System.out.println(tips);
    }

    //Just for setting up the testing will be deleted later
    public void testMethod() {
        this.cucumberTestValue = "Hello world!";
    }
}
