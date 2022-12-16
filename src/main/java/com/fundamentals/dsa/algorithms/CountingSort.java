package com.fundamentals.dsa.algorithms;

import java.util.Arrays;

import static com.fundamentals.dsa.algorithms.ArrayHelper.generateRandomArray;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

public class CountingSort {

    /*
     https://www.programiz.com/dsa/counting-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Counting Sort - sorting algorithm
        1. Find the max & min of the array
        2. Create auxiliary space to count the occurrences of the nums
        3. Sort the input using the count tracked in the auxiliary space.
     Best - O(n+k)
     Worst - O(n+k)
     Average - O(n+k)
     Space - O(max-min)
     Stable - Yes
     In Place - No
 */

    public static void sort(int[] nums) {
        // Find max in input
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        // Make array of size max
        int[] count = new int[max + 1];

        // Iterate through & count occurrences
        for (int num : nums) {
            count[num]++;
        }

        // Iterate through count array & replace items in original array
        int totalItems = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] != 0) {
                nums[totalItems++] = i;
                count[i]--;
            }
        }
    }

    public static void sortSpaceOptimized(int[] nums) {
        // Get find max & min in input
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        // Make array of size range of the array
        int[] count = new int[max - min + 1];

        // Iterate through & count occurrences
        for (int num : nums) {
            count[num - min]++;
        }

        // Iterate through count array & replace items in original array
        int totalItems = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[totalItems++] = i + min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = generateRandomArray();

        System.out.println("Before:");
        printArray(numbers);

        sortSpaceOptimized(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }
}
