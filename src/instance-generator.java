/*
* Class to generate instances of the problem
*/
class InstanceGenerator {
  public Problem generateInstance(int size) {
    int kLimit = 10000;
    int[] data = new int[size];
    for (int i = 0; i < size; i++) {
      data[i] = (int) (Math.random() * kLimit); // Números aleatorios entre 0 y 99
    }
    return new Problem(data);
  }
}
