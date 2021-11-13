package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.ExternalPage;
import at.ac.tuwien.inso.swtesten.lab.pages.RegistrationPage;
import at.ac.tuwien.inso.swtesten.lab.pages.SuccessPage;
import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import io.cucumber.java8.En;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class Definitions implements En {

	private WebDriver driver;
	private RegistrationPage registrationPage;
	private ExternalPage externalPage;
	private SuccessPage successPage;

	public Definitions() {

		Before(() -> {
			driver = SeleniumWebDriver.getDriver();
		});
		AfterStep( scenario -> {
			if(scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
			}
		});

		Given("I am on the registration page", () -> {
			registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
			registrationPage.visit();
		});

		When("I hover over the menu", () -> {
			registrationPage.hoverMenu();

		});
		When("I click the link labeled {string}", (String value) -> {
			if(value.length() > 0)
			externalPage = registrationPage.clickLink(value);
		});
		When("I enter a valid input", () -> {
			registrationPage.fillFirstname("Vorname");
			registrationPage.fillLastname("Nachname");
			registrationPage.fillBirthday("01/02/2000");
			registrationPage.setGender(RegistrationPage.Gender.Female);
			registrationPage.setStudent(true);
			registrationPage.setUniversity("Technische Universität Wien");
		});
		When("I enter {string} as a first name", (String value) -> {
			if(value.length() > 0)
			registrationPage.fillFirstname(value);
		});
		When("I enter {string} as a last name", (String value) -> {
			if(value.length() > 0)
			registrationPage.fillLastname(value);
		});
		When("I enter {string} as a birthday", (String value) -> {
			if(value.length() > 0)
			registrationPage.fillBirthday(value);
		});

		When("I select {string} as a gender", (String value) -> {
			if(value.length() > 0)
			if(value.equals("Unspecified")){
				registrationPage.setGender(RegistrationPage.Gender.Unspecified);
			} else if(value.equals("Female")){
				registrationPage.setGender(RegistrationPage.Gender.Female);
			} else if(value.equals("Male")){
				registrationPage.setGender(RegistrationPage.Gender.Male);
			}else{
				throw new Exception("UnsupportedGender");
			}
		});

		When("I select {string} as university", (String value) -> {
			if(value.length() > 0)
			registrationPage.setUniversity(value);
		});

		When("I acknowledge that I am a student {word}", (String yes) -> {
			registrationPage.setStudent(yes.equals("yes"));
		});

		When("I sign up", () -> {
			successPage = registrationPage.submit();
		});

		Then("I should be registered", () -> {
			assertTrue(successPage.wasSuccessful());
		});
		Then("the title of the page should be {string}", (String value) -> {
			assertEquals(value, externalPage.getTitle());
		});
		Then("there should be a validation error", () -> {
			assertTrue(registrationPage.isErroneous());
		});
		Then("the name should be {string}", (String value) -> {
			assertEquals(value, successPage.getName());
		});
		Then("the name not should be {string}", (String value) -> {
			assertNotEquals(value, successPage.getName());
		});
		Then("the birthday should be {string}", (String value) -> {
			assertEquals(value, successPage.getBirthday());
		});
		Then("the university should be {string}", (String value) -> {
			assertEquals(value, successPage.getUniversity());
		});
		Then("the acknowledgments should be {string}", (String value) -> {
			assertEquals(value, successPage.getAcknowledgments());
		});
		Then("the gender should be {string}", (String value) -> {
			assertEquals(value, successPage.getGender());
		});
	}
}
