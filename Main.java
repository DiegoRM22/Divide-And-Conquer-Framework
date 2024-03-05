import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Archivo para probar el mergesort
public class Main {
  public static void main(String[] args) {
    InstanceGenerator instanceGenerator = new InstanceGenerator();
    DivideAndConquerFramework framework = new DivideAndConquerFramework();
    QuickSort quickSort = new QuickSort();
    MergeSort mergeSort = new MergeSort();
    long averageTimeMergeSort = 0;
    long averageTimeQuickSort = 0;
    
    try {
      FileWriter writer = new FileWriter("results.txt");

      // Crear 4 instancias de diferentes tamaños
      for (int size : new int[] {10, 100, 1000, 10000}) {
        Problem problem = instanceGenerator.generateInstance(size);
        
        // Medir tiempo de ejecución del MergeSort
        long startTimeMergeSort = System.nanoTime();
        Solution solution = framework.executeAlgorithm(mergeSort, problem);
        long endTimeMergeSort = System.nanoTime();
        long elapsedTimeMergeSort = endTimeMergeSort - startTimeMergeSort;
        averageTimeMergeSort += elapsedTimeMergeSort;
        
        writer.write("MergeSort Execution Time (Size " + size + "): " + elapsedTimeMergeSort + " nanoseconds\n");
        
        // Medir tiempo de ejecución del QuickSort
        long startTimeQuickSort = System.nanoTime();
        Solution solution2 = framework.executeAlgorithm(quickSort, problem);
        long endTimeQuickSort = System.nanoTime();
        long elapsedTimeQuickSort = endTimeQuickSort - startTimeQuickSort;
        averageTimeQuickSort += elapsedTimeQuickSort;
        
        writer.write("QuickSort Execution Time (Size " + size + "): " + elapsedTimeQuickSort + " nanoseconds\n");
      }

      writer.write("Average MergeSort Execution Time: " + averageTimeMergeSort / 4 + " nanoseconds\n");
      writer.write("Average QuickSort Execution Time: " + averageTimeQuickSort / 4 + " nanoseconds\n");
      
      writer.close();
      System.out.println("Results have been written to results.txt");
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file: " + e.getMessage());
    }
  }
}
