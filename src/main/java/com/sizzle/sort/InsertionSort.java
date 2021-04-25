package com.sizzle.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by test on 2018/1/22.
 * 插入排序算法
 */
public class InsertionSort {

    /**
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，依次与前面已经排序后的元素进行比较
     * 3.根据需求(降序或升序)和比较结果调整元素的位置
     * 4.重复步骤2-3
     * 平均时间复杂度(执行一个算法所花费的时间)：O(n*n)
     * 最好情况：O(n*n)
     * 最坏情况：O(n*n)
     * 空间复杂度：O(1)
     * @param list
     * @return
     */

    public static void insertSort(List list){
        for(int i = 0;i < list.size() - 1;i++){
            for(int j = i+1;j > 0;j--){
                if(((Integer)list.get(j)).intValue() >= ((Integer)list.get(j-1)).intValue()){
                    //升序排列
                }else {
                    //后一个值小于前一个值进行位置交换
                    Object temporary = list.get(j);
                    list.set(j,list.get(j-1));
                    list.set(j-1,temporary);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(45);
        list.add(23);
        list.add(19);
        list.add(78);
        list.add(0);
        list.add(-9);
        InsertionSort.insertSort(list);
        System.out.println(list);
        List<String> newList = new LinkedList<>();
        AtomicInteger integer = new AtomicInteger();
    }



}
