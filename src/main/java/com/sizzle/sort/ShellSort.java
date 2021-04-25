package com.sizzle.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2018/1/22.
 *
 */
public class ShellSort {

    /**
     * 希尔排序,希尔排序，也称递减增量排序算法，是插入排序的一种高速而稳定的改进版本。
     * 希尔排序是先将整个待排序的记录序列分割成为若干子序列，
     * 分别进行直接插入排序，待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序
     *  平均时间复杂度         最好情况    最坏情况    空间复杂度
     *      O(nlog2n)       O(nlog2n)    O(nlog2n)      O(1)
     */
    public static void shellSort(List list){
        //设置步长，第一次去数组长度的一半
        for (int step = list.size()/2;step > 0;step /=2){//外层循环不断缩小步长
            for (int i  = step;i < list.size();i++){//取出分组后的一个数
                Object val = list.get(i);
                //按照步长取出另外一个数，进行比较，并进行交换
                //如果步长变为1则成为插入排序
                for (int j = i - step;j >= 0 && ((Integer)val).intValue() < ((Integer) list.get(j)).intValue();j -= step){
                    list.set(j+step,list.get(j));
                    list.set(j,val);
                }
            }
        }

    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(114);
        list.add(115);
        list.add(11);
        list.add(14);
        list.add(89);
        list.add(110);
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(7);
        list.add(23);
        list.add(0);
        ShellSort.shellSort(list);
        System.out.println(list.toString());

        System.out.println("text");
    }

}
