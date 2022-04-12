package com.singleton.app;

public class Test {

	public static void main(String[] args) {

		/*
		 * EagerSingleTon s1 = EagerSingleTon.getInstance();
		 * 
		 * System.out.println(s1);
		 * 
		 * EagerSingleTon s2 = EagerSingleTon.getInstance();
		 * 
		 * System.out.println(s2);
		 * 
		 * System.out.println(s1 == s2);
		 */

		/*
		 * LazySingleTon l1 = LazySingleTon.getInstance(); LazySingleTon l2 =
		 * LazySingleTon.getInstance(); LazySingleTon l3 = LazySingleTon.getInstance();
		 * LazySingleTon l4 = LazySingleTon.getInstance(); LazySingleTon l5 =
		 * LazySingleTon.getInstance(); LazySingleTon l6 = LazySingleTon.getInstance();
		 * 
		 * System.out.println(l1); System.out.println(l2); System.out.println(l3);
		 * System.out.println(l4); System.out.println(l5); System.out.println(l6);
		 */
		
		
		SyncSingleTon l1 = SyncSingleTon.getInstance();
		SyncSingleTon l2 = SyncSingleTon.getInstance();
		SyncSingleTon l3 = SyncSingleTon.getInstance();
		SyncSingleTon l4 = SyncSingleTon.getInstance();
		SyncSingleTon l5 = SyncSingleTon.getInstance();
		SyncSingleTon l6 = SyncSingleTon.getInstance();

		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l3);
		System.out.println(l4);
		System.out.println(l5);
		System.out.println(l6);

	}

}
