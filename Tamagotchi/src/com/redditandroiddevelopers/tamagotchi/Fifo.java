
package com.redditandroiddevelopers.tamagotchi;

public class Fifo {
    private Object[] object;
    private int size = 0;

    /**
     * Fifo's creator, make space for one object on start
     */
    public Fifo() {
        object = new Object[3];
    }

    /**
     * Adds object to fifo
     * 
     * @param ob
     */
    public void add(Object ob) {
        if (object.length == size) {
            Object[] temp = new Object[size + 3];
            System.arraycopy(object, 0, temp, 0, size);
            object = temp;
            temp = null;
        }

        object[size] = ob;
        size++;
    }

    /*
     * Return first object without removing it
     */
    public Object show(int iterator) {
        if (size > 0 && iterator <=size && iterator >=0)
            return object[iterator];
        return null;
    }

    /**
     * Remove first object from list
     */
    public void removeFirst() {
        if (size > 0) {
            size--;
            System.arraycopy(object, 1, object, 0, object.length - 1);
        }
    }

    /**
     * Return and remove first object from list
     * 
     * @return
     */
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

    /**
     * Remove all object from list
     */
    public void clear() {
        object = new Object[1];
        size = 0;
    }

    /**
     * Return current size of fifo
     * 
     * @return
     */
    public int getSize() {
        return size;
    }

    /*
     * Return true if fifo is empty
     */
    public boolean isEmpty() {
        return (size <= 0);
    }

}
