package biz.tugay.ctci;

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
        IntegerNode head = new IntegerNode(0);
        IntegerNode curr = head;

        int carry = 0;
        while (x != null || y != null || carry > 0) {
            int temp = 0;
            if (x != null) {
                temp += x.val;
                x = x.next;
            }
            if (y != null) {
                temp += y.val;
                y = y.next;
            }
            if (temp + carry > 10) {
                curr.val = (temp + carry) % 10;
                carry = (temp + carry) / 10;
            } else {
                curr.val = temp + carry;
                carry = 0;
            }
            if (x != null || y != null || carry > 0) {
                curr.next = new IntegerNode(0);
                curr = curr.next;
            }
        }

        return head;
    }
}

class Node {
    char val;
    Node next;

    Node(char val) {
        this.val = val;
    }
}

class IntegerNode {
    int val;
    IntegerNode next;

    public IntegerNode(int val) {
        this.val = val;
    }
}