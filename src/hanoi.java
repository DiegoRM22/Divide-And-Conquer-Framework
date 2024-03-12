import java.util.Arrays;

class Hanoi implements DivideAndConquerAlgorithm {
  @Override
  public boolean small(Problem problem) {
    return problem.getData().length == 1; // Verifica si hay un solo disco en la aguja
  }

  @Override
  public Solution solveDirectly(Problem problem) {
    return new Solution(problem.getData()); // Solución directa es el estado actual de los discos
  }

  @Override
  public Problem[] divide(Problem problem) {
    int[] data = problem.getData();
    int n = data.length;
    int[] newData = Arrays.copyOfRange(data, 0, n - 1); // Quita el disco superior de la aguja
    return new Problem[] { new Problem(newData) };
  }

  @Override
  public Solution combine(Solution[] solutions) {
    return solutions[0]; // No hay combinación necesaria, ya que solo hay una aguja en este caso
  }

  @Override
  public int getRecursionTreeSize(Problem p) {
    return p.getData().length; // El tamaño del árbol de recursión es el número de discos
  }
}