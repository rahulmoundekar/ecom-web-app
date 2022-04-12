package com.singleton.app;

public class EagerSingleTon {

	private static EagerSingleTon instance = new EagerSingleTon();

	private EagerSingleTon() {
	}

	public static EagerSingleTon getInstance() {
		return instance;
	}
}
