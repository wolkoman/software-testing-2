package at.ac.tuwien.inso.swtesten.util;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.concurrent.TimeUnit;

/**
 * Helper class providing general Selenium functionality
 */
public class SeleniumWebDriver {

	private static WebDriver webdriver = null;
	private static String defaultLocale = "en"; //use english as default

	public static WebDriver getDriver() {
		if (webdriver == null) {
			// Be aware that we recommend to use Chrome.
			webdriver = createChromeWebDriver();
//			webdriver = createFireFoxWebDriver();
		}

		webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webdriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		webdriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

		return webdriver;
	}

	private static WebDriver createChromeWebDriver() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		if (StringUtils.isNoneBlank(defaultLocale)) {
			options.setExperimentalOption("prefs", ImmutableMap.builder().put("intl.accept_languages", defaultLocale).build());
		}
		return new ChromeDriver(options);
	}

	private static WebDriver createFireFoxWebDriver() {
		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		if (StringUtils.isNoneBlank(defaultLocale)) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("intl.accept_languages", defaultLocale);
			options.setProfile(profile);
		}

		return new FirefoxDriver(options);
	}

	public static void closeDriver() {
		if(webdriver == null) {
			return;
		}

		try {
			webdriver.quit();
			webdriver = null;
		} catch (UnreachableBrowserException ignored) {
		}
	}

	public static void setDefaultLocale(String defaultLocale) {
		SeleniumWebDriver.defaultLocale = defaultLocale;
	}
}

