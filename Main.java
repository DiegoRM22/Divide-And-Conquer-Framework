
import java.util.Arrays;

// Archivo para probar el mergesort
public class Main {
  public static void main(String[] args) {
    int[] data = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    Problem problem = new Problem(data);
    Solution solution = new MergeSort().solve(problem);
    System.out.println(Arrays.toString(solution.getSolutionData()));
  }
}