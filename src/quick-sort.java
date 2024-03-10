import java.util.Arrays;
import java.util.Vector;

class QuickSort implements DivideAndConquerAlgorithm {
  private Vector<Integer> pivots = new Vector<Integer>();
  int pivotIndex = 0;
  int kDividerFactor = 2;

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
        i++; // increment index of smaller element
        int temp = data[i]; // swap data[i] and data[j]
        data[i] = data[j]; // move all elements smaller than pivot to the left
        data[j] = temp;
      }
    }
    int temp = data[i + 1];
    data[i + 1] = data[data.length - 1];
    data[data.length - 1] = temp;
    int[] left = Arrays.copyOfRange(data, 0, i + 1);
    int[] right = Arrays.copyOfRange(data, i + 2, data.length);
   
    return new Problem[] { new Problem(left), new Problem(right) };
  }

  /**
   * Combines the solutions to the subproblems.
   */
  @Override
  public Solution combine(Solution[] solutions) {
    int[] left = solutions[0].getSolutionData();
    int[] right = solutions[1].getSolutionData();
    int[] result = new int[left.length + right.length + 1];
    for (int i = 0; i < left.length; i++) {
      result[i] = left[i];
    }
    result[left.length] = pivots.get(pivotIndex - 1);
    // quitar el pivote
    pivots.remove(pivotIndex - 1);
    for (int i = 0; i < right.length; i++) {
      result[i + left.length + 1] = right[i];
    }

    Arrays.sort(result);
    --pivotIndex;
    return new Solution(result);
  }

  /**
   * Solves the problem directly.
   */
  @Override
  public Solution solveDirectly(Problem problem) {
    return new Solution(problem.getData());
  }

  /**
   * Returns the size of the recursion tree of the algorithm.
   * logk(n) leave nodes.
   */
  @Override
  public int getRecursionTreeSize(Problem p) {
    return (int) (Math.log(p.getData().length) / Math.log(kDividerFactor));
  }
}
