package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends PageObject {

	@FindBy(xpath = "//input[@placeholder='First name']")
	private WebElement firstNameField;
	@FindBy(xpath = "//input[@placeholder='Last name']")
	private WebElement lastNameField;
	@FindBy(xpath = "//input[@placeholder='Birthday']")
	private WebElement birthdayField;
	@FindBy(xpath = "//legend[normalize-space()='Birthday*']")
	private WebElement birthdayLabel;
	@FindBy(xpath = "//input[@value='unspecified']")
	private WebElement genderUnspecifiedBox;
	@FindBy(xpath = "//input[@value='female']")
	private WebElement genderFemaleBox;
	@FindBy(xpath = "//input[@value='male']")
	private WebElement genderMaleBox;
	@FindBy(xpath = "//select[@name='uni']")
	private WebElement universitySelect;
	@FindBy(xpath = "//input[@name='student']")
	private WebElement studentCheckbox;
	@FindBy(xpath = "//button[@id='btn-submit']")
	private WebElement submitButton;
	@FindBy(xpath = "//li[@aria-label='Menu']")
	private WebElement menuButton;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void visit() {
		driver.get("http://localhost:8080/");
	}

	public void hoverMenu() {
		Actions actions = new Actions(driver);
		actions.moveToElement(menuButton);
		actions.click().build().perform();
	}

	public ExternalPage clickLink(String text) {
		driver.findElement(new By.ByXPath("//a[normalize-space()='"+text+"']")).click();
		return initPage(ExternalPage.class);
	}

	public String getLink() {
		return driver.getCurrentUrl();
	}

	public void fillFirstname(String value) {
		firstNameField.clear();
		firstNameField.sendKeys(value);
	}

	public void fillLastname(String value) {
		lastNameField.clear();
		lastNameField.sendKeys(value);
	}

	public void fillBirthday(String value) {
		birthdayField.clear();
		birthdayField.sendKeys(value);
		birthdayLabel.click();
	}

	public void setGender(Gender value) {
		if(value == Gender.Unspecified){
			genderUnspecifiedBox.click();
		} else if(value == Gender.Female){
			genderFemaleBox.click();
		} else if(value == Gender.Male){
			genderMaleBox.click();
		}
	}

	public void setUniversity(String value) {
		new Select(universitySelect).selectByVisibleText(value);
	}

	public void setStudent(boolean value) {
		if(studentCheckbox.isSelected() != value)
			studentCheckbox.click();
	}

	public SuccessPage submit() {
		submitButton.click();
		return initPage(SuccessPage.class);
	}

	public boolean isErroneous() {
		return driver.findElements(new By.ByXPath("//*[@data-abide-error]")).size() > 0;
	}

	public enum Gender {Unspecified, Female, Male}
}
