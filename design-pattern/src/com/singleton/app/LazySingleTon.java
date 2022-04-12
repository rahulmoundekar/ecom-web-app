package com.singleton.app;

public class LazySingleTon {

	private static LazySingleTon instance = null;

	private LazySingleTon() {
	}

	public static LazySingleTon getInstance() {
		if (instance == null)
			instance = new LazySingleTon();
		return instance;
	}
}
