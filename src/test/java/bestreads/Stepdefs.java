package bestreads;

import bestreads.main.Main;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs {
    Main main;

    @Given("program is ran")
    public void mainIsInitialized() {
        main = new Main();
    }

    @When("cucumber is tested")
    public void mainIsRan() {
        main.testMethod();
    }

    @Then("the program should say {string}")
    public void testValueShouldBe(String value) {
        assertEquals(value, main.cucumberTestValue);
    }

}
