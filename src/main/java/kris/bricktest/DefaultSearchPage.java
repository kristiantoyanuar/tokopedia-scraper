package kris.bricktest;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DefaultSearchPage implements SearchPage {
	private static final String PRODUCT_CSS_CLASS_XPATH = "//div[@class = 'css-13l3l78 e1nlzfl10']/div";
	private TokopediaNavigator tokpedNavigator;
	private Category category;

	public DefaultSearchPage(TokopediaNavigator tokpedNavigator, Category category) {
		this.tokpedNavigator = tokpedNavigator;
		this.category = category;
	}

	@Override
	public List<Product> top(int count) {
		List<Product> topProducts = new ArrayList<Product>();

		try {
			int page = 1;
			while (topProducts.size() < count) {
				tokpedNavigator.openCategoryPage(category, page);
				tokpedNavigator.scrollToBottom();
				List<WebElement> els = tokpedNavigator.findElements(By.xpath(PRODUCT_CSS_CLASS_XPATH));

				for (WebElement el : els) {
					if (topProducts.size() < count) {
						Product candidate = new Product(el);
						if (!candidate.isPromoted()) // we assume promoted product is not a "top" product
							topProducts.add(new Product(el));
					}
				}

				page++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return topProducts;
	}
}
