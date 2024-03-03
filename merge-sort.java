import java.util.Arrays;

class MergeSort implements DivideAndConquerAlgorithm {

  private int kDividerFactor = 2;

  /**
   * Solves the problem using a divide-and-conquer approach, specifically merge sort.
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
      return combine(solutions);
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
    for (Problem subproblem : subproblems) {
      System.out.println(Arrays.toString(subproblem.getData()));
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

    // Sort the merged array (you can use your favorite sorting algorithm here)
    Arrays.sort(mergedArray);

    return new Solution(mergedArray);
  }

  /**
   * Returns the recurrence relation of the merge sort algorithm.
   */
  @Override
  public String recurrence() {
    return "T(n) = k * T(n/k) + O(n)";
  }
}
