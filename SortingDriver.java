/*
 * Class SortingDriver
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
import java.util.Random;
import java.util.Scanner;

public class SortingDriver {

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
            System.err.println(
                    "User Input: java SortingAlgorithmsDriver <inputfile.txt> OR <Number of Integers in Desired Array>");
            return;
        }

        // Get the first argument from the command line
        String input = args[0];
        ArrayList<Integer> valuesToSort = new ArrayList<>();

        // Boolean value to see if the first argument is an integer
        boolean isInteger = false;

        try {
            int size = Integer.parseInt(input);

            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                valuesToSort.add(rand.nextInt(size));
            }

            isInteger = true;
        } catch (NumberFormatException e) {
            // The first argument is not an integer
        }

        if (!isInteger) {
            // Input is a file not a number, read the file

            File inputFile = new File(input);
            Scanner scanner = null;

            // Use try/catch block to error check if the file exists
            try {
                scanner = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + input);
                return;
            }

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
        }

        // Prompt user
        System.out.println("selection-sort (s)");
        System.out.println("merge-sort (m)");
        System.out.println("heap-sort (h)");
        System.out.println("quick-sort-fp (q)");
        System.out.println("quick-sort-rp (r)");

        // Create a new scanner to read user input
        Scanner uinput = new Scanner(System.in);
        String userInput = "";

        System.out.print("Enter the algorithm: ");

        userInput = uinput.next().toLowerCase();

        // Run basic if, else if, else statement to see if the input was valid, end the
        // program after
        if (userInput.equals("s")) {
            Sorting sort = new Sorting();
            long comparisons = sort.selectionSort(valuesToSort);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Selection-sort comparisons: " + comparisons);

            uinput.close();
            return;
        } else if (userInput.equals("m")) {
            Sorting sort = new Sorting();
            long comparisons = sort.mergeSort(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Merge-sort comparisons: " + comparisons);

            uinput.close();
            return;
        } else if (userInput.equals("h")) {
            Sorting sort = new Sorting();
            long comparisons = sort.heapSort(valuesToSort);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Heap-sort comparisons: " + comparisons);

            uinput.close();
            return;
        } else if (userInput.equals("q")) {
            Sorting sort = new Sorting();
            long comparisons = sort.quickSortFP(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Quick-sort-fp comparisons: " + comparisons);

            uinput.close();
            return;
        } else if (userInput.equals("r")) {
            Sorting sort = new Sorting();
            long comparisons = sort.quickSortFP(valuesToSort, 0, valuesToSort.size() - 1);

            for (int num : valuesToSort) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("#Quick-sort-rp comparisons: " + comparisons);

            uinput.close();
            return;
        } else {
            System.out.println("Inputted value must be s, m, h, q or r. Program Closing....");
            uinput.close();
            return;
        }
    }
}
