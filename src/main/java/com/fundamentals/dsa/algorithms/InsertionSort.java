package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.generateRandomArray;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

/*
     https://www.programiz.com/dsa/insertion-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Insertion Sort - algorithm where we try to insert a new element in a sorted partition each iteration
     Best - O(n)
     Worst - O(n^2)
     Average - O(n^2)
     Space - O(1)
     Stable - Yes
     In Place - Yes
 */

public class InsertionSort {
    public static void sort(int[] nums){
        for(int i=1; i<nums.length; i++){

            int key = nums[i];
            int j= i-1;

            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            // For descending order, change key<array[j] to key>array[j].
            while ( j >= 0 && key < nums[j]){
                nums[j+1] = nums[j];
                j--;
            }

            // Place key at after the element just smaller than it.
            nums[j+1] = key;
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
