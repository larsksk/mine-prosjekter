import java.util.Arrays;
import java.util.Random;

public class TestSort {
    private static int[] randomArray;
    private static int[] ascendingArray;
    private static int[] descendingArray;
    private static int okTests;
    private static int tests;

    public static void main(String[] args) {
        patternTest(10, 0, 9);
        //speedTest(10, 0, 9);
    }

    private static void speedTest(int length, int min, int max) {
        initArrays(length, min, max);
        System.out.println("(random)\n(ascending)\n(descending)");
        speedSelection();
        speedHeap();
        speedQuick(length);
        speedJavaSort();
    }

    private static void speedSelection() {
        long t;
        double tid;

        int[] randomArraySelection = randomArray.clone();
        int[] ascendingArraySelection = ascendingArray.clone();
        int[] descendingArraySelection = descendingArray.clone();

        System.out.println("Selection-sort:");

        t = System.nanoTime(); //nanosekunder
        SelectionSort.sort(randomArraySelection);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        SelectionSort.sort(ascendingArraySelection);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        SelectionSort.sort(descendingArraySelection);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);
    }

    private static void speedHeap() {
        long t;
        double tid;

        int[] randomArrayHeap = randomArray.clone();
        int[] ascendingArrayHeap = ascendingArray.clone();
        int[] descendingArrayHeap = descendingArray.clone();

        System.out.println("Heap-sort:");

        t = System.nanoTime(); //nanosekunder
        HeapSort.sort(randomArrayHeap);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        HeapSort.sort(ascendingArrayHeap);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        HeapSort.sort(descendingArrayHeap);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);
    }

    private static void speedQuick(int length) {
        long t;
        double tid;

        int[] randomArrayQuick = randomArray.clone();
        int[] ascendingArrayQuick = ascendingArray.clone();
        int[] descendingArrayQuick = descendingArray.clone();

        System.out.println("Quick-sort:");

        t = System.nanoTime(); //nanosekunder
        QuickSort.sort(randomArrayQuick, 0, length - 1);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        QuickSort.sort(ascendingArrayQuick, 0, length - 1);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        QuickSort.sort(descendingArrayQuick, 0, length - 1);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);
    }

    private static void speedJavaSort() {
        long t;
        double tid;

        System.out.println("Java.sort:");

        t = System.nanoTime(); //nanosekunder
        Arrays.sort(randomArray);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        Arrays.sort(ascendingArray);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);

        t = System.nanoTime(); //nanosekunder
        Arrays.sort(descendingArray);
        tid = ( System.nanoTime() - t ) / 1000000.0; //millisekunder
        System.out.println(tid);
    }

    private static void patternTest(int length, int min, int max) {
        initArrays(length, min, max);

        System.out.println("SELECTION-SORT:\n");
        System.out.println("(random array)");

        testSelection(randomArray);

        System.out.println("\n--------------------\n");
        System.out.println("(ascending array)");

        testSelection(ascendingArray);

        System.out.println("\n--------------------\n");
        System.out.println("(descending array)");

        testSelection(descendingArray);


        initArrays(length, min, max);

        System.out.println("____________________");
        System.out.println("HEAP-SORT:\n");
        System.out.println("(random array)");

        testHeap(randomArray);

        System.out.println("\n--------------------\n");
        System.out.println("(ascending array)");

        testHeap(ascendingArray);

        System.out.println("\n--------------------\n");
        System.out.println("(descending array)");

        testHeap(descendingArray);


        initArrays(length, min, max);

        System.out.println("____________________");
        System.out.println("QUICK-SORT:\n");
        System.out.println("(random array)");

        testQuick(randomArray);

        System.out.println("\n--------------------\n");
        System.out.println("(ascending array)");

        testQuick(ascendingArray);

        System.out.println("\n--------------------\n");
        System.out.println("(descending array)");

        testQuick(descendingArray);


        System.out.println("\n\n" + okTests + "/" + tests + " TESTS WAS SUCCESSFUL");
    }


    private static void initArrays(int length, int min, int max) {
        randomArray = initRandom(length, min, max);
        //randomArray = uniqueArray(length);

        ascendingArray = randomArray.clone();
        Arrays.sort(ascendingArray);

        descendingArray = ascendingArray.clone();
        reverseArray(descendingArray, length);
    }

    private static int[] initRandom(int length, int min, int max) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = (random.nextInt((max - min) + 1)) + min;

            /*
            int randomNr = random.nextInt();
            if (randomNr < 0) {
                randomNr = randomNr*-1;
            }
            array[i] = randomNr;
            */

        }
        return array;
    }

    private static void reverseArray(int[] array, int length) {
        for (int i = 0; i < length/2; i++) {
            int temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
    }

    private static int[] uniqueArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        Random random = new Random();
        for (int i = length - 1; i >= 0; i--) {
            int j = random.nextInt(length);

            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

        return array;
    }

    private static void testSelection(int[] array){

        System.out.println("Before sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        System.out.println("\n\nSorting iterations:");
        SelectionSort.sort(array);

        System.out.println("\nAfter sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        tests += 1;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]) {
                System.out.println("\nArray is NOT sorted!");
                return;
            }
        }
        System.out.println("\nOK");
        okTests += 1;
    }

    private static void testHeap(int[] array) {

        System.out.println("Before sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        System.out.println("\n\nSorting iterations:");
        HeapSort.sort(array);

        System.out.println("\nAfter sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        tests += 1;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]) {
                System.out.println("\nArray is NOT sorted!");
                return;
            }
        }
        System.out.println("\nOK");
        okTests += 1;
    }

    private static void testQuick(int[] array) {
        System.out.println("Before sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        System.out.println("\n\nSorting iterations:");
        QuickSort.sort(array, 0, array.length-1);

        System.out.println("\nAfter sorting:");
        for (int var : array) {
            System.out.print(var + " ");
        }

        tests += 1;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]) {
                System.out.println("\nArray is NOT sorted!");
                return;
            }
        }
        System.out.println("\nOK");
        okTests += 1;
    }
}
