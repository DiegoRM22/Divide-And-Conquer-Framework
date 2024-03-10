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
    MergeSort mergeSort3 = new MergeSort(3);
    long averageTimeMergeSort = 0;
    long averageTimeQuickSort = 0;
    long averageTimeMergeSort3 = 0;


    try {
      FileWriter writer = new FileWriter("results.txt");

      // Crear 4 instancias de diferentes tamaños
      for (int size : new int[] {10, 100, 1000, 10000}) {
        Problem problem = instanceGenerator.generateInstance(size);
        writer.write("Instancia de tamaño: " + size + "\n\n" );
        // Medir tiempo de ejecución del MergeSort
        long startTimeMergeSort = System.nanoTime();
        Solution solution = framework.executeAlgorithm(mergeSort, problem);
        long endTimeMergeSort = System.nanoTime();
        long elapsedTimeMergeSort = endTimeMergeSort - startTimeMergeSort;
        averageTimeMergeSort += elapsedTimeMergeSort;
        
        writer.write("MergeSort Execution Time (Size " + size + "): " + elapsedTimeMergeSort + " nanoseconds\n");
        writer.write("MergeSort Max Level Recursion Tree : " + framework.getRecursionTreeSize(mergeSort, problem) + "\n");
        writer.write("MergeSort Recurrence: " + framework.getRecurrence(mergeSort) + "\n\n");

        // Medir tiempo de ejecución del QuickSort
        long startTimeQuickSort = System.nanoTime();
        Solution solution2 = framework.executeAlgorithm(quickSort, problem);
        long endTimeQuickSort = System.nanoTime();
        long elapsedTimeQuickSort = endTimeQuickSort - startTimeQuickSort;
        averageTimeQuickSort += elapsedTimeQuickSort;
        
        writer.write("QuickSort Execution Time (Size " + size + "): " + elapsedTimeQuickSort + " nanoseconds\n");
        writer.write("QuickSort Max Level Recursion Tree : " + framework.getRecursionTreeSize(quickSort, problem) + "\n");
        writer.write("QuickSort Recurrence: " + framework.getRecurrence(quickSort) + "\n\n");

        // Medir tiempo de ejecucion para MergeSort con factor de division 3.
        long startTimeMergeSort3 = System.nanoTime();
        Solution solution3 = framework.executeAlgorithm(mergeSort3, problem);
        long endTimeMergeSort3 = System.nanoTime();
        long elapsedTimeMergeSort3 = endTimeMergeSort3 - startTimeMergeSort3;
        averageTimeMergeSort3 += elapsedTimeMergeSort3;
        writer.write("MergeSort3 Execution Time (Size " + size + "): " + elapsedTimeMergeSort3 + " nanoseconds\n");
        writer.write("MergeSort3 Max Level Recursion Tree : " + framework.getRecursionTreeSize(mergeSort3, problem) + "\n");
        
        // Imprimir la ecuación de recurrencia
        writer.write("MergeSort Recurrence: " + framework.getRecurrence(mergeSort) + "\n\n");

      }

      writer.write("Average MergeSort Execution Time: " + averageTimeMergeSort / 4 + " nanoseconds\n");
      writer.write("Average QuickSort Execution Time: " + averageTimeQuickSort / 4 + " nanoseconds\n");
      writer.write("Average MergeSort3 Execution Time: " + averageTimeMergeSort3 / 4 + " nanoseconds\n");

      // Generar una instancia de tamaño 10
      Problem problem = instanceGenerator.generateInstance(10);
      writer.write("\nInstancia de tamaño 10: " + Arrays.toString(problem.getData()) + "\n");
      Solution solution = framework.executeAlgorithm(mergeSort, problem);
      writer.write("MergeSort Solution: " + Arrays.toString(solution.getSolutionData()) + "\n");
      // Obtener un valor de la instancia en un indice aleatorio
      int randomIndex = (int) (Math.random() * 10);
      int searchedValue = problem.getData()[randomIndex];
      writer.write("Valor a buscar en la instancia: " + searchedValue + "\n");
      Problem sortedProblem = new Problem(solution.getSolutionData());
      Solution solution2 = framework.executeAlgorithm(sortedProblem, searchedValue, 0, problem.getData().length - 1);
      writer.write("Indice del valor buscado: " + Arrays.toString(solution2.getSolutionData()) + "\n");

      

      
      writer.close();
      System.out.println("Results have been written to results.txt");
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file: " + e.getMessage());
    }
  }
}
