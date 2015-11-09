package algorithms.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HeapSort {
    private static void sort(int[] array) {
        heapify(array, array.length);
        heapSort(array);
    }

    private static void heapSort(int[] array) {
        int heapEnd = array.length;
        while (heapEnd > 0) {
            int max = array[0];
            array[0] = array[heapEnd - 1];
            array[heapEnd - 1] = max;
            heapify(array, --heapEnd);
        }
    }

    private static void fixHeap(int[] array, int heapSize) {
        int parentIndex = 0;
        int leftIndex = 1;
        int rightIndex = 2;
        for (; parentIndex < heapSize;) {
            int newParentIndex = -1;
            if (leftIndex < heapSize && array[parentIndex] < array[leftIndex]) {
                int temp = array[leftIndex];
                array[leftIndex] = array[parentIndex];
                array[parentIndex] = temp;
                newParentIndex = leftIndex;
            }
            if (rightIndex < heapSize && array[parentIndex] < array[rightIndex]) {
                int temp = array[rightIndex];
                array[rightIndex] = array[parentIndex];
                array[parentIndex] = temp;
                newParentIndex = rightIndex;
            }
            if (newParentIndex == -1) {
                break;
            }
            parentIndex = newParentIndex;
            leftIndex = 2 * parentIndex + 1;
            rightIndex = leftIndex + 1;
        }
    }

    private static void heapify(int[] heap, int size) {
        for (int i = size - 1; i > 0; i -= 2) {
            maxifyParent(heap, i);
            if (i == size - 1 && size % 2 == 0) {
                ++i;
            }
        }
    }

    private static void maxifyParent(int[] heap, int childIndex) {
        // odd means that the array has no right child at the end so the comparison is simplified
        boolean missingRight = childIndex % 2 != 0;
        int leftIndex = missingRight ? childIndex : childIndex - 1;
        int parentIndex = (leftIndex - 1) / 2;
        int maxIndex;
        if (missingRight) {
            maxIndex = heap[parentIndex] > heap[leftIndex] ? parentIndex : leftIndex;
        } else {
            int rightIndex = childIndex;
            maxIndex = max(heap, parentIndex, leftIndex, rightIndex);
        }
        if (parentIndex != maxIndex) {
            int max = heap[maxIndex];
            heap[maxIndex] = heap[parentIndex];
            heap[parentIndex] = max;
        }
    }

    private static int max(int[] array, int index1, int index2, int index3) {
        int maxIndex = array[index1] > array[index2] ? index1 : index2;
        return array[maxIndex] > array[index3] ? maxIndex : index3;
    }

    private static int[] convert(String[] stringArray) {
        int[] result = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            result[i] = Integer.parseInt(stringArray[i]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] stringArray = line.split(",");
            int[] array = convert(stringArray);
            System.out.println("Before: " + Arrays.toString(array));
            sort(array);
            System.out.println("After: " + Arrays.toString(array));
            System.out.println();
        }
    }
}
