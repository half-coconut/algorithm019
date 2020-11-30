package com.coconut.Day28_H;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2020/11/30 12:01
 * File: HomeWork
 * Project: leetcode_cc
 */


public class HomeWork {
    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        // 1.方法一
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        // 递归
        dfs(n, k, 1, path, res);
        return res;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            // 在 Java 中，参数传递是 值传递，对象类型变量在传参的过程中，复制的是变量的地址。这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此会看到 66 个空的列表对象。解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点，注意这里是优化点（优化搜索上届，n-(k-path.size()+1做剪枝，优化算法）
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数里不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            used[i] = false;
            path.removeLast();

        }
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<String>();
        _generate(0, 0, n, "");
        return result;
    }

    private void _generate(int left, int right, int max, String s) {
        // left小于n，right小于left
        if (left == max && right == max) {
            result.add(s);
            return;
        }
        if (left < max)
            _generate(left + 1, right, max, s + "(");
        if (right < left)
            _generate(left, right + 1, max, s + ")");
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    // 1.暴力法，O(n),二分查找
    public int search(int[] nums, int target) {
        // 暴力法
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }

    // 2.二分查找[单调、边界、index]，mid不再用作判断中间值，而是用来判断单调性了。
    public int search2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            // 如果[0,mid]有序时，向后规约条件，注意这里target可以小于nums[0]，是因为数组旋转了，nums[0]并不一定是最小的。
            // ^ 异或
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low == high && nums[low] == target ? low : -1;
    }

    /**
     * 367. 有效的完全平方数
     *
     * @param num
     * @return
     */
    // 1.二分查找
    public boolean isPerfectSquare(int num) {
        long left = 0;
        long right = num;

        if (num == 0 && num == 1)
            return true;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid == num)
                return true;
            if (mid * mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left == right && left * left == num ? true : false;
    }

    //方法二： 1 4=1+3 9=1+3+5 16=1+3+5+7以此类推，模仿它可以使用一个while循环，不断减去一个从1开始不断增大的奇数，若最终减成了0，说明是完全平方数，否则，不是。
    public boolean isPerfectSquare2(int num) {
        int num1 = 1;
        while (num > 0) {
            num -= num1;
            num1 += 2;
        }
        return num == 0;
    }

    /**
     * 69. x 的平方根
     *
     * @param x
     * @return
     */
    // 1.方法一：二分查找
    // y = x^2 (x>0):抛物线 在y轴右侧单调递增；上下界
    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        long left = 1, right = x;
        long mid = 1;
        while (left <= right) {
            // 防止越界
            mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) right;
    }

    // 2. 牛顿迭代法
    public int mySqrt2(int x) {
        long r = x;
        while (r * r > x)
            r = (r + x / r) / 2;
        return (int) r;
    }
}
