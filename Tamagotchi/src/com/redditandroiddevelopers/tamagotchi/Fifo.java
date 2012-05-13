
package com.redditandroiddevelopers.tamagotchi;

import java.util.Arrays;

public class Fifo {
    private Object[] objects;
    private int size = 0;

    /**
     * Fifo's creator, make space for one objects on start
     */
    public Fifo() {
        objects = new Object[3];
    }

    /**
     * Adds objects to fifo
     * 
     * @param ob
     */
    public void add(Object ob) {
        ensureCapacity(size + 1);
        objects[size++] = ob;
    }

    /*
     * Return first objects without removing it
     */
    public Object show(int iterator) {
        if (size > 0 && iterator <=size && iterator >=0)
            return objects[iterator];
        return null;
    }

    /**
     * Remove first objects from list
     */
    public void removeFirst() {
        if (size > 0) {
            size--;
            System.arraycopy(objects, 1, objects, 0, objects.length - 1);
        }
    }

    /**
     * Return and remove first objects from list
     * 
     * @return
     */
    public Object get() {
        if (size > 0) {
            //If size is half the array + 1 then resize it
            int resize = (objects.length/2) + 1;
            if(--size < resize)  {
                objects = Arrays.copyOf(objects, Math.max(resize, size));
            }
            Object temp = objects[0];
            System.arraycopy(objects, 1, objects, 0, objects.length - 1);
            objects[size] = null; //For garbage collection
            return temp;
        }
        return null;
    }

    /**
     * Remove all objects from list
     */
    public void clear() {
        objects = new Object[1];
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

    /**
     * Similar to Java ArrayList, ensures that the object
     * has more than the minimum capacity
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = objects.length;
        if (minCapacity > oldCapacity) {
            Object[] oldData = objects;
            int newCapacity = (oldCapacity * 3)/2 + 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            // minCapacity is usually close to size, so this is a win:
            objects = Arrays.copyOf(objects, newCapacity);
        }
    }
}
