
import java.util.Arrays;

/**
 * The DivideAndConquerFramework class is the client of the DivideAndConquerAlgorithm class.
 * It uses the algorithm to solve a problem.
 */
class DivideAndConquerFramework {
  /**
   * Executes the algorithm to solve the problem.
   * @param algorithm the algorithm to use
   * @param problem the problem to solve
   * @return the solution to the problem
   */
  public Solution executeAlgorithm(DivideAndConquerAlgorithm algorithm, Problem problem) {
    if (algorithm.small(problem)) {
      return algorithm.solveDirectly(problem);
    } else {
      Problem[] subproblems = algorithm.divide(problem);
      Solution[] solutions = new Solution[subproblems.length];
      for (int i = 0; i < subproblems.length; i++) {
        solutions[i] = executeAlgorithm(algorithm, subproblems[i]);
      }
      return algorithm.combine(solutions);
    }
  }

  public Solution executeAlgorithm(Problem problem, int searchedValue, int left, int right) {
    if (left <= right) {
      int middle = left + (right - left) / 2;
      System.out.println(problem.getData()[left] + " " + problem.getData()[middle] + " " + problem.getData()[right] + "    Valor a buscar: " + searchedValue);
      if (problem.getData()[middle] < searchedValue) {
        return executeAlgorithm(problem, searchedValue, middle + 1, right);
      } else if (problem.getData()[middle] > searchedValue) {
        return executeAlgorithm(problem, searchedValue, left, middle - 1);
      } else {

        return new Solution(new int[] { middle });
      }
    }
    return new Solution(new int[] { -1 });
  }

  
  /**
   * Returns the recurrence relation of the algorithm.
   * @param algorithm the algorithm to use
   * @return the recurrence relation of the algorithm
   */
  public String getRecurrence(DivideAndConquerAlgorithm algorithm) {
    return "2T(n/2) + O(n)";
  }

  /**
   * Returns the size og the recursion tree of the algorithm.
   * @param algorithm the algorithm to use
   * @param problem the problem to solve
   * @return the size of the recursion tree of the algorithm
   */
  public int getRecursionTreeSize(DivideAndConquerAlgorithm algorithm, Problem problem) {
    return algorithm.getRecursionTreeSize(problem);
  }
}