public class SelectionSort {

    public static void sort(int[] array) {
        int l = array.length;

        for (int i = 0; i < l-1; i++) {
            for (int j = i+1; j < l; j++) {
                if(array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }

            for (int var : array) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
    }
}
