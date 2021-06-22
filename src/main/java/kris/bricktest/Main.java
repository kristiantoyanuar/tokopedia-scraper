package kris.bricktest;

import java.io.File;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	TokopediaNavigator navigator;

	public static void main(String[] args) throws Exception {
		if (args.length < 2 || args.length > 3)
			showHelp();

		if (args.length > 0 && !Category.isExist(args[0])) {
			showHelp();
		}

		int defaultTop = 100;
		if (args.length == 3) {
			try {
				defaultTop = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				showHelp();
			}
		}
		Category c = Category.valueOf(args[0].toUpperCase());
		File outputFile = new File(args[1]);

		new Main().run(c, defaultTop, outputFile);
		System.exit(0);
	}

	private void run(Category category, int top, File outputFile) throws Exception {
		WebDriverManager.chromedriver().setup();
		navigator = new TokopediaNavigator();

		List<Product> topProducts = null;
		try {
			topProducts = new DefaultSearchPage(navigator, category).top(top);
		} catch (Exception e) {
			System.out.println("Unable to search from Tokopedia, error: " + e.getMessage());
			throw e;
		}

		AsyncFetchDetails asyncFetchDetails = new AsyncFetchDetails(topProducts);
		try {
			asyncFetchDetails.run();
			new ResultExporter(asyncFetchDetails.getProducts()).toCSV(outputFile);
		} catch (InterruptedException e) {
			System.out.println("Unable to save result: " + e.getMessage());
			throw e;
		}
		
		navigator.kill();
	}

	private static void showHelp() {
		print("Invalid arguments!");
		print("Usage: 	[category] [output-file]");
		print("         [category] [output-file] (count)");
		print("Options: ");
		print("         category: handphone");
		print("Example: handphone top100phone.csv");
		print("         handphone top10phone.csv 10");

		System.exit(1);
	}

	private static void print(String msg) {
		System.out.println(msg);
	}

}
