package kris.bricktest;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class Top100PhoneTest {
	ChromeDriver chrome;
	TokopediaNavigator navigator;

	@Test
	void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		navigator = new TokopediaNavigator();
		List<Product> top100 = new DefaultSearchPage(navigator, Category.HANDPHONE).top(10);
		AsyncFetchDetails asyncFetchDetails = new AsyncFetchDetails(top100);
		asyncFetchDetails.run();
		new ResultExporter(asyncFetchDetails.getProducts()).toCSV(new File("result.csv"));
		navigator.kill();
	}

	@Test
	void testFetchDetail() throws Exception {
		Product p = Mockito.mock(Product.class);
		when(p.getProductUrl())
				.thenReturn("https://www.tokopedia.com/gudang-hp/xiaomi-redmi-9t-4-64-gb-garansi-resmi-abu-abu?whid=0");
		FetchDetailCallable r = new FetchDetailCallable(p);
		p = r.call();
	}

}
