package com.fundamentals.dsa.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import static com.fundamentals.dsa.algorithms.ArrayHelper.*;

/*
     Explanation of what's happening: https://www.youtube.com/watch?v=6du1LrLbDpA
     Time Complexity Explanation: https://iq.opengenus.org/time-and-space-complexity-of-radix-sort/
     General Explanation: https://www.youtube.com/watch?v=XiuSW_mEn7g
     https://www.programiz.com/dsa/radix-sort
     https://algs4.cs.princeton.edu/cheatsheet/
     Radix Sort - sorting algorithm that sorts based on the digits places. similar to counting sort
        1. get the max & count the number of digits places there are
        2. for the number of digits that exist:
            a. map the number to a bucket, based off of that number 'places' digit.
            b. iterate through the buckets & map them back to the array.

     Given that n = input size, d = number of digits (of largest number), and b = base# we are using, 10 here.
     Best - O(d*(n+k)) -> O(d*(n+n)) -> O(dn) - when all elements have the same number of digits
     Worst - O(logb(mx)(n+b)) -> occurs when all elements have the same number of digits except one element which has significantly large number of digits
     Average - O(d(n+b))
     Space - O(n+b) -> n = buckets that hold numbers; b = base
     Stable - Yes
     In Place - No - requires extra space
 */

public class RadixSort{

    public static void radixSort(int[] array) {
        // Calculate the max number of digits
        int max = getMax(array);
        int maxDigits = 0;
        final int BUCKET_SIZE = 10;

        while (max > 0) {
            max/=10;
            maxDigits++;
        }

        // Create a queue for each digit (0-9)

        @SuppressWarnings("unchecked")
        Queue<Integer>[] digitQueues = new Queue[BUCKET_SIZE];
        for (int i = 0; i < 10; i++) {
            digitQueues[i] = new LinkedList<Integer>();
        }

        // Repeat the process for each remaining digit, starting with the next most significant digit
        // Sort by digits place & place into bins, then place the items in the bins back to the array
        // Repeat this step for # of digits.
        int divisor = 1;
        for (int digit = 1; digit <= maxDigits; digit++) {
            // Add items to the buckets, based off of the next largest digits place
            for (int i = 0; i < array.length; i++) {
                digitQueues[(array[i] / divisor) % BUCKET_SIZE].add(array[i]);
            }

            // Put the items back from bucket -> array
            int i = 0;
            for (int j = 0; j < BUCKET_SIZE; j++) {
                while (!digitQueues[j].isEmpty()) {
                    array[i++] = digitQueues[j].remove();
                }
            }

            divisor *= 10;
        }
    }


    public static void main(String[] args) {
        int[] numbers = generateRandomArray();

        System.out.println("Before:");
        printArray(numbers);

        radixSort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
    }
}
