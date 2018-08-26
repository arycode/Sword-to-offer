package com.test.base;

//二维数组中的查找
public class Test04 {
    public boolean ser(int[][] numbers,int key){
        int row = 0;
        int col = numbers.length-1;
        while (row<numbers.length&&col>=0){
            if(numbers[row][col]==key){
                return true;
            }else if(numbers[row][col]>key){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }
}
