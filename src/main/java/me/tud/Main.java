package me.tud;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = Inventory.readInventory("""
                2
                5332 1428 1059 3990 2638 1419 4539 3996 5486
                2342 2956 2860 3686 4333 5357 5056 1363 6095
                478 0 4493 1171 4898 116 783 287 4999
                511 3096 5933 1765 1143 6150 621 557 1034
                6136 541 6085 1289 323 5551 5674 2555 5945
                1470 5788 717 563 1774 925 5838 5972 4081
                """);
        inventory.mergeItems();
        inventory.sortItems();
        System.out.println(inventory);
    }

}
