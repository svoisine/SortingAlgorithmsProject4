/*
 * Class SortingAlgorithms
 * 
 * This class is used to house all 4 types of sorting algorithms.
 * Selection Sort, Merge Sort, Heap Sort and Quick Sort
 * Quick Sort will have two different methods, one for first value
 * being the pivot and one for the pivot value being random.
 * 
 * Name: Seth Voisine
 * UGA ID: 811906637
 * Date: 11/15/2025
 */

import java.util.ArrayList;

public class SortingAlgorithms {

    /*
     * Constructor for SortingAlgorithms class.
     * 
     * param - none
     * 
     * @return - none
     */
    public SortingAlgorithms() {
    }

    /*
     * This method sorts the inputted array list via a selection sort.
     * Selection Sort is when you loop through an array and compare the indexed
     * value i to the rest of the array.
     * You find the smallest value in the array and you swap it with i. i then
     * increments up by one and loops through
     * the array to find the next smallest value.
     * 
     * Selection Sort - O(n^2) since you loop through an array of length n, while
     * also making n amount of comparisons.
     * 
     * param - ArrayList<Integer> valuesToSort
     * 
     * @return - long comparisons
     */
    public long selectionSort(ArrayList<Integer> valuesToSort) {
        long comparisons = 0;
        int n = valuesToSort.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (valuesToSort.get(j) < valuesToSort.get(minIndex)) {
                    minIndex = j;
                }
            }

            int temp = valuesToSort.get(i);
            valuesToSort.set(i, valuesToSort.get(minIndex));
            valuesToSort.set(minIndex, temp);
        }

        return comparisons;
    }

    /*
     * This method sorts the inputted array list via a merge sort.
     * Merge sort is when you continually split the array until there is only one
     * value remaining. and then you recurisvely merge the values back together,
     * sorting each half as you go.
     * 
     * This class specifically is used as a helper to call the actual merge sort
     * method.
     * 
     * Merge Sort - O(n log n) since you split the array log n times while making
     * n comparisons at each level.
     * 
     * param - ArrayList<Integer> valuesToSort, int left, int right
     * 
     * @return - long comparisons
     */
    public long mergeSort(ArrayList<Integer> valuesToSort, int left, int right) {
        // Split down to a single value
        if (left >= right) {
            return 0;
        }

        // Find middle
        int mid = (left + right) / 2;

        // Set comparison value
        long comparisons = 0;

        // Recursively split the array in 2;
        comparisons += mergeSort(valuesToSort, left, mid);
        comparisons += mergeSort(valuesToSort, mid + 1, right);

        // Recursively call the actual merge sorting algorithm on the split array
        comparisons += merge(valuesToSort, left, mid, right);

        return comparisons;
    }

    /*
     * This method does the actual comparisons and properly merges both halves of
     * the
     * array together.
     * 
     * param - ArrayList<Integer> valuesToSort, int left, int mid, int right
     * 
     * @return - long comparisons
     */
    public long merge(ArrayList<Integer> valuesToSort, int left, int mid, int right) {

        ArrayList<Integer> temp = new ArrayList<>();

        // Start of the left and the right half
        int i = left;
        int j = mid + 1;

        long comparisons = 0;

        // Merge values
        while (i <= mid && j <= right) {
            comparisons++;
            if (valuesToSort.get(i) <= valuesToSort.get(j)) {
                temp.add(valuesToSort.get(i));
                i++;
            } else {
                temp.add(valuesToSort.get(j));
                j++;
            }
        }

        // Copy remaining values
        while (i <= mid) {
            temp.add(valuesToSort.get(i));
            i++;
        }
        while (j <= right) {
            temp.add(valuesToSort.get(j));
            j++;
        }

        // Write back to the original list
        for (int k = 0; k < temp.size(); k++) {
            valuesToSort.set(left + k, temp.get(k));
        }

        return comparisons;
    }

    /*
     * This method sorts the inputted array list via a heap sort.
     * Heap Sort is when you build a max heap and then recursively find the
     * max or minimum element and swap it with the last or first element.
     * 
     * Heap Sort - O(nlogn) because constructing the max heap is O(n) and
     * extracting the elements you repeated remove the max value, and swap
     * it with the last elementto reduce the heap.
     * To reduce the heap you have to swap n amount of times through the height of
     * the tree which is O(logn)
     * So the total is O(n) + O(nlogn) = O(nlogn)
     * 
     * This sorting algorithm can be found at
     * https://www.geeksforgeeks.org/dsa/heap-sort/
     * 
     * param - ArrayList<Integer> valuesToSort
     * 
     * @return - long comparisons
     */
    public long heapSort(ArrayList<Integer> valuesToSort) {
        long comparisons = 0;
        int n = valuesToSort.size();

        // Build the max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            comparisons += heapify(valuesToSort, n, i);
        }

        // Pull values from the heap one by one
        for (int i = n - 1; i > 0; i--) {

            // Move current root to the end
            int temp = valuesToSort.get(0);
            valuesToSort.set(0, valuesToSort.get(i));
            valuesToSort.set(i, temp);

            // Reduce the heap
            comparisons += heapify(valuesToSort, i, 0);
        }

        return comparisons;
    }

    /*
     * This is the helper function for heap sort. This is used to build the max heap
     * as well as to reduce the max heap.
     * 
     * param - ArrayList<Integer> valuesToSort, int n, int i
     * 
     * @return - long comparisons
     */
    public long heapify(ArrayList<Integer> valuesToSort, int n, int i) {
        long comparisons = 0;

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left is larger than the root
        if (left < n && valuesToSort.get(left) > valuesToSort.get(largest)) {
            comparisons++;
            largest = left;
        }

        // If right is larger than the largest so far
        if (right < n && valuesToSort.get(right) > valuesToSort.get(largest)) {
            comparisons++;
            largest = right;
        }

        // If largest is not the root
        if (largest != i) {
            int swap = valuesToSort.get(i);
            valuesToSort.set(i, valuesToSort.get(largest));
            valuesToSort.set(largest, swap);

            // Recursively "heapify" the now affected subtree
            comparisons += heapify(valuesToSort, n, largest);
        }

        return comparisons;
    }

    /*
     * This class sorts the inputted array list via a quick sort where
     * the pivot is FIRST value.
     * 
     * In quick sort wehre the first value is the pivot, you use two pointers to
     * compare to the pivot value. One at the beginning (just after the pivot) and
     * one at the end.
     * Move the left pointer up and the right pointer back and swap values when the
     * left pointer > pivot and the right pointer < pivot.
     * Do this until the pointers meet.
     * You then have to swap the pivot value with the right pointer to place it in
     * the correct position.
     * 
     * Recursively do this for both sides of the array until the array is sorted
     * 
     * Quick Sort with First Value as Pivot - O(nlogn), but O(n^2) in worst case.
     * This is because you are splitting the array logn times on average while
     * making n comparisons at each level.
     * In the worst case, if the array is already sorted or reverse sorted, you end
     * up making n splits while making n comparisons at each level.
     * 
     * param - ArrayList<Integer> valuesToSort, int low, int high
     * 
     * @return - long comparisons
     */
    public long quickSortFP(ArrayList<Integer> valuesToSort, int low, int high) {
        long comparisons = 0;

        // Base case
        while (low < high) {
            // Split the array and get pivot index of each half
            SplitResult result = split(valuesToSort, low, high);
            comparisons += result.comparisons;

            // Recursively sort the smaller half so that the stack depth is O(log n)
            if (result.pivotIndex - low < high - result.pivotIndex) {
                comparisons += quickSortFP(valuesToSort, low, result.pivotIndex - 1);
                low = result.pivotIndex + 1;
            } else {
                comparisons += quickSortFP(valuesToSort, result.pivotIndex + 1, high);
                high = result.pivotIndex - 1;
            }
        }

        return comparisons;
    }

    /*
     * Helper function to split the array and swap the values around the pivot.
     * 
     * param - ArrayList<Integer> valuesToSort, int low, int high
     * 
     * @return - SplitResult
     */
    public SplitResult split(ArrayList<Integer> valuesToSort, int low, int high) {
        // Choose the first element as pivot
        int pivot = valuesToSort.get(low);
        int i = low + 1;
        long comparisons = 0;

        // Rearrange the array
        for (int j = low + 1; j <= high; j++) {
            comparisons++;
            if (valuesToSort.get(j) < pivot) {
                int temp = valuesToSort.get(i);
                valuesToSort.set(i, valuesToSort.get(j));
                valuesToSort.set(j, temp);
                i++;
            }
        }

        // Swap the pivot element with the element at i-1
        int temp = valuesToSort.get(low);
        valuesToSort.set(low, valuesToSort.get(i - 1));
        valuesToSort.set(i - 1, temp);

        // Return the pivot index and comparisons
        return new SplitResult(i - 1, comparisons);
    }

    /*
     * This class sorts the inputted array list via a quick sort where
     * the pivot is a RANDOM value.
     * 
     * This class works the exact same as the other quick sort but instead once
     * you call splitRandom() it will find a random variable within the array
     * to be the pivot value and swap it to the front instead of just assuming
     * the first value is the pivot value.
     * 
     * 
     * 
     * param - ArrayList<Integer> valuesToSort
     * 
     * @return - long comparisons
     */
    public long quickSortRP(ArrayList<Integer> valuesToSort, int low, int high) {
        long comparisons = 0;

        // Base case || only difference from other quicksort is that it calls
        // splitRandom() instead of split
        if (low < high) {
            SplitResult result = splitRandom(valuesToSort, low, high);
            comparisons += result.comparisons;

            // Recursively sort both halves
            comparisons += quickSortRP(valuesToSort, low, result.pivotIndex - 1);
            comparisons += quickSortRP(valuesToSort, result.pivotIndex + 1, high);
        }
        return comparisons;
    }

    /*
     * Helper function that splits the array and swaps the values around the pivot.
     * 
     * param - ArrayList<Integer> valuesToSort, int low, int high
     * 
     * @return - SplitResult
     */
    public SplitResult splitRandom(ArrayList<Integer> valuesToSort, int low, int high) {
        // Find a random pivot using Math.random()
        int randomIndex = low + (int) (Math.random() * (high - low + 1));

        // Swap the pivot into the first value
        int temp = valuesToSort.get(low);
        valuesToSort.set(low, valuesToSort.get(randomIndex));
        valuesToSort.set(randomIndex, temp);

        // Do the exact same thing as the split() method to iterate through the values
        // to compare then swap
        int pivot = valuesToSort.get(low);
        int i = low + 1;
        long comparisons = 0;

        // Rearrange the array
        for (int j = low + 1; j <= high; j++) {
            comparisons++;
            if (valuesToSort.get(j) < pivot) {
                int t = valuesToSort.get(i);
                valuesToSort.set(i, valuesToSort.get(j));
                valuesToSort.set(j, t);
                i++;
            }
        }

        // Swap pivot value to sorted position
        int temp2 = valuesToSort.get(low);
        valuesToSort.set(low, valuesToSort.get(i - 1));
        valuesToSort.set(i - 1, temp2);

        // Return pivot index and the number of comparisons made
        return new SplitResult(i - 1, comparisons);
    }
}
