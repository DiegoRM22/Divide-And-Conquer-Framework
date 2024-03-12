import java.util.Arrays;
import java.util.Vector;

/**
 * The DivideAndConquerFramework class is the client of the DivideAndConquerAlgorithm class.
 * It uses the algorithm to solve a problem.
 */
class DivideAndConquerFramework {
  public String movements = ""; // Variable para almacenar los movimientos de las torres de Hanoi
  
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
      // System.out.println(problem.getData()[left] + " " + problem.getData()[middle] + " " + problem.getData()[right] + "    Valor a buscar: " + searchedValue);
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

   // Nuevo método para resolver las Torres de Hanoi
   public Solution executeHanoi(DivideAndConquerAlgorithm algorithm, int n, String origen, String destino, String auxiliar) {
    if (n == 1) {
      MoverDisco(origen, destino);
      return new Solution(new int[] {1}); // Simplemente retornamos un arreglo con 1, solo para mantener consistencia en la firma del método
    } else {
      Solution sol1 = executeHanoi(algorithm, n - 1, origen, auxiliar, destino);
      MoverDisco(origen, destino);
      Solution sol2 = executeHanoi(algorithm, n - 1, auxiliar, destino, origen);
     
      Solution[] solutions = { sol1, sol2 };
      return algorithm.combine(solutions);
    }
  }

  // Método para mover un disco de una aguja a otra
  public void MoverDisco(String origen, String destino) {
    System.out.println("Mover disco de " + origen + " a " + destino);
    movements += "Mover disco de " + origen + " a " + destino + "\n";
  }

  public void printHanoiMovements() {
    System.out.println("Movimientos de las Torres de Hanoi:");
    System.out.println(movements);
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
