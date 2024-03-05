
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

  /**
   * Returns the recurrence relation of the algorithm.
   * @param algorithm the algorithm to use
   * @return the recurrence relation of the algorithm
   */
  public String getRecurrence(DivideAndConquerAlgorithm algorithm) {
    return algorithm.recurrence();
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