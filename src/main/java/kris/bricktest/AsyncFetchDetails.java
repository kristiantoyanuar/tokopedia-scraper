package kris.bricktest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncFetchDetails {
	private final int MAX_THREAD_POOL = 20;
	private final List<Product> products;

	public AsyncFetchDetails(List<Product> products) {
		this.products = products;
	}

	public void run() throws InterruptedException {
		ExecutorService executors = Executors.newFixedThreadPool(MAX_THREAD_POOL);
		List<FetchDetailCallable> fetchTasks = new ArrayList<FetchDetailCallable>();

		products.forEach(product -> {
			fetchTasks.add(new FetchDetailCallable(product));
		});

		List<Future<Product>> finalProducts = executors.invokeAll(fetchTasks);
		executors.shutdown();

		products.clear();
		finalProducts.forEach(f -> {
			try {
				products.add(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}

	public List<Product> getProducts() {
		return products;
	}

}
