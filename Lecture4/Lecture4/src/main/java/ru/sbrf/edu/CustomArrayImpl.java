package ru.sbrf.edu;

import java.util.Collection;

public class CustomArrayImpl implements CustomArray {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 1;

    public CustomArrayImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object item) {
        ensureCapacity(1);
        elements[size++] = item;
        return true;
    }

    @Override
    public boolean addAll(Object[] items) {
        if(items == null)
            throw new IllegalArgumentException("Parameter items is null");
        else if(items.length == 0)
            return false;

        ensureCapacity(items.length);
        System.arraycopy(items, 0, elements, size, items.length);
        size += items.length;
        return true;
    }

    @Override
    public boolean addAll(Collection items) {
        if(items == null)
            throw new IllegalArgumentException("Parameter items is null");
        else if(items.isEmpty())
            return false;

        Object[] itemsArray = items.toArray();
        ensureCapacity(itemsArray.length);
        System.arraycopy(itemsArray, 0, elements, size, itemsArray.length);
        size += itemsArray.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        checkIndex(index);
        if(items == null)
            throw new IllegalArgumentException("Parameter items is null");
        else if(items.length == 0)
            return false;

        ensureCapacity(items.length);
        // Сдвигаем элементы вправо, чтобы освободить место
        System.arraycopy(elements, index, elements, index + items.length, size - index);
        System.arraycopy(items, 0, elements, index, items.length);
        size += items.length;
        return true;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public Object set(int index, Object item) {
        checkIndex(index);
        Object old = elements[index];
        elements[index] = item;
        return old;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // очистка ссылки
    }

    @Override
    public boolean remove(Object item) {
        if (item == null)
            return false;
        int index = indexOf(item);
        if (index == -1){
            return false;
        }
        else {
            remove(index);
            return true;
        }
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i]))
                return i;
        }

        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount <= 0) return;
        int minCapacity = size + newElementsCount;
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(minCapacity, elements.length * 2);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public int getCapacity() {
        return elements.length;
    }

    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            Object temp = elements[i];
            elements[i] = elements[size - i - 1];
            elements[size - i - 1] = temp;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
