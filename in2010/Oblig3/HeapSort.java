public class HeapSort {

    public static void sort(int[] array){
        int length = array.length;

        for (int i = length/2 - 1; i >= 0; i--) {
            buildHeap(array, length, i);
        }

        for (int i = length - 1; i >= 0; i--) {

            for (int var : array) {
                System.out.print(var + " ");
            }
            System.out.println();

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            buildHeap(array, i, 0);
        }
    }

    private static void buildHeap(int[] array, int length, int i) {

        int max = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < length && array[left] > array[max]) {
            max = left;
        }
        if(right < length && array[right] > array[max]) {
            max = right;
        }

        if (max != i) {
            int temp = array[i];
            array[i] = array[max];
            array[max] = temp;

            buildHeap(array, length, max);
        }
    }
}
