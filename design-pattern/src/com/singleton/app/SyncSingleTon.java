package com.singleton.app;

public class SyncSingleTon {

	private static SyncSingleTon instance = null;

	private SyncSingleTon() {
	}

	public static SyncSingleTon getInstance() {
		synchronized (SyncSingleTon.class) {
			if (instance == null)
				instance = new SyncSingleTon();
		}
		return instance;
	}
}
