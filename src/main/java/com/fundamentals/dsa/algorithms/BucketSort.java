package com.fundamentals.dsa.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
     Explanation of what's happening: https://www.youtube.com/watch?v=VuXbEb5ywrU
     https://www.programiz.com/dsa/bucket-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Bucket Sort - sorting algorithm that sorts by placing items in K buckets, then sorting them.
       1. Create buckets
       2. Map numbers to buckets
       3. Sort within buckets
       4. Remove numbers from buckets
     Given n = # of elements & k = # of buckets
     Best - O(n + k) - occurs when dataset is evenly distributed
     Worst - O(n^2) - occurs when dataset maps to one bucket only.
     Average - O(n+n^2/ k + k) -> O(n), when k = Theta(n) - occurs with random distribution
     Space - O(n+k) -> n = buckets that hold numbers; k = # buckets
     Stable - Yes
     In Place - No - requires extra space
 */

public class BucketSort {

    public static void sort(float[] arr) {
        int n = arr.length;
        if (n <= 0)
            return;
        @SuppressWarnings("unchecked")
        List<Float>[] bucket = new ArrayList[n];

        // Create empty buckets
        for (int i = 0; i < n; i++)
            bucket[i] = new ArrayList<Float>();

        // Add elements into the buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (arr[i] * n);
            bucket[bucketIndex].add(arr[i]);
        }

        // Sort the elements of each bucket
        for (int i = 0; i < n; i++) {
            Collections.sort((bucket[i]));
        }

        // Get the sorted array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr[index++] = bucket[i].get(j);
            }
        }
    }


    public static void main(String[] args) {
        float[] array = {0.14f, 0.81f, 0.89f, 0.63f, 0.38f, 0.44f, 0.91f, 0.71f, 0.52f, 0.27f};
        sort(array);
        System.out.println(Arrays.toString(array)); // prints [0.14, 0.27, 0.38, 0.44, 0.52, 0.63, 0.71, 0.81, 0.89, 0.91]

    }

}
