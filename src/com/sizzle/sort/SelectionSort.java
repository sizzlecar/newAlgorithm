package com.sizzle.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2018/1/23.
 */
public class SelectionSort {

    /**
     * 选择排序
     * 在未排序序列中找到最小（大）元素，存放到未排序序列的起始位置在未排序序列中找到最小（大）元素，存放到未排序序列的起始位置
     * 1.从待排序的序列中找到最小或最大的元素放入序列的起始位
     * 2.从剩下的元素的中继续找次小或 次大的元素放入序列的次位
     * 3.重复步骤2
     * @param list
     */
    public static void selectSort(List list){
        for (int i = 0;i < list.size();i++){
            for(int j = i + 1;j < list.size();j++){
                if(((Integer)list.get(i)).intValue() > ((Integer)list.get(j)).intValue()){
                    Object temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);

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
        list.add(-2);
        SelectionSort.selectSort(list);
        System.out.println(list.toString());
    }



}
