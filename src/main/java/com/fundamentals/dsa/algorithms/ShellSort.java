package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

public class ShellSort {

/*
     https://www.programiz.com/dsa/shell-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Visualization of Shell Sort - https://www.youtube.com/watch?v=1yDcmjLTWOg
     Shell Sort - algorithm is a generalization of insertion & bubble sort via insertion & exchanging respectively.
        It sorts elements farther apart, then decreases that gap.
        0. Choose a sequence for the interval. Here, we use shell's original sequence (N/2, N/4, ...)
        1. Loop through the gap, starting at the gap value, and keep shifting/exchanging the value at the right if value at the left is larger.
        2. Insert the item that was originally at the right, the last exchanged location
        3. Change the gap length.
     Best - O(nlog(n)) - If the array is already sorted, we do nlogn comparisons
     Worst - always <= O(n^2) ; according to Poonen Theorem, worst case is O(N(logN)^2)
     Average - O(nlog(n)) -
     Space - O(1)
     Stable - No
     In Place - Yes
 */

    public static void sort(int[] array) {
        // Initialize the gap to the length of the array divided by 2
        int gap = array.length / 2;

        // Loop until the gap is 0
        while (gap > 0) {
            // Loop through the array, starting at the gap value
            for (int i = gap; i < array.length; i++) {
                // Save the current element in a temporary variable
                int temp = array[i];
                // Initialize a counter variable
                int j;
                // Loop until the counter is less than the gap and the element at the counter minus the gap is greater than the temp value
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    // Shift the element at the counter minus the gap to the current position
                    array[j] = array[j - gap];
                }
                // Set the current position to the temp value
                array[j] = temp;
            }
            // Divide the gap by 2 for the next iteration
            gap /= 2;
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
