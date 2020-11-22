package com.coconut.Day20.HomeWork;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2020/11/22 20:22
 * File: HomeWork
 * Project: leetcode_cc
 */


public class HomeWork {
    // 144. 二叉树的前序遍历
    // 前序遍历：根节点——左子树——右子树的方式遍历这棵树
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }


    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // 二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
        // 整个遍历过程天然具有递归的性质,递归终止的条件为碰到空节点.
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        // 终止条件：空节点
        if (root == null) {
            return;
        }
        // 先遍历左子树
        inorder(root.left, res);
        // 将节点的值加入res，
        res.add(root.val);
        // 在递归调用右子树
        inorder(root.right, res);

    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        // 遍历s
        for (int i = 0; i < length; i++) {
            // c 为每次的字符，i为字符数组的索引
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;
    }

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
