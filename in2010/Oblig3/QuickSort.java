public class QuickSort {

    public static void sort(int[] array, int min, int max) {

        if (min < max) {
            int partition = partition(array, min, max);
            sort(array, min, partition - 1);
            sort(array, partition + 1, max);
        }
    }

    private static int partition(int[] array, int min, int max) {

        int pivot = array[max];
        int i = min - 1;
        for (int j = min; j < max; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[max];
        array[max] = temp;

        for (int var : array) {
            System.out.print(var + " ");
        }
        System.out.println();

        return i + 1;
    }
}
