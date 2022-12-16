package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

/*
     https://www.programiz.com/dsa/quick-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     https://www.youtube.com/watch?v=COk73cpQbFQ
     Quick Sort - recursive divide & conquer algorithm where we
        1. divide the original array into multiple subarrays
            a. while dividing, elements less than the pivot should be on the left & elements greater than the pivots should be on the right
        2. recursively call, excluding the pivot index from future sorts
     Best - O(n*log(n)) -- occurs when the pivot index is always the middle element / near the middle element
     Worst - O(n^2) -- occurs when the pivot index is always the smallest or largest element
     Average - O(n*log(n))
     Space - O(n)
     Stable - No -- due to the nature of swapping & placing pivot
     In Place - Yes
 */

public class QuickSort {

    public static void quickSort(int[] nums, int low, int high){
        if (low < high){
            // Pick a pivot & sort accordingly. Return the pivots index
            int pivotIndex = partition(nums, low, high);

            // Recursively call algorithm on subarrays
            quickSort(nums, low, pivotIndex - 1); // we want to change the pivot, higher range is pivotIndex - 1
            quickSort(nums, pivotIndex + 1, high); // pivot is in the proper spot respective of the larger numbers, don't include
        }
    }

    private static int partition(int[] nums, int low, int high) {
        // Pick a pivot. Here, we just pick the last item in the array.
        int pivot = nums[high];

        // Sort items in respect to the pivot via 2 pointer comparison
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        // Move pivot to proper spot
        i++; // Pivot location should be one after the last smaller element
        swap(nums, i, high);

        // return pivot index
        return i;
    }

    public static void main(String[] args) {
        int[] numbers = generateRandomArray();

        System.out.println("Before:");
        printArray(numbers);

        quickSort(numbers, 0, numbers.length-1);

        System.out.println("\nAfter:");
        printArray(numbers);
    }
}
