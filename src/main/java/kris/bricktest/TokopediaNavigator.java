package kris.bricktest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TokopediaNavigator {
	private WebDriver driver;
	public static final String PRODUCT_CSS_CLASS_XPATH = "//div[@class = 'css-13l3l78 e1nlzfl10']/div";

	public TokopediaNavigator() {
		ChromeOptions options = new ChromeOptions();
		this.driver = new ChromeDriver(options);
	}

	public void openCategoryPage(Category category, int page) {
		driver.get(category.getUrl() + page);
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_CSS_CLASS_XPATH)));
	}

	public void scrollToBottom() {
		int i = 0;
		while (i < 10) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
			i++;
		}
	}

	public List<WebElement> findElements(By by) {
		return this.driver.findElements(by);
	}

	public void kill() {
		this.driver.close();
	}

}
