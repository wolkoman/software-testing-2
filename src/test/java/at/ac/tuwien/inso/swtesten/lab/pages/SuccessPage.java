package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends PageObject {

	@FindBy(xpath = "//*[@class='cell a_name']/span") private WebElement nameElement;
	@FindBy(xpath = "//*[@class='cell a_birthday']/span") private WebElement birthdayElement;
	@FindBy(xpath = "//*[@class='cell a_gender']/span") private WebElement genderElement;
	@FindBy(xpath = "//*[@class='cell a_acknowledgments']/span") private WebElement acknowledgmentsElement;
	@FindBy(xpath = "//*[@class='cell a_uni']/span") private WebElement uniElement;

	public SuccessPage(WebDriver driver) {
		super(driver);
	}

	public boolean wasSuccessful() {
		return driver.findElements(new By.ByXPath("//div[@class='callout success autoclose']")).size() == 1;
	}

	public String getName() {
		return nameElement.getText();
	}

	public String getBirthday() {
		return birthdayElement.getText();
	}

	public String getGender() {
		return genderElement.getText();
	}

	public String getUniversity() {
		return uniElement.getText();
	}

	public String getAcknowledgments() {
		return acknowledgmentsElement.getText();
	}
}
