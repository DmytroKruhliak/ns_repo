package com.company.task8;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;

import java.io.Serializable;

public class CyclicCollectionImpl implements CyclicCollection, Serializable {

    private static final long serialVersionUID = 1L;
    private int size;
    private CyclicItem head;
    private CyclicItem tail;

    @Override
    public boolean add(CyclicItem item) { //addFirst like
        if (item == null) {
            throw new NullPointerException();
        }
        if (containsItem(item)) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            head = item;
            tail = item;
            tail.setNextItem(head);
            ;
        } else {
            addAfterTail(item);
        }
        size++;
        return true;
    }

    private void addAfterTail(CyclicItem item) {
        tail = item;
        tail.setNextItem(head);
    }

    @Override
    public void insertAfter(CyclicItem item, CyclicItem newItem) {
        if (item == null || newItem == null) {
            throw new NullPointerException();
        }
        if (!containsItem(item) || containsItem(newItem)) {
            throw new IllegalArgumentException();
        }
        if (item.equals(tail)) {
            addAfterTail(newItem);
        } else if (item.equals(head)) {
            addAfterHead(newItem);
        } else {
            addAfter(item, newItem);
        }
        size++;

    }

    private void addAfter(CyclicItem item, CyclicItem newItem) {
        CyclicItem current = head;
        for (int i = 0; i < size; i++, current = current.nextItem()) {
            newItem.setNextItem(current.nextItem());
            head.setNextItem(newItem); //mb current?
            break;
        }
    }

    private void addAfterHead(CyclicItem newItem) {
        newItem.setNextItem(head.nextItem());
        head.setNextItem(newItem);
    }

    @Override
    public CyclicItem getFirst() {
        return head;
    }

    @Override
    public boolean remove(CyclicItem item) {
        if (item == null) {
            throw new NullPointerException();
        } if (!containsItem(item)) {
            return false;
        }
        if (item.equals(head)) {
            removeHead();
        }else if (item.equals(tail)) {
            removeTail();
        } else {
            removeAfter(item);
        }
        return false;
    }

    private void removeAfter(CyclicItem item) {
        CyclicItem current = head;
        for (int i = 0; i < size; i++, current = current.nextItem()) {
            if (current.nextItem().equals(item)) {
                current.setNextItem(current.nextItem().nextItem());
                break;
            }
        }
    }

    private void removeHead() {
        if (size == 1) {
            head = head.nextItem(); // was = null
            tail.setNextItem(head); // was = nul
        } else {
            head = tail;
        }
    }

    private void removeTail() {
        CyclicItem current = head;
        for (int i = 0; i < size; i++, current = current.nextItem()) {
            if (current.nextItem().equals(tail)) {
//                current.setNextItem(head); //wtf?
                tail = current;
                tail.setNextItem(head);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    private boolean containsItem(CyclicItem item) {
        CyclicItem current = head;
        for (int i = 0; i < size; i++, current = current.nextItem()) {
            if (current.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
