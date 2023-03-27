package me.tud;

public class Item {

    private final int id;
    private final int stackSize;
    private int count;

    public Item() {
        this(0, 0);
    }

    public Item(int tag) {
        this(tag % 100, tag / 100);
    }

    public Item(int id, int count) {
        if (id != 0 && id < 10)
            throw new IllegalArgumentException("The id has to be at least 10, id '" + id + "'");
        if (id != 0 && count < 1)
            throw new IllegalArgumentException("The item amount has to be at least 1, count '" + count + "'");
        this.id = id;
        this.stackSize = Math2.isPrime(id) ? 16 : 64;
        if (count > stackSize)
            throw new IllegalArgumentException("The item amount is more than the max stack of the item");
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void merge(Item other) {
        if (id != other.id)
            return;
        while (other.count > 0 && count < stackSize) {
            other.count--;
            count++;
        }
    }

    @Override
    public String toString() {
        return id == 0 ? "0" : count + "" + id;
    }

}
