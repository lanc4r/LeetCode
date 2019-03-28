package Problems.Medium;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad" <br>
 * 输出: "bab" <br>
 * 注意: "aba" 也是一个有效答案。<br>
 * 
 * 示例 2：
 * 
 * 输入: "cbbd" 输出: "bb"
 */

public class LongestPalindromicSubstring_05 {

    public static void main(String[] args) {

        // String str = "abcba";
        // System.out.println(isPalindromicSubstring(str));

        // String s = "abcba";
        // int start, end, flag, len;
        // flag = s.length();
        // len = s.length();
        // while (--flag > 0) {
        // for (start = 0, end = (start + flag); end < len; start++, end =
        // (start + flag)) {
        // System.out.println("str: " + s.substring(start, end + 1));
        // }
        // }

        String s = "absabsba";
        System.out.println("res: " + longestPalindrome(s));

    }

    /**
     * 思路一：<br>
     * 
     * 要求最长的回文字符串，一定是从最中间开始向两边延伸的，可以从中点开始一步步判断<br>
     * 具体的再想想<br>
     * 
     * 
     * 思路二(感觉好傻....)：<br>
     * 
     * 准备一个方法，判断传入的字符串是不是回文字符串<br>
     * 准备另一个方法，从一个字符串中返回 长度为 n 的字符串数组 <br>
     * 从字符串的长度 len 开始截取字符串，不断的让 len减一，然后靠着返回的字符数组的字符串进行判断<br>
     * 
     * 
     * 思路三:<br>
     * 
     * 是不是还存在着一种类似于滑动窗口的东西，靠着那个滑动的窗口来判断，其实和 思路二有点像，只是不用返回字符数组了<br>
     * 我们我们定义长度为 len 的滑动窗口，相当于放在字符串上滑动，然后比较窗口中的字符串是不是回文字符串，循环的话减小 len 的值就好
     */
    public static String longestPalindrome(String s) {

        if (s == null) {
            return "";
        }

        if (s.length() == 1) {
            return s;
        }

        /**
         * str = abcabac<br>
         * 第一次长度为 7, 判断不为 回文字符串.<br>
         * 第二次长度为 6，窗口开始滑动： abcaba、bcabac 都不为回文字符串<br>
         * 第三次长度为 5，窗口开始滑动：abcab、bcaba、cabac，找到回文字符串 cabac，程序运行终止。
         */
        int start, end, flag, len;
        String str;
        flag = s.length();
        len = s.length();
        while (--flag >= 0) {
            for (start = 0, end = (start + flag); end < len; start++, end = (start + flag)) {
                str = s.substring(start, end + 1);
                if (isPalindromicSubstring(str)) {
                    return str;
                }
            }
        }
        return "";
    }

    // 判断字符串是否是回文字符串
    public static boolean isPalindromicSubstring(String str) {
        if (str.length() == 1) {
            return true;
        }
        // 当字符长度大于1 的情况我们需要进行判断
        // 取中间的值，两个循环同时进行，左边逐位 +1，右边逐位 -1，比较是否相等
        /**
         * 3 / 2 = 1 ==> 0 | 2<br>
         * 4 / 2 = 2 ==> 0 1 | 2 3<br>
         * 5 / 2 = 2 ==> 0 1 | 3 4<br>
         * 6 / 2 = 3 ==> 0 1 2 | 3 4 5<br>
         * 7 / 2 = 3 ==> 0 1 2 | 4 5 6
         */
        // 看来 基数 和 偶数 需要分别对待才行呢~
        int left, mid, right, len;
        len = str.length();
        left = 0;
        right = len - 1;
        mid = len / 2;

        if (len % 2 == 0) {
            for (; left < mid && right >= mid; left++, right--) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
            }
        } else {
            for (; left < mid && right > mid; left++, right--) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
            }
        }

        return true;
    }

}
