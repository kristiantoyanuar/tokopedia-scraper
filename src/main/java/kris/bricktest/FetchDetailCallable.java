package kris.bricktest;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlMeta;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FetchDetailCallable implements Callable<Product> {
	private Product product;

	public FetchDetailCallable(Product product) {
		this.product = product;
	}

	@Override
	public Product call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " perform fetch from " + this.product.getProductUrl());
		WebClient webClient = null;
		try {
			webClient = new WebClient();
			final HtmlPage page = webClient.getPage(
					"https://www.tokopedia.com/gudang-hp/xiaomi-redmi-9t-4-64-gb-garansi-resmi-abu-abu?whid=0");
			String description = ((HtmlDivision) page.getDocumentElement()
					.getByXPath("//div[@data-testid='lblPDPDescriptionProduk']").get(0)).asNormalizedText();
			this.product.setDescription(description);
			String rating = ((HtmlMeta) page.getDocumentElement().getByXPath("//meta[@itemprop='ratingValue']").get(0))
					.getAttribute("content");
			this.product.setRating(Float.parseFloat(rating));
			this.product.setDescription(description);

			System.out.println(Thread.currentThread().getName() + " is done");
			return this.product;
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			webClient.close();
		}
		return null;
	}

}
