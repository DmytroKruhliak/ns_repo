package com.company.task8;

import interfaces.task8.CyclicItem;

public class CyclicItemImpl implements CyclicItem {

    private static final long serialVersionUID = 1L;
    private transient Object temp;
    private Object value;
    private CyclicItem next = this; //interesting...

    public CyclicItemImpl() {
    }

    public CyclicItemImpl(Object value, Object temp) {
        this.value = value;
        this.temp = temp;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setTemp(Object temp) {
        this.temp = temp;
    }

    @Override
    public Object getTemp() {
        return temp;
    }

    @Override
    public CyclicItem nextItem() {
        return next;
    }

    @Override
    public void setNextItem(CyclicItem next) {
        this.next = next;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CyclicItemImpl other = (CyclicItemImpl) obj;
        if (value == null) {
            return other.value == null;
        } else return value.equals(other.value);
    }

}
