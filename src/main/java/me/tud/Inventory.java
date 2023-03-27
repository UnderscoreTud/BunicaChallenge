package me.tud;

import java.util.*;

public class Inventory implements Iterable<Item> {

    private static final int ROW_SIZE = 9;
    private static final int INVENTORY_SIZE = ROW_SIZE * 3;

    private final Item[] items;
    private final int size;

    public Inventory(int size) {
        this.items = new Item[INVENTORY_SIZE * size];
        Arrays.fill(items, new Item());
        this.size = size;
    }

    public Item getSlot(int i) {
        return items[i];
    }

    public void setSlot(int i, Item item) {
        items[i] = item;
    }

    public void mergeItems() {
        Set<Integer> mergedItems = new HashSet<>();
        first: for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item.getId() == 0 || mergedItems.contains(item.getId()))
                continue;
            for (int j = i + 1; j < items.length; j++) {
                Item other = items[j];
                if (item.getId() == other.getId()) {
                    item.merge(other);
                    if (other.getCount() == 0) {
                        items[j] = new Item();
                    } else {
                        continue first;
                    }
                }
            }
            mergedItems.add(item.getId());
        }
    }

    public void sortItems() {
        Arrays.sort(items, (item1, item2) -> {
            if (item1.getId() == 0)
                return 1;
            if (item2.getId() == 0)
                return -1;
            return Integer.compare(item1.getId(), item2.getId());
        });
    }

    @Override
    public Iterator<Item> iterator() {
        return Arrays.stream(items).iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size);
        int slot = 0;
        for (int i = 0; i < size * 3; i++) {
            builder.append('\n');
            for (int j = 0; j < ROW_SIZE; j++)
                builder.append(items[slot++].toString()).append(' ');
        }
        return builder.toString();
    }

    public static Inventory readInventory(String string) {
        Scanner scanner = new Scanner(string);
        Inventory inventory = new Inventory(scanner.nextInt());
        int slot = 0;
        while (scanner.hasNext()) {
            for (int i = 0; i < 9; i++) {
                inventory.setSlot(slot++, new Item(scanner.nextInt()));
            }
        }
        return inventory;
    }

}
