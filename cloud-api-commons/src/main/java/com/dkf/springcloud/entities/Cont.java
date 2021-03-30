package com.dkf.springcloud.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 88460 on 2021/2/27.
 */
public class Cont {

        public static void main(String[] args) {
            int[] A = {1,2,3,4,5};
            int[] B = {1,2,2,3,4,5,5,6,7,7};
            int[] result = Cont.multiply(A, B);
            System.out.println(Arrays.toString(result));
        }
        public static int[] multiply(int[] A,int[] B) {
            if (A == null && B == null || A.length < 1 && B.length < 1) {
                return null;
            }
//        创建辅助集合，其实数组创建新的数组也行，new int[A.length+B.length]
            List<Integer> list = new ArrayList<>();
            int index = 0, p1 = 0, p2 = 0;
//        先将A或者B中的首个元素添加到list
//      这里其实得判断是否为null。
             list.add(A[p1++]);   //  int[] A = {1,2,3,4,5};  int[] B = {1,2,2,3,4,5,5,6,7,7};
//        当没有遍历完A和B中任意一个数组时执行
            while ( p1 < A.length && p2 < B.length ) {
//          拿到当前指针的最小值
                int tmp = A[p1] < B[p2] ? A[p1++] : B[p2++];
//            判断list中是否已经添加了此元素
                if (tmp > list.get(index)) {
                    list.add(tmp);
//                每次添加元素后指针后移
                    index++;
                }
            }
//        当B中元素添加完，只剩A中元素时
            while ( p1 < A.length ) {
                int tmp = A[p1++];
                if (tmp > list.get(index)) {
                    list.add(tmp);
                    index++;
                }
            }
//        当A中元素添加完，只剩B中元素时
            while ( p2 < B.length ) {
                int tmp = B[p2++];
                if (tmp > list.get(index)) {
                    list.add(tmp);
                    index++;
                }
            }
//        将list中元素重新移回到数组，如果刚开始创建的是数组，那么就不用这一步了
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;

        }
    }










