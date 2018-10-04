package com.company.HW4_z1;

/**
 * Д.З. 4 Java Profi
 * 1. Создать три потока, каждый из которых выводит определённую букву (A, B и C) 5 раз
 * (порядок - ABCABCABC). Используйте wait/notify/notifyAll.
 */

public class Main  {

    public static void main(String[] args) {
        SinchMetods abc = new SinchMetods();
        new ThreadA(abc);
        new ThreadB(abc);
        new ThreadC(abc);
    }
}

