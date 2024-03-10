import java.util.Arrays;

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
  public int size() {
    return solutionData.length;
  }
}