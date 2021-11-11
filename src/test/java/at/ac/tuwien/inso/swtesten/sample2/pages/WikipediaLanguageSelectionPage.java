package at.ac.tuwien.inso.swtesten.sample2.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaLanguageSelectionPage extends PageObject {

	@FindBy(xpath = "//strong[text() = 'English']")
	private WebElement englishVersionLink;

	public WikipediaLanguageSelectionPage(WebDriver driver) {
		super(driver);
	}

	public void visit() {
		driver.get("http://www.wikipedia.org/");
	}

	public WikipediaMainPage selectLanguage() {
		englishVersionLink.click();
		return initPage(WikipediaMainPage.class);
	}
}
