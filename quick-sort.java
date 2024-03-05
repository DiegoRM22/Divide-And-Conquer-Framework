import java.util.Arrays;
import java.util.Vector;

class QuickSort implements DivideAndConquerAlgorithm {
  private Vector<Integer> pivots = new Vector<Integer>();
  int pivotIndex = 0;

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
   * Divides the problem into subproblems.
   */
  @Override
  public Problem[] divide(Problem problem) {
    int[] data = problem.getData();
    int pivot = data[data.length - 1];
    // Insertar el pivote en la ultima posicion
    pivots.add(pivot);
    // System.out.println("PIVOTES: " + pivots);
    pivotIndex++;
    int i = -1;
    for (int j = 0; j < data.length - 1; j++) {
      if (data[j] < pivot) {
        i++;
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
      }
    }
    int temp = data[i + 1];
    data[i + 1] = data[data.length - 1];
    data[data.length - 1] = temp;
    int[] left = Arrays.copyOfRange(data, 0, i + 1);
    int[] right = Arrays.copyOfRange(data, i + 2, data.length);
    // System.out.println("\nData: " + Arrays.toString(data) + '\n');
    // System.out.println("Left: " + Arrays.toString(left));
    // System.out.println("Pivot: " + pivot);
    // System.out.println("Right: " + Arrays.toString(right));
    return new Problem[] { new Problem(left), new Problem(right) };
  }

  /**
   * Combines the solutions to the subproblems.
   */
  @Override
  public Solution combine(Solution[] solutions) {
    // System.out.println("Pivots: " + pivots);
    // System.out.println("Pivot index: " + pivotIndex);
    int[] left = solutions[0].getSolutionData();
    int[] right = solutions[1].getSolutionData();
    int[] result = new int[left.length + right.length + 1];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i];
    }
    // System.out.println("Metiendo el pivote: " + pivots.get(pivotIndex - 1));
    result[left.length] = pivots.get(pivotIndex - 1);
    // quitar el pivote
    pivots.remove(pivotIndex - 1);
    for (int i = 0; i < right.length; i++) {
      result[i + left.length + 1] = right[i];
    }

    Arrays.sort(result);
    --pivotIndex;
    // System.out.println("Result after combine: " + Arrays.toString(result));
    return new Solution(result);
  }

  /**
   * Solves the problem directly.
   */
  public Solution solveDirectly(Problem problem) {
    return new Solution(problem.getData());
  }

  /**
   * Returns the recurrence relation of the algorithm.
   */
  @Override
  public String recurrence() {
    return "2T(n/2) + O(n)";
  }

  
}
