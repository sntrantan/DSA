package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;

public class BubbleSort {

    public static void sort(int[] nums){

        for (int i = nums.length - 1; i >= 0; i--){
            int numSwaps = 0;
            for(int j=0; j<i; j++){
                if (nums[j] > nums[j+1]){
                    swap(nums, j, j+1);
                    numSwaps++;
                }
            }
            if (numSwaps == 0) break;
        }
    }

    public static void main(String[] args) {
        int[] numbers = generateRandomArray();

        System.out.println("Before:");
        printArray(numbers);

        sort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }


}
