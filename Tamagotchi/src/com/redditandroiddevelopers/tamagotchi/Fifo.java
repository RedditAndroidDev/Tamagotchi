
package com.redditandroiddevelopers.tamagotchi;

public class Fifo {
    private Object[] object;
    private int size = 0;

    public Fifo() {
        object = new Object[1];
    }

    public void add(Object ob) {
        if (object.length == size) {
            Object[] temp = new Object[size + 1];
            System.arraycopy(object, 0, temp, 0, size);
            object = temp;
            temp = null;
        }
        object[size] = ob;
        size++;
    }

    public Object show() {
        if (size > 0)
            return object[0];
        return null;
    }

    public void removeFirst() {
        if (size > 0) {
            size--;
            System.arraycopy(object, 1, object, 0, object.length - 1);
        }
    }

    public Object get() {
        if (size > 0) {
            Object temp;
            temp = object[0];
            size--;
            System.arraycopy(object, 1, object, 0, object.length - 1);
            return temp;
        }
        return null;
    }

    public void clear() {
        object = new Object[1];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size <= 0);
    }

}
