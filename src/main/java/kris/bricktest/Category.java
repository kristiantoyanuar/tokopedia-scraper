package kris.bricktest;

public enum Category {
	HANDPHONE("https://www.tokopedia.com/p/handphone-tablet/handphone?page=");

	private String url;

	private Category(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public static boolean isExist(String category) {
		if (category == null) {
			return false;
		}
		
		return valueOf(category.toUpperCase()) != null;
	}
}
