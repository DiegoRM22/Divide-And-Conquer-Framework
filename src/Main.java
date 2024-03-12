import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Execution mode:");
    System.out.println("1. Normal Mode");
    System.out.println("2. Debug Mode");
    int mode = scanner.nextInt();

    if (mode == 1) {
      normalMode();
    } else if (mode == 2) {
      debugMode();
    } else {
      System.out.println("Invalid mode");
    }

    scanner.close();
  }

  private static void normalMode() {
    InstanceGenerator instanceGenerator = new InstanceGenerator();
    DivideAndConquerFramework framework = new DivideAndConquerFramework();

    System.out.println("Algorithm to execute:");
    System.out.println("1. Binary Search");
    System.out.println("2. Hanoi");
    Scanner scanner = new Scanner(System.in);
    int algorithmChoice = scanner.nextInt();

    if (algorithmChoice == 1) {
      executeBinarySearchNormalMode(instanceGenerator, framework);
    } else if (algorithmChoice == 2) {
      executeHanoiNormalMode(instanceGenerator, framework);
    } else {
      System.out.println("Invalid algorithm");
    }

    scanner.close();
  }

  private static void debugMode() {
    InstanceGenerator instanceGenerator = new InstanceGenerator();
    DivideAndConquerFramework framework = new DivideAndConquerFramework();

    System.out.println("Algorithm to execute:");
    System.out.println("1. Binary Search");
    System.out.println("2. Hanoi");
    Scanner scanner = new Scanner(System.in);
    int algorithmChoice = scanner.nextInt();

    if (algorithmChoice == 1) {
      executeBinarySearchDebugMode(instanceGenerator, framework, scanner);
    } else if (algorithmChoice == 2) {
      executeHanoiDebugMode(instanceGenerator, framework, scanner);
    } else {
      System.out.println("Invalid algorithm");
    }

    scanner.close();
  }

  private static void executeBinarySearchNormalMode(InstanceGenerator instanceGenerator, DivideAndConquerFramework framework) {
    QuickSort quickSort = new QuickSort();
    int[] instanceSizes = {10, 100, 1000}; // Instance sizes to run binary search on

    for (int size : instanceSizes) {
      Problem problem = instanceGenerator.generateInstance(size);
      Solution sortedSolution = framework.executeAlgorithm(quickSort, problem);
      Problem sortedProblem = new Problem(sortedSolution.getSolutionData());
      int searchedValue = sortedProblem.getData()[(int) (Math.random() * size)]; // Choose a random value from the sorted array
      long startTime = System.nanoTime();
      Solution binarySearchSolution = framework.executeAlgorithm(sortedProblem, searchedValue, 0, sortedProblem.getData().length - 1);
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;

      System.out.println("Binary Search execution time (Size " + size + "): " + elapsedTime + " nanoseconds");
    }
  }

  private static void executeHanoiNormalMode(InstanceGenerator instanceGenerator, DivideAndConquerFramework framework) {
    Hanoi hanoiSolver = new Hanoi();
    int[] diskCounts = {3, 5, 7}; // Disk counts to run Hanoi on

    for (int disks : diskCounts) {
      Problem hanoiProblem = instanceGenerator.generateInstance(disks);
      long startTime = System.nanoTime();
      Solution hanoiSolution = framework.executeHanoi(hanoiSolver, disks, "A", "C", "B");
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;

      System.out.println("Hanoi execution time (Disks " + disks + "): " + elapsedTime + " nanoseconds");
    }
  }

  private static void executeBinarySearchDebugMode(InstanceGenerator instanceGenerator, DivideAndConquerFramework framework, Scanner scanner) {
    System.out.println("Instance size:");
    int size = scanner.nextInt();
    QuickSort quickSort = new QuickSort();
    Problem problem = instanceGenerator.generateInstance(size);
    Solution sortedSolution = framework.executeAlgorithm(quickSort, problem);
    Problem sortedProblem = new Problem(sortedSolution.getSolutionData());
    System.out.println("Unsorted instance:");
    System.out.println(Arrays.toString(problem.getData()));
    System.out.println("Sorted instance:");
    System.out.println(Arrays.toString(sortedProblem.getData()));
    System.out.println("Enter the value to search:");
    int searchedValue = scanner.nextInt();
    Solution binarySearchSolution = framework.executeAlgorithm(sortedProblem, searchedValue, 0, sortedProblem.getData().length - 1);
    int[] resultIndexes = binarySearchSolution.getSolutionData();

    if (resultIndexes[0] != -1) {
      System.out.println("The value " + searchedValue + " was found at index " + resultIndexes[0]);
    } else {
      System.out.println("The value " + searchedValue + " was not found in the instance.");
    }
  }

  private static void executeHanoiDebugMode(InstanceGenerator instanceGenerator, DivideAndConquerFramework framework, Scanner scanner) {
    Hanoi hanoiSolver = new Hanoi();
    System.out.println("Select number of disks:");
    int numberOfDisks = scanner.nextInt();
    Problem hanoiProblem = instanceGenerator.generateInstance(numberOfDisks);
    Solution hanoiSolution = framework.executeHanoi(hanoiSolver, numberOfDisks, "A", "C", "B");
    System.out.println("Hanoi movements:");
    framework.printHanoiMovements();
  }
}
