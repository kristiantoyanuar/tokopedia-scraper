package kris.bricktest;

import java.math.BigDecimal;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {
	private String name;
	private String description;
	private String imageUrl;
	private String productUrl;
	private BigDecimal price;
	private Float rating;
	private String sellerName;

	public Product(WebElement webElement) {
		this.name = extractName(webElement);
		this.imageUrl = extractImageUrl(webElement);
		this.price = extractPrice(webElement);
		this.sellerName = extractSellerName(webElement);
		this.productUrl = extractProductUrl(webElement);
	}

	private String extractProductUrl(WebElement webElement) {
		try {
			return webElement.findElement(By.xpath(".//a")).getAttribute("href");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to extract product URL from " + webElement.toString());
		}
		return null;
	}

	private String extractSellerName(WebElement webElement) {
		try {
			return webElement.findElements(By.xpath(".//span[@class='css-1kr22w3']")).get(1).getAttribute("innerHTML");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to extract name from " + webElement.toString());
		}
		return null;
	}

	private BigDecimal extractPrice(WebElement webElement) {
		try {
			String priceString = webElement.findElement(By.xpath(".//span[@class='css-o5uqvq']")).getText();
			priceString = priceString.replaceAll("Rp", "").replaceAll("\\.", "").replaceAll(" ", "");

			return new BigDecimal(priceString);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to extract price from " + webElement.toString());
		}
		return null;
	}

	private String extractName(WebElement webElement) {
		try {
			return webElement.findElement(By.xpath(".//span[@class='css-1bjwylw']")).getText();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to extract name from " + webElement.toString());
		}
		return null;
	}

	private String extractImageUrl(WebElement webElement) {
		try {
			return webElement.findElement(By.xpath(".//img")).getAttribute("src");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to extract image URL from " + webElement.toString());
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Override
	public String toString() {
		return '[' + this.name + ", " + this.rating + ", " + this.sellerName + ", " + this.price + ", "
				+ this.productUrl + ", " + this.imageUrl + ", " + this.description + ']';
	}

	public boolean isPromoted() {
		return this.productUrl != null && this.productUrl.startsWith("https://ta.tokopedia.com/promo/");
	}

	public String toCommaString() {
		return String.join(",", Objects.toString(this.name, ""), Objects.toString(this.price, ""),
				Objects.toString(this.rating, ""), Objects.toString(this.sellerName, ""),
				Objects.toString(this.sellerName, ""), Objects.toString(this.price, ""),
				Objects.toString(this.imageUrl, ""),
				"\"" + Objects.toString(this.description, "").replace("\n", "").replace("\r", ""));
	}
}
