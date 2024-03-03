
import java.util.Arrays;

/**
 * Defines a class to represent the problem to be solved.
 */
class Problem {
  private int[] data;
  public Problem(int[] data) {
    this.data = data;
  }
  public int[] getData() {
    return data;
  }
}

/**
 * Defines a class to represent the solution to the problem.
 */
class Solution {
  private int[] solutionData;
  public Solution(int[] solutionData) {
    this.solutionData = solutionData;
  }
  public int[] getSolutionData() {
    return solutionData;
  }
}

/**
 * Divide and conquer algorithm interface.
 */
interface DivideAndConquerAlgorithm {
  Solution solve(Problem p);
  Problem[] divide(Problem p);
  Solution combine(Solution s1, Solution s2);
  boolean small(Problem p);
  String recurrence();
}

