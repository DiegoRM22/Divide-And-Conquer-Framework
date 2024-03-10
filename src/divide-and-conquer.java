
import java.util.Arrays;


/**
 * Divide and conquer algorithm interface.
 */
interface DivideAndConquerAlgorithm {
  Solution solveDirectly(Problem p);
  Problem[] divide(Problem p);
  Solution combine(Solution[] solutions);
  boolean small(Problem p);
  int getRecursionTreeSize(Problem p);
}