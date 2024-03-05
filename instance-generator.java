/*
 * Class to generate instances of the problem
 */
class InstanceGenerator {
    public Problem generateInstance(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) (Math.random() * 100); // Números aleatorios entre 0 y 99
        }
        return new Problem(data);
    }
}
