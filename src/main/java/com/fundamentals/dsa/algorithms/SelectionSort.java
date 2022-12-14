package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;

/*
     https://www.programiz.com/dsa/insertion-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Selection Sort - algorithm where we 'select' the lowest number and swap it with the i'th index each iteration
     Best - O(1/2 n^2) -> O(n^2)
     Worst - O(1/2 n^2) -> O(n^2)
     Average - O(1/2 n^2) -> O(n^2)
     Space - O(1)
     Stable - No
     In Place - Yes
 */

public class SelectionSort {
    public static void sort(int[] nums){
        for(int i=0; i<nums.length; i++){
            int minIndex = i;

            for(int j = i+1; j<nums.length; j++){
                if (nums[minIndex] > nums[j]) minIndex = j;
            }

            swap(nums, i, minIndex);
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
