package MemoryLeak;

import java.util.LinkedList;

public class list {
    static LinkedList<Object> list = new LinkedList<>();

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            Object o = new Object();
            list.add(o);
            o = null;
        }
    }
}
