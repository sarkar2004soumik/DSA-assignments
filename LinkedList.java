class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedList {
    private ListNode head;
    private int size;

    // Inserts an element at the specified index.
    public void insert(int index, int val) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode newNode = new ListNode(val);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    // Deletes the element at the specified index.
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            ListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
        }
        size--;
    }

    // Returns the size of the linked list.
    public int size() {
        return size;
    }

    // Returns true if the linked list is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Helper method to get the node at the specified index.
    private ListNode getNode(int index) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // Rotates the linked list by k positions to the right.
    public void rotate(int k) {
        if (size <= 1 || k % size == 0) return;
        k = k % size;
        int stepsToNewHead = size - k - 1;
        ListNode newTail = getNode(stepsToNewHead);
        ListNode newHead = newTail.next;
        ListNode oldTail = getNode(size - 1);
        oldTail.next = head;
        head = newHead;
        newTail.next = null;
    }

    // Reverses the linked list.
    public void reverse() {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        head = prev;
    }

    // Appends an element to the end of the linked list.
    public void append(int val) {
        insert(size, val);
    }

    // Prepends an element to the beginning of the linked list.
    public void prepend(int val) {
        insert(0, val);
    }

    // Merges two linked lists into a single linked list.
    public void merge(LinkedList other) {
        ListNode tail = getNode(size - 1);
        tail.next = other.head;
        size += other.size;
    }

    // Interleaves two linked lists into a single linked list.
    public void interleave(LinkedList other) {
        ListNode current1 = head;
        ListNode current2 = other.head;
        while (current1 != null && current2 != null) {
            ListNode next1 = current1.next;
            ListNode next2 = current2.next;
            current1.next = current2;
            current2.next = next1;
            current1 = next1;
            current2 = next2;
        }
        if (current2 != null) {
            getNode(size - 1).next = current2;
            size += other.size - 1;
        }
    }

    // Returns the middle element of the linked list.
    public ListNode getMiddle() {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Returns the index of the first occurrence of the specified element in the linked list,
    // or -1 if the element is not found.
    public int indexOf(int val) {
        ListNode current = head;
        int index = 0;
        while (current != null) {
            if (current.val == val) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    // Splits the linked list into two linked lists at the specified index.
    public LinkedList[] split(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedList[] result = new LinkedList[2];
        result[0] = new LinkedList();
        result[1] = new LinkedList();
        ListNode current = head;
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex < index) {
                result[0].append(current.val);
            } else {
                result[1].append(current.val);
            }
            current = current.next;
            currentIndex++;
        }
        return result;
    }
}
