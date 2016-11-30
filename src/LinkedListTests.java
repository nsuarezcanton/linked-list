public class LinkedListTests {

    public static void main(String[] args) {
        Testing.setVerbose(true);
        System.out.println("Starting Tests");
        // Tests start here.

        testNodes();
        testLLConstructor();
        testSize();
        testGet();
        testSet();
        testAdd();
        testRemove();
        testToString();
        testFind();
        testRemoveItem();
        testInsertSorted();

        // Tests end here.
        System.out.println("Tests Complete");
    }

    private static void testNodes() {
        Testing.testSection("GenericListNode() tests");

        GenericListNode<String> nodeB = new GenericListNode<String>("B", null);
        GenericListNode<String> nodeA = new GenericListNode<String>("A", nodeB);

        Testing.assertEquals("nodeA is linked to nodeB", true,
                nodeA.link == nodeB);
        Testing.assertEquals("nodeB refers to null", true, nodeB.link == null);
    }

    private static void testLLConstructor() {
        Testing.testSection("GenericLinkedList() tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();

        Testing.assertEquals("listA has no items in it", 0, listA.size());
        Testing.assertEquals("listA is empty", "", listA.toString());
    }

    private static void testSize() {
        Testing.testSection("size() tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        Testing.assertEquals("list is empty", 0, listA.size());

        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");
        Testing.assertEquals("list has 4 items", 4, listA.size());
    }

    private static void testGet() {
        Testing.testSection("get(int index) tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");

        Testing.assertEquals("getting item @0 (firs item)", "A", listA.get(0));
        Testing.assertEquals("getting item @2 (middle item)", "B", listA.get(1));
        Testing.assertEquals("getting item @3 (last item)", "D", listA.get(3));

    }

    private static void testSet() {
        Testing.testSection("set(String value, int index) tests");
        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");

        listA.set("Z", 0);
        listA.set("X", 2);
        listA.set("Y", 3);
        Testing.assertEquals("set first element to 'Z'", "Z", listA.get(0));
        Testing.assertEquals("set a middle element to 'X'", "X", listA.get(2));
        Testing.assertEquals("set a last element to 'Y'", "Y", listA.get(3));
        Testing.assertEquals("toString output", "Z\nB\nX\nY",
                listA.toString());

    }

    private static void testAdd() {
        Testing.testSection("add(int index, T toAdd) tests");
        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.add(0, "A");
        Testing.assertEquals("adding to an empty list", "A", listA.toString());

        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");
        listA.add(4, "X");
        Testing.assertEquals("adding to last position ", "A\nB\nC\nD\nX",
                listA.toString());

        listA.add(2, "Y");
        Testing.assertEquals("adding in the middle ", "A\nB\nY\nC\nD\nX",
                listA.toString());

    }

    private static void testRemove() {
        Testing.testSection("remove(int index) tests");
        GenericLinkedList<String> listA = new GenericLinkedList<String>();

        listA.add(0, "A");
        String removed = listA.remove(0);
        Testing.assertEquals("removing only item", "", listA.toString());
        Testing.assertEquals("returns the removed string", "A", removed);

        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");
        removed = listA.remove(0);
        Testing.assertEquals("removing item @0", "B\nC\nD", listA.toString());
        Testing.assertEquals("returns the removed string", "A", removed);

        removed = listA.remove(1);

        Testing.assertEquals("removing item @1 (middle item)", "B\nD",
                listA.toString());
        Testing.assertEquals("returns the removed string", "C", removed);

        removed = listA.remove(1);
        Testing.assertEquals("removing item @1 (last item)", "B",
                listA.toString());

        Testing.assertEquals("returns the removed string", "D", removed);
    }

    private static void testToString() {
        Testing.testSection("toString() tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        Testing.assertEquals("list is empty (size = 0)", "", listA.toString());

        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");
        Testing.assertEquals("list not empty (size = 4)", "A\nB\nC\nD",
                listA.toString());
    }

    private static void testFind() {
        Testing.testSection("find(T toFind) tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");

        Testing.assertEquals("finds element @ 0", "A", listA.find("A"));
        Testing.assertEquals("finds element @ middle position", "C",
                listA.find("C"));
        Testing.assertEquals("finds element @ last index", "D", listA.find("D"));
    }

    private static void testRemoveItem() {
        Testing.testSection("remove(T toRemove) tests");

        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.add(0, "A");
        listA.add(1, "B");
        listA.add(2, "C");
        listA.add(3, "D");

        Testing.assertEquals("REMOVES element @ 0", "A", listA.remove("A"));
        Testing.assertEquals("REMOVES element @ middle position", "C", listA.remove("C"));
        Testing.assertEquals("REMOVES element @ 3 (last position)", "D", listA.remove("D"));
    }

    private static void testInsertSorted() {
        Testing.testSection("remove(T toRemove) tests");
        GenericLinkedList<String> listA = new GenericLinkedList<String>();
        listA.insertSorted("Beer");
        Testing.assertEquals("insertSorted() when list empty", "Beer", listA.toString());
        listA.add(1, "Drive");
        listA.add(2, "French");

        listA.insertSorted("Acorn");
        Testing.assertEquals("insertSorted() (non-empty) before first", "Acorn\nBeer\nDrive\nFrench", listA.toString());
        listA.insertSorted("Car");
        Testing.assertEquals("insertSorted() (non-empty) middle", "Acorn\nBeer\nCar\nDrive\nFrench", listA.toString());
        listA.insertSorted("Gray");
        Testing.assertEquals("insertSorted() (non-empty) after last", "Acorn\nBeer\nCar\nDrive\nFrench\nGray", listA.toString());
    }
}
