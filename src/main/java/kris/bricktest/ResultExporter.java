package kris.bricktest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ResultExporter {
	private List<Product> products;

	public ResultExporter(List<Product> products) {
		super();
		this.products = products;
	}

	public void toCSV(File file) {
		if (this.products != null && this.products.isEmpty()) {
			System.out.println("Nothing to save, exited.");
			return;
		}

		OutputStream os = null;
		try {
			System.out.println("Writing to " + file.getAbsolutePath());
			os = new FileOutputStream(file);
			for (Product p : this.products) {
				String line = p.toCommaString() + getNewLine();
				os.write(line.getBytes());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	String getNewLine() {
		return System.getProperty("line.separator");
	}

}
