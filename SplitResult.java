/*
 * Class SplitResult
 * 
 * This class is used as a way to not only return the pivot index
 * but also to give the ability to reference the number of comparisons made.
 * I have to do it this way since the recursive call would cause problems with properly
 * tracking the amount of comparisons that are made.
 * 
 * Name: Seth Voisine
 * UGA ID: 811906637
 * Date: 11/15/2025
 */
public class SplitResult {
    int pivotIndex;
    long comparisons;

    public SplitResult(int pivotIndex, long comparisons) {
        this.pivotIndex = pivotIndex;
        this.comparisons = comparisons;
    }
}
