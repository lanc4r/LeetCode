package Problems.Medium;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。<br>
 * 
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：<br>
 * 
 * L C I R<br>
 * E T O E S I I G<br>
 * E D H N<br>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。<br>
 * 
 * 请你实现这个将字符串进行指定行数变换的函数：<br>
 * 
 * string convert(string s, int numRows);<br>
 * 示例 1:<br>
 * 
 * 输入: s = "LEETCODEISHIRING", numRows = 3<br>
 * 输出: "LCIRETOESIIGEDHN"<br>
 * 示例 2:<br>
 * 
 * 输入: s = "LEETCODEISHIRING", numRows = 4<br>
 * 输出: "LDREOEIIECIHNTSG"<br>
 * 解释:<br>
 * 
 * L D R<br>
 * E O E I I<br>
 * E C I H N<br>
 * T S G <br>
 */

public class ZigZagConversion {

    public static void main(String[] args) {

        String s = "AB";
        int numRows = 3;
        // convert(s, numRows);

        // 新思路测试：
        System.out.println("result Str: " + convertTest(s, numRows));

        // System.out.println("i: " + calcRowIndex(3, 4));

    }

    /**
     * 思路思路：<br>
     * 
     * 这里我的打算是创建一个 char[n][m] 的二维数组，n 能表示行，直接就对应参数 numRows<br>
     * 而 m 表示列，可以通过计算得出，这里的计算公式只适用于 numRows >= 3 的情况<br>
     * 
     * 标记：后期可以将 二维数组修改为 List <br>
     * 
     * 考虑要不要说一下这个东西，可以直接参考网易云笔记吧.
     * 
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {

        // int columeNum = calcColumnNum(s, numRows);

        // 生成二维字符数组开始赋值
        // char[][] charArr = new char[numRows][columeNum];

        // TODO：### 思考一下是不是 char[numRows][] 这样的情况也能满足，不用写列有多长吗？我觉得不可能！

        // 然后开始推赋值的逻辑.
        /**
         * 循环 s.length 的长度，分别通过通过当前的长度算出需要的 i 和 j，然后进行赋值
         */

        return null;
    }

    /**
     * 新思路：<br>
     * 
     * 变成了找规律，第一层 和 第 n 层，逻辑是一样的，只是 n 从 1 或是 n 开始罢了
     */
    public static String convertTest(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        if (s == null || s.length() == 1) {
            return s;
        }

        int len, flag, offset, first, second;
        len = s.length();
        offset = numRows * 2 - 2;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder reStringBuilder = new StringBuilder();

        // 由于第一层 和 最后一层的特殊性，这里先计算 第 2-(n-1) 层的数据，然后计算 第 1和n 层的数据
        // 找个机会把第一层写进来把
        for (int i = 0; i < numRows && i < len; i++) {
            // 初始化清空数组
            stringBuilder.setLength(0);

            flag = i;
            stringBuilder.append(s.charAt(flag));
            while (flag < len) {

                first = offset - i * 2;
                flag += first;
                if (flag >= len) {
                    break;
                }
                // 避免重复增加 flag 下标的值，这里需要判断first 是否为空的情况
                if (first > 0) {
                    stringBuilder.append(s.charAt(flag));
                }

                second = offset - (numRows - 1 - i) * 2;
                flag += second;
                if (flag >= len) {
                    break;
                }
                // 避免重复增加 flag 下标的值，这里需要判断first 是否为空的情况
                if (second > 0) {
                    stringBuilder.append(s.charAt(flag));
                }
            }
            reStringBuilder.append(stringBuilder);
            // System.out.println("第 " + (i + 1) + " 排的数据为：" +
            // stringBuilder.toString());
        }

        // TODO: ### 先试试写在一起，再试试分开的情况

        return reStringBuilder.toString();
    }

    /**
     * 计算横坐标
     * 
     * @return
     */
    public static int calcRowIndex(int index, int numRows) {
        int flag, offset, resNum, addFlag, i;
        // 每一次循环的个数
        flag = numRows * 2 - 2;
        // 每一次循环的偏移量
        offset = numRows - 1;
        // 把循环除完后剩下的个数
        resNum = index - (index / flag) * flag;

        // 然后通过剩下的个数来选择加个数来配横坐标
        addFlag = 0;
        if (resNum > 0) {
            addFlag = resNum - numRows <= 0 ? 0 : (resNum - numRows);
            i = (index / flag) * offset + addFlag;
        } else {
            i = (index / flag) * offset - 1;
        }

        // 最后计算横坐标
        // i = (index / flag) * offset + addFlag;
        return i;
    }

    public static int calcColumnNum(String s, int numRows) {
        int circleFlag, circleNum, len, resNum, offset;

        len = s.length();
        circleFlag = 2 * numRows - 2;
        circleNum = len / circleFlag;
        resNum = len - (circleFlag * circleNum);
        offset = 0;
        if (resNum > 0) {
            offset = resNum - numRows <= 0 ? 1 : (resNum - numRows) + 1;
        }

        // 计算列长度
        return (numRows - 1) * circleNum + offset;
    }
}
