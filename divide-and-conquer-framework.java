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
    return algorithm.solve(problem);
  }

  /**
   * Returns the recurrence relation of the algorithm.
   * @param algorithm the algorithm to use
   * @return the recurrence relation of the algorithm
   */
  public String getRecurrence(DivideAndConquerAlgorithm algorithm) {
    return algorithm.recurrence();
  }
}