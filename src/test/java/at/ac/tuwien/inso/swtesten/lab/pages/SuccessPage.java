package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SuccessPage extends PageObject {

	@FindBy(xpath = "")
	private WebElement successNotice;

	public SuccessPage(WebDriver driver) {
		super(driver);
	}

	public boolean wasSuccessful() {
		return driver.findElements(new By.ByXPath("//div[@class='callout success autoclose']")).size() == 1;
	}
}
