public class LinkedItemPile implements ItemPile {
    private Node top;
    private int size;

    public class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public LinkedItemPile() {
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int seeTop() {
        // Return the top item of the stack, or -1 if empty.
        int topItem = -1;
        if (this.size > 0) {
            topItem = top.data;
        }
        return topItem;
    }

    @Override
    public int takeFromPile() {
        int removed = -1;
        if (top != null) {
            removed = top.data;
            top = top.next;
            size--;
        }
        return removed;
    }

    @Override
    public int addToPile(int item) {
        int added = -1;
        if (item > 0) {
            Node newNode = new Node(item, top);
            this.top = newNode;
            size++;
            added = top.data;
        }
        return added;
    }

    @Override
    public int pushDown(int item) {
        Node prevNode = null;
        Node currentNode = top;

        // Loop through the stack until you find where the new item fits or reach the
        // end.
        while (currentNode != null && currentNode.data < item) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        Node newNode = new Node(item, currentNode); // Create a new node with the item.
        if (prevNode == null) {
            top = newNode;
        } else {
            prevNode.next = newNode;
        }
        size++;

        return newNode.data;
    }

    @Override
    public boolean isInIncreasingOrder() {
        boolean isIncreasing = true;
        if (top != null) {
            Node currentNode = top;
            // Loop until you reach the end of the stack or find the order is not
            // increasing.
            while (currentNode.next != null && currentNode != null & isIncreasing) {
                if (currentNode.data > currentNode.next.data) {
                    isIncreasing = false;
                }
                currentNode = currentNode.next;
            }
        } else {
            isIncreasing = false;
        }
        return isIncreasing;

    }

    @Override
    public String toString() {
        String list = "";
        Node temp = top;
        while (temp != null) {
            if (list.isEmpty()) {
                list += temp.data;
            } else {
                list = temp.data + ", " + list;
            }
            temp = temp.next;
        }

        return list;
    }
}
