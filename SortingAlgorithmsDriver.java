/*
 * Class SortingAlgorithmsDriver
 * 
 * This class is used to run the SortingAlgorithmsProgram.
 * 
 * Name: Seth Voisine
 * UGA ID: 811906637
 * Date: 11/15/2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SortingAlgorithmsDriver {

    /*
     * Take user inputed file and read the content in the file.
     * Attach content in the file to an array and provide user
     * with different sorting algorithms.
     * Sort and return the number of comparisons that
     * the sorting algorithm made.
     * 
     * param - String[] args
     * 
     * @return - void
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("User Input: java SortingAlgorithmsDriver <inputfile.txt>");
            return;
        }

        // Get the file
        String fileName = args[0];
        File inputFile = new File(fileName);
        Scanner scanner = null;

        // Use try/catch block to error check if the file exists
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            return;
        }

        // Create an integer array list to input values from the file to
        ArrayList<Integer> valuesToSort = new ArrayList<>();

        // Read file
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                valuesToSort.add(scanner.nextInt());
            } else {
                System.err.println("File contains a non integer value, ending program...");
                scanner.close();
                return;
            }
        }

        scanner.close();

        // Prompt user
        System.out.println("selection-sort (s)");
        System.out.println("merge-sort (m)");
        System.out.println("heap-sort (h)");
        System.out.println("quick-sort-fp (q)");
        System.out.println("quick-sort-rp (r)");

        // Create a new scanner to read user input
        Scanner input = new Scanner(System.in);
        String userInput = "";

        System.out.print("Enter the algorithm: ");

        userInput = input.next().toLowerCase();

        // Run basic if, else if, else statement to see if the input was valid, end the
        // program after
        if (userInput.equals("s")) {
            SortingAlgorithms sort = new SortingAlgorithms();
            long comparisons = sort.selectionSort(valuesToSort);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Selection-sort comparisons: " + comparisons);

            input.close();
            return;
        } else if (userInput.equals("m")) {
            SortingAlgorithms sort = new SortingAlgorithms();
            long comparisons = sort.mergeSort(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Merge-sort comparisons: " + comparisons);

            input.close();
            return;
        } else if (userInput.equals("h")) {
            SortingAlgorithms sort = new SortingAlgorithms();
            long comparisons = sort.heapSort(valuesToSort);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Heap-sort comparisons: " + comparisons);

            input.close();
            return;
        } else if (userInput.equals("q")) {
            SortingAlgorithms sort = new SortingAlgorithms();
            long comparisons = sort.quickSortFP(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Quick-sort-fp comparisons: " + comparisons);

            input.close();
            return;
        } else if (userInput.equals("r")) {
            SortingAlgorithms sort = new SortingAlgorithms();
            long comparisons = sort.quickSortFP(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Quick-sort-rp comparisons: " + comparisons);

            input.close();
            return;
        } else {
            System.out.println("Inputted value must be s, m, h, q or r. Program Closing....");
            input.close();
            return;
        }
    }
}
