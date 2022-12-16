package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

public class HeapSort {


    /*
     https://www.programiz.com/dsa/heap-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     https://www.youtube.com/watch?v=2DmK_H7IdTo
     Heap Sort - sorting algorithm based off properties of array & tree data structure properties
        0. Relationship between array indices and tree elements
            a. Given indice i of any tree node mapped to an array... if the node has children....
                the node's left child is 2*i+1
                the node's right child is 2*i+2
        1. heapify the input array, setting the largest value item at the top.
        2. iterate from the end to 0,
            a. set the first index (largest item) as the last item in the array.
            b. heapify to maintain the top of the heap has the next largest item from elements 0->i, not including items that have been sorted
     Best - O(n*log(n)) ... or O(n) if all the keys are the same
     Worst - O(n*log(n))
     Average - O(n*log(n)) -- proof found here: https://iq.opengenus.org/time-complexity-of-heap-sort/
     Space - O(1)
     Stable - No -
     In Place - Yes
 */

    public static void heapSort(int[] nums) {
        int size = nums.length;
        // Create max heap
        for (int i = size / 2; i >= 0; i--)
            heapify(nums, size, i);

        // Remove the element from the top of the heap (largest), one by one
        for (int i = size - 1; i >= 0; i--) {
            swap(nums, 0, i);

            // maintain heap property
            heapify(nums, i, 0);
        }
    }

    private static void heapify(int[] nums, int size, int cur) {
        // find largest between current and its two children (left & right)
        int left = 2 * cur + 1;
        int right = 2 * cur + 2;
        int largest = cur;

        if (left < size && nums[left] > nums[largest])
            largest = left;
        if (right < size && nums[right] > nums[largest])
            largest = right;

        // Swap & continue heapifying if root is not the largest
        if (cur != largest) {
            swap(nums, cur, largest);
            heapify(nums, size, largest);
        }
    }

    public static void main(String[] args) {
        int[] numbers = generateRandomArray();

        System.out.println("Before:");
        printArray(numbers);

        heapSort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }

}
