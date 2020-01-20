package com.company.task8;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;

public class Main {
    public static void main(String[] args) {
        CyclicItem ci1 = new CyclicItemImpl(1,2);
        CyclicItem ci2 = new CyclicItemImpl(3,4);

        CyclicCollection cc = new CyclicCollectionImpl();
        cc.add(ci1);
        cc.add(ci2);
        cc.remove(ci2);
        System.out.println(cc.size());
        System.out.println(cc.getFirst().toString());
    }
}
