package com.fundamentals.dsa.algorithms;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;
import static com.fundamentals.dsa.algorithms.ArrayHelper.printArray;

/*
     https://www.programiz.com/dsa/merge-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Merge Sort - recursive divide & conquer algorithm where we
        1. divide the original array into multiple subarrays
        2. combine the 2 smaller arrays in order
     Best - O(n*log(n))
     Worst - O(n*log(n))
     Average - O(n*log(n))
     Space - O(n)
     Stable - Yes
     In Place - No
 */

public class MergeSort {
    public static void sort(int[] nums){
        mergeSort(nums, 0, nums.length - 1);
    }

    // Continuously call mergeSort to divide the array into 2 subarrays, then call the merge method to merge the divided subarrays in order
    private static void mergeSort(int[] nums, int left, int right) {
        if (left < right){
            // mid is the point where array is divided into 2 subarrays
            int mid = left + (right - left)/2;

            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);

            // Merge the sorted subarrays
            merge(nums, left, mid, right);
        }
    }

    // Merge the two subarrays L & M, representing the start of the subarrays
    private static void merge(int[] nums, int left, int mid, int right) {

        // Create & Populate the Subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] M = new int[n2];

        for(int i=0; i < n1; i++){
            L[i] = nums[left + i];
        }
        for(int i = 0; i< n2; i++){
            M[i] = nums[mid + 1 + i];
        }

        // Maintain current index of sub-arrays and main array
        int i=0, j=0, k=left;

        // Until we reach either end of either L or M, pick smaller among
        // elements L and M and place them in the proper position in nums[k]
         while(i < n1 && j < n2){
            if (L[i] <= M[j]) {
                nums[k] = L[i];
                i++;
            }else{
                nums[k] = M[j];
                j++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and populate nums
         while (i < n1) {
            nums[k++] = L[i];
            i++;
        }

        while (j < n2) {
            nums[k++] = M[j];
            j++;
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
