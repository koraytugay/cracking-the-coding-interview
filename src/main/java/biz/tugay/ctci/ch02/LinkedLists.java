package biz.tugay.ctci.ch02;

import biz.tugay.ctci.ch02.modal.IntegerNode;
import biz.tugay.ctci.ch02.modal.Node;

import java.util.*;

import static java.util.Optional.ofNullable;

class LinkedLists {

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

    Node partition(Node head, char partition) {
        Node leftHead = null;
        Node leftCurr = null;

        Node rightHead = null;
        Node rightCurr = null;

        while (head != null) {
            Node nodeNext = head.next;
            head.next = null;

            if (head.val < partition) {
                if (leftHead == null) {
                    leftHead = head;
                    leftCurr = head;
                } else {
                    leftCurr.next = head;
                    leftCurr = head;
                }
            } else {
                if (rightHead == null) {
                    rightHead = head;
                    rightCurr = head;
                } else {
                    rightCurr.next = head;
                    rightCurr = head;
                }
            }

            head = nodeNext;
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

    boolean isPalindrome(Node head) {
        BitSet bitSet = new BitSet();

        while (head != null) {
            bitSet.flip(head.val);
            head = head.next;
        }

        return bitSet.nextSetBit(0) == -1;
    }

    boolean intersecting(Node x, Node y) {
        Node anchor = y;

        while (x != null) {
            while (y != null) {
                if (x == y)
                    return true;
                y = y.next;
            }
            y = anchor.next;
            x = x.next;
        }

        return false;
    }

    boolean loopDetection(Node head) {
        Set<Node> visited = new HashSet<>();

        while (head != null) {
            if (!visited.add(head))
                return true;
            head = head.next;
        }

        return false;
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