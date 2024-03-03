
import java.util.Arrays;


class MergeSort implements DivideAndConquerAlgorithm {

  private int kDividerFactor = 2;

  /**
   * Solves the problem using a divide-and-conquer approach, specifically
   * merge sort.
   */
  @Override
  public Solution solve(Problem problem) {
    if (small(problem)) {
      return solveDirectly(problem);
    } else {
      Problem[] subproblems = divide(problem);
      Solution[] solutions = new Solution[subproblems.length];
      for (int i = 0; i < subproblems.length; i++) {
        solutions[i] = solve(subproblems[i]);
      }
      return combine(solutions[0], solutions[1]);
    }
  }

  /**
   * Checks if the problem is small enough to be solved directly.
   */
  @Override
  public boolean small(Problem problem) {
    return problem.getData().length < 2;
  }

  /**
   * Solves the problem directly.
   */
  public Solution solveDirectly(Problem problem) {
    return new Solution(problem.getData());
  }

  @Override
  public Problem[] divide(Problem problem) {
    // Para ampliar a k subproblemas, habria que cambiar y no devolver solo dos subproblemas.
    int[] data = problem.getData();
    int mid = data.length / kDividerFactor;
    int[] left = Arrays.copyOfRange(data, 0, mid);
    int[] right = Arrays.copyOfRange(data, mid, data.length);
    return new Problem[] { new Problem(left), new Problem(right) };
  }

  /**
   * Combines the solutions to the subproblems into the solution to the original
   * problem.
   */
  @Override
  public Solution combine(Solution firstSolution, Solution secondSolution) {
    // Extracting data from solutions
    int[] leftArray = firstSolution.getSolutionData();
    int[] rightArray = secondSolution.getSolutionData();
    // Merging arrays initialization
    int[] mergedArray = new int[leftArray.length + rightArray.length];
    int leftIndex = 0, rightIndex = 0, mergeIndex = 0;
    // Merging arrays
    while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
      if (leftArray[leftIndex] < rightArray[rightIndex]) {
        // Copy element from left array
        mergedArray[mergeIndex++] = leftArray[leftIndex++];
      } else {
        // Copy element from right array
        mergedArray[mergeIndex++] = rightArray[rightIndex++];
      }
    }
    // Copy remaining elements from left array
    while (leftIndex < leftArray.length) {
      mergedArray[mergeIndex++] = leftArray[leftIndex++];
    }
    // Copy remaining elements from right array
    while (rightIndex < rightArray.length) {
      mergedArray[mergeIndex++] = rightArray[rightIndex++];
    }
    // Return new solution with merged array
    return new Solution(mergedArray);
  }

  /**
   * Returns the recurrence relation of the merge sort algorithm.
   */
  @Override
  public String recurrence() {
    return "T(n) = 2T(n/2) + O(n)";
  }


}
