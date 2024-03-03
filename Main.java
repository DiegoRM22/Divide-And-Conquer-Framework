
import java.util.Arrays;

// Archivo para probar el mergesort
public class Main {
  public static void main(String[] args) {
    int[] data = { 1, 3, 2, 5, 4, 8, 47, 6 };
    Problem problem = new Problem(data);
    Solution solution = new MergeSort().solve(problem);
    System.out.println(Arrays.toString(solution.getSolutionData()));
  }
}