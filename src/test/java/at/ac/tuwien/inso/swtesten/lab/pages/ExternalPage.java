package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExternalPage extends PageObject {

	public ExternalPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}
}
