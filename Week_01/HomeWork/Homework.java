package com.coconut.HomeWork;

import java.util.HashMap;
import java.util.Map;

public class Homework {
    // 加一
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    // 两数之和
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> hashtable = new HashMap<>(len - 1);
        hashtable.put(nums[0], 0);
        for (int i = 1; i < len; i++) {
            int another = target - nums[i];
            if (hashtable.containsKey(another)) {
                return new int[]{hashtable.get(another), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head){
        if (head ==null || head.next ==null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;

        return newHead;
    }
    // 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    // 盛最多水的容器
    public int maxArea0(int[] height){
        int len = height.length;
        int max = 0;
        for (int i = 0,j=len-1;i<j;){
            int minHeight = (height[i]<height[j]) ? height[i++]:height[j--];
            int area = (j-i +1)* minHeight;
            max = Math.max(area,max);
        }
        return max;
    }

}
