package com.employee.maintenance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest implements Runnable {
	String s = "";
	public ThreadPoolExecutorTest(String s) {
		this.s = s;
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
            Runnable worker = new ThreadPoolExecutorTest("" + i);
            executor.execute(worker);
        }
		executor.shutdown();
		while (!executor.isTerminated()) {
        }
	}

	@Override
	public void run() {
		System.out.println(s);
		
	}

}
