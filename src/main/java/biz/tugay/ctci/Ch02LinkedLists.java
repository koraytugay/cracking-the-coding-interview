package biz.tugay.ctci;

import java.util.BitSet;

import static java.util.Optional.ofNullable;

class Ch02LinkedLists {

    void removeDuplicatesInPlace(Node head) {
        Node anchor = head;
        Node curr;

        while ((curr = anchor) != null) {
            while (curr.next != null)
                if (curr.next.val == anchor.val)
                    curr.next = curr.next.next;
                else
                    curr = curr.next;
            anchor = anchor.next;
        }
    }

    Node returnKthLast(Node head, int k) {
        Node follow = head;
        int gap = 0;

        while ((head = head.next) != null)
            if (++gap > k)
                follow = follow.next;

        return follow;
    }

    void deleteMiddleNode(Node middleNode) {
        while (middleNode.next != null) {
            middleNode.val = middleNode.next.val;
            middleNode = middleNode.next;

            if (middleNode.next.next == null)
                middleNode.next = null;
        }
    }

    Node partition(Node node, char partition) {
        Node leftHead = null;
        Node leftCurr = null;

        Node rightHead = null;
        Node rightCurr = null;

        while (node != null) {
            Node nodeNext = node.next;
            node.next = null;

            if (node.val < partition) {
                if (leftHead == null) {
                    leftHead = node;
                    leftCurr = node;
                } else {
                    leftCurr.next = node;
                    leftCurr = node;
                }
            } else {
                if (rightHead == null) {
                    rightHead = node;
                    rightCurr = node;
                } else {
                    rightCurr.next = node;
                    rightCurr = node;
                }
            }

            node = nodeNext;
        }

        if (leftHead == null)
            return rightHead;

        if (rightHead == null)
            return leftHead;

        leftCurr.next = rightHead;
        return leftHead;
    }

    IntegerNode sumLists(IntegerNode x, IntegerNode y) {
        return sumListsRecursive(x, y, 0);
    }

    boolean isPalindrome(Node node) {
        BitSet bitSet = new BitSet();

        while (node != null) {
            bitSet.flip(node.val);
            node = node.next;
        }

        return bitSet.nextSetBit(0) == -1;
    }

    private IntegerNode sumListsRecursive(IntegerNode x, IntegerNode y, int carry) {
        if (x == null && y == null && carry == 0)
            return null;

        IntegerNode node = new IntegerNode(carry);
        carry = 0;

        node.val += ofNullable(x).orElse(new IntegerNode(0)).val + ofNullable(y).orElse(new IntegerNode(0)).val;

        if (node.val > 10)
            carry = node.val / 10;

        node.val = node.val % 10;


        node.next = sumListsRecursive(ofNullable(x).orElse(new IntegerNode(0)).next, ofNullable(y).orElse(new IntegerNode(0)).next, carry);
        return node;
    }
}

class Node {
    char val;
    Node next;

    Node(char val) {
        this.val = val;
    }

    public Node(char val, Node next) {
        this.val = val;
        this.next = next;
    }
}

class IntegerNode {
    int val;
    IntegerNode next;

    public IntegerNode(int val) {
        this.val = val;
    }
}