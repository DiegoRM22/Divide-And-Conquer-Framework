import java.util.Arrays;

/**
 * Merge sort algorithm.
 */
class MergeSort implements DivideAndConquerAlgorithm {

  private int kDividerFactor;

  MergeSort(int dividerFactor) {
    kDividerFactor = dividerFactor;
  }

  MergeSort() {
    kDividerFactor = 2;
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
  @Override
  public Solution solveDirectly(Problem problem) {
    return new Solution(problem.getData());
  }

  /**
   * Divides the problem into subproblems.
   */
  @Override
  public Problem[] divide(Problem problem) {
    int[] data = problem.getData();
    int subproblemSize = data.length / kDividerFactor;
    int remainder = data.length % kDividerFactor;
    Problem[] subproblems = new Problem[kDividerFactor];
    int startIndex = 0;
    for (int i = 0; i < kDividerFactor; i++) {
      int subproblemLength = subproblemSize;
      if (i < remainder) {
        subproblemLength++;
      }
      int[] subproblemData = Arrays.copyOfRange(data, startIndex, startIndex + subproblemLength);
      subproblems[i] = new Problem(subproblemData);
      startIndex += subproblemLength;
    }
    
    return subproblems;
  }

  /**
   * Combines the solutions to the subproblems into the solution to the original
   * problem.
   */
  @Override
  public Solution combine(Solution[] solutions) {
    // Calculate the total length of the merged array
    int totalLength = 0;
    for (Solution solution : solutions) {
      totalLength += solution.getSolutionData().length;
    }
    int[] mergedArray = new int[totalLength];
    // Merge the arrays
    int mergeIndex = 0;
    for (Solution solution : solutions) {
      int[] solutionData = solution.getSolutionData();
      for (int value : solutionData) {
        mergedArray[mergeIndex++] = value;
      }
    }
    // Sort the merged array
    Arrays.sort(mergedArray);

    return new Solution(mergedArray);
  }

  /**
   * Returns the recurrence relation of the merge sort algorithm.
   */
  @Override
  public String recurrence() {
    return "T(n) = " + kDividerFactor + "T(n/" + kDividerFactor + ") + O(n)";
  }

  /**
   * Returns the size of the recursion tree of the merge sort algorithm.
   * logk(n) + 1
   */
  @Override
  public int getRecursionTreeSize(Problem p) {
    return (int) (Math.log(p.getData().length) / Math.log(kDividerFactor));
  }
}
