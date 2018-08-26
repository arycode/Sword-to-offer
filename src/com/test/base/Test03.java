package com.test.base;

import java.util.HashMap;

//数据组重复的数字
public class Test03 {
    public static void main(String[] args){
        int[] numbers = {2,3,1,0,2,5,3};
        dup(numbers);
    }

    public static boolean dup(int[] numbers){
        if(numbers==null||numbers.length==0){
            return false;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<numbers.length;i++){
            if(map.containsValue(numbers[i])){
                System.out.print(numbers[i]);
                return true;
            }else{
                map.put(i,numbers[i]);
            }

        }
        return false;
    }

    public boolean dup2(int[] numbers){
        if(numbers==null||numbers.length==0){
            return false;
        }
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] < 1 || numbers[i] > numbers.length-1)
                return false;
        }
        int low=1;
        int high=numbers.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            int count = 0;
            //对范围内的元素个数进行计数
            for(int i = 0; i < numbers.length; i++)
            {
                if(numbers[i] <= mid && numbers[i] >= low)
                    count++;
            }

            if(low == high)
            {
                if(count > 1) {

                    System.out.print(low);
                    return true;
                }
                else
                    break;
            }

            // 根据检测情况调整会出现重复的整数范围
            if(count > mid - low + 1)
                high = mid;
            else
                low = mid + 1;

        }

        return false;


    }

}
