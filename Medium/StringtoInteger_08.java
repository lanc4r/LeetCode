package Problems.Medium;

/**
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；<br>
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 
 * 说明：
 * 
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，qing返回 INT_MAX
 * (231 − 1) 或 INT_MIN (−231) 。
 * 
 * 示例 1:
 * 
 * 输入: "42" <br>
 * 输出: 42 <br>
 * 
 * 
 * 示例 2:
 * 
 * 输入: " -42" <br>
 * 输出: -42 <br>
 * 解释: 第一个非空白字符为 '-', 它是一个负号。 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。 示例 3:
 * 
 * 输入: "4193 with words" <br>
 * 输出: 4193 <br>
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。 示例 4:
 * 
 * 输入: "words and 987" <br>
 * 输出: 0 <br>
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。 因此无法执行有效的转换。 示例 5:
 * 
 * 输入: "-91283472332" <br>
 * 输出: -2147483648<br>
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 因此返回 INT_MIN (−231) 。
 *
 */

public class StringtoInteger_08 {

    public static void main(String[] args) {

        String str = "wqerqwer123";

        // char c = '-';
        // System.out.println(c == '-');

        System.out.println("res: " + myAtoi(str));

        // System.out.println("resStr: " + getNumicStr(7, str));
        // System.out.println("Max: " + Integer.MAX_VALUE);
        // System.out.println("Min: " + Integer.MIN_VALUE);

    }

    /**
     * 妈个鸡，搞错了，难受得一匹...
     */

    /**
     * 思路思路：<br>
     * 
     * 1. 第一步使用 replaceAll() 替换掉所有的空格<br>
     * 2. 开始获取第一个字符 (数字字符、+、-) <br>
     * 3. 然后开始从第一个数字字符开始遍历，判断下一个字符是否是数字，直到判断不是数字 或是 位数超过 (Max|Min)的位数<br>
     * 4. 最后将字符转换成数字<br>
     * 
     * 多思考两个问题：<br>
     * 
     * 1. 如果 +、- 号后不是数字的话，这个情况需要返回 0<br>
     * 2. 为了提高效率应该在超过 多少位字符后直接 返回，因为 最大、最小值是固定的<br>
     * 3. 还有可能有 +sadfas1234 或者 sad+asdf-114234 或者 --++---1234 这样的操作，总之骚东西很多<br>
     * 4. 能够用正则表达式吗？
     */
    public static int myAtoi(String str) {

        if (str == null) {
            return 0;
        }

        // 1. 只替换前后的空格
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        /**
         * 从第一个字符串开始遍历，这里需要分情况讨论：<br>
         * 
         * 1. 遍历到 + 、- 号时候，这是需要记录当前下表位置，然后从当前下标开始遍历新的数字。<br>
         * 必须是 +、— 后面接上数字才能去遍历数字 (很重要！！) <br>
         * 
         * 2. 如果遍历到数字，就开始往后面无限延伸(其中写 Max 和 Min 的逻辑咯)<br>
         * 
         * 3. 最后需要根据正负返回不同的最大值
         * 
         */

        // 开始循环字符串
        int index, len;
        char operator = '+';
        // long tempRes = 0l;
        index = 0;
        len = str.length();

        while (index < len) {
            char tempChar = str.charAt(index);
            // 如果是数字直接开始退出循环，从我们定义好的方法中拿去结果字符串
            if (Character.isDigit(tempChar)) {
                break;
            }
            // 如果当前字符是 + | -，并且后一位字符是数字，此时记录操作符，然后退出循环去搜索数值字符串
            if ((tempChar == '+' || tempChar == '-') && index + 1 < len && Character.isDigit(str.charAt(index + 1))) {
                operator = tempChar;
                index++;
                break;
            }
            // 如果两个循环都没有进入，则表示是其余字符，我们只需要将 index+1 即可
            return 0;
        }
        // 拿去字符串逻辑，然后再进行什么空的判断啊巴拉巴拉的
        String numberStr = getNumicStr(index, str);
        if (numberStr.length() == 0) {
            return 0;
        }

        // 然后根据正负号判断
        if (operator == '+') {
            // 如果超过10位数直接
            if (numberStr.length() > 10) {
                return Integer.MAX_VALUE;
            }
            if (numberStr.length() == 10 && numberStr.compareTo("2147483647") >= 0) {
                return Integer.MAX_VALUE;
            } else {
                // 返回转换后的正值
                return Integer.parseInt(numberStr);
            }

        } else {
            if (numberStr.length() > 10) {
                return Integer.MIN_VALUE;
            }
            if (numberStr.length() == 10 && numberStr.compareTo("2147483648") >= 0) {
                return Integer.MIN_VALUE;
            } else {
                // 返回转换后的负值
                return Integer.parseInt(numberStr) * -1;
            }

        }
    }

    // 我决定先上正则表达式麻蛋的

    // 定义一个方法，从一个下标开始遍历，把连续的数值型的字符串取出来 (注意 Max 和 Min 的逻辑： 有 00001232 这样的情况真的可怕)
    public static String getNumicStr(int index, String str) {

        StringBuilder resultBuilder = new StringBuilder();
        int len = str.length();
        boolean flag = true;

        while (index < len) {
            if (!Character.isDigit(str.charAt(index))) {
                break;
            }
            // 这里不截取字符串0
            if (str.charAt(index) == '0' && flag) {
                index++;
                continue;
            }
            resultBuilder.append(str.charAt(index++));
            flag = false;
        }

        return resultBuilder.toString();
    }

    // 定义一个方法判断字符是否是字符串
    public static boolean isNumber() {
        return false;
    }

}
