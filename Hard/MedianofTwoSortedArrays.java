package Problems.Hard;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 <br>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 <br>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例1：<br>
 * nums1 = [1, 3] <br>
 * nums2 = [2]
 * 
 * 则中位数是 2.0
 * 
 * 示例2：<br>
 * 
 * nums1 = [1, 2]<br>
 * nums2 = [3, 4]<br>
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 */

public class MedianofTwoSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = { 1, 3 };
        int[] nums2 = { -4 };

        System.out.println(findMedianSortedArrays(nums1, nums2));

        // int len = nums1.length + nums2.length;
        // double res = 0;
        // int[] resArr = new int[len];
        // mergeGroup(nums1, nums2, resArr);
        // for (int i : resArr) {
        // System.out.println(i);
        // }
        // if (len % 2 == 0) {
        // res = (resArr[len / 2 - 1] + resArr[len / 2]) / 2;
        // } else {
        // res = resArr[len / 2];
        // }
        // System.out.println(res);

    }

    /**
     * 思考：中位数，这里分为基数和偶数情况。<br>
     * 如果是基数则直接取 中间那个数字<br>
     * 如果是偶数取中间两个数字，然后求一下平均值<br>
     * 
     * 解题思路1：先将两个数组合并为一个有序数组，然后再做
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len, len1, len2, i, j, k;
        i = 0;
        j = 0;
        k = 0;
        len1 = nums1.length;
        len2 = nums2.length;
        len = len1 + len2;
        int[] resArr = new int[len];

        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                resArr[k++] = nums1[i++];
            } else {
                resArr[k++] = nums2[j++];
            }
        }

        // 最后判断哪个数组指针没有指到最后，再进行赋值
        while (i < len1) {
            resArr[k++] = nums1[i++];
        }

        while (j < len2) {
            resArr[k++] = nums2[j++];
        }

        if (len % 2 == 0) {
            return (resArr[len / 2 - 1] + resArr[len / 2]) / 2.0;
        } else {
            return resArr[len / 2];
        }
    }

    /**
     * 先尝试合并两个有序数列:<br>
     * 循环两个数组，把当前指针最小的值赋值到结果数组 resArr 中，然后把赋值的数组 和 结果数组 指针向后移动一位<br>
     * 如果某个数组赋值完了，然后把剩余数组的数字放到 resArr 之后就好了
     */
    public static void mergeGroup(int[] arr1, int[] arr2, int[] resArr) {

        int len1, len2, i, j, k;
        i = 0;
        j = 0;
        k = 0;
        len1 = arr1.length;
        len2 = arr2.length;

        while (i < len1 && j < len2) {
            if (arr1[i] < arr2[j]) {
                resArr[k++] = arr1[i++];
            } else {
                resArr[k++] = arr2[j++];
            }
        }

        // 最后判断哪个数组指针没有指到最后，再进行赋值
        while (i < len1) {
            resArr[k++] = arr1[i++];
        }

        while (j < len2) {
            resArr[k++] = arr2[j++];
        }
    }

}
