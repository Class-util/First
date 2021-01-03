package Demo12;

import org.w3c.dom.Node;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 01 03
 * Time:15:51
 */
public class Test03 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(4);

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);

//        node1.next = node2;
//        node2.next = node3;

        node4.next = node5;
        node5.next = node6;

        ListNode cur = mergeTwoLists(node1, node4);
        while (cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode pre = head;

        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
