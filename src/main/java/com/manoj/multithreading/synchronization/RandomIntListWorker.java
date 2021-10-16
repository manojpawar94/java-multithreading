package com.manoj.multithreading.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RandomIntListWorker {

	private static final int UPPERBOUND = 100;
	private int size;
	private Random random = new Random();
	private List<Integer> list1;
	private List<Integer> list2;
	private Object randomIntList1Lock = new Object();
	private Object randomIntList2Lock = new Object();

	public RandomIntListWorker(int size) {
		this.size = size;
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
	}

	public void createList1() {
		synchronized (randomIntList1Lock) {
			for (int index = 0; index < size; index++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				list1.add(random.nextInt(UPPERBOUND));
			}
		}
	}

	public void createList2() {
		synchronized (randomIntList2Lock) {
			for (int index = 0; index < size; index++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				list2.add(random.nextInt(UPPERBOUND));
			}
		}
	}

	public void createParallelList() {
		var thread1 = new Thread(() -> createList1());
		var thread2 = new Thread(() -> createList2());
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * CountDownLatch helps to
	 */
	public void createParallelList2() {
		var latch = new CountDownLatch(2);
		new Thread(() -> {
			createList1();
			latch.countDown();
		}).start();
		new Thread(() -> {
			createList2();
			latch.countDown();
		}).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @return the list1
	 */
	public List<Integer> getList1() {
		return list1;
	}

	/**
	 * @return the list2
	 */
	public List<Integer> getList2() {
		return list2;
	}

}
