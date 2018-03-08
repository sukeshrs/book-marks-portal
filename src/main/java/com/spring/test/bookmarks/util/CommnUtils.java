package com.spring.test.bookmarks.util;

import org.springframework.stereotype.Component;


public class CommnUtils {

	public static void swapElements() {
		int i=10 , j=5;
		i=i+j;
		j=i-j;
		i=i-j;
		System.out.println("Swapped Values , i:" + i + "j :" + j);
	}
	
	public static void swapElementsUsingTemp() {
		int i=10 , j=5 ,k;
		k=i;
		i=j;
		j=k;
		System.out.println("Swapped Values , i:" + i + "j :" + j);
	}
	
	public static void main(String args[]) {
		swapElements();
		swapElementsUsingTemp();
		createNewThread();
	}

	private static void createNewThread() {
		
		Thread thread = new Thread(() ->System.out.println("Started"));
		thread.start();
		
	}
}
