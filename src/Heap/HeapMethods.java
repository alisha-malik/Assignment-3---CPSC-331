package Heap;

/*
 * the following resources were used to develop the solution for this exercise: 
 * - https://www.baeldung.com/java-heap-sort
 * - https://www.algolist.net/Data_structures/Binary_heap/Insertion
 * - https://www.geeksforgeeks.org/insertion-and-deletion-in-heaps/
 * - https://www.geeksforgeeks.org/building-heap-from-array/
 * - https://www.educative.io/answers/how-to-build-a-heap-from-an-array
 * - https://stackoverflow.com/questions/68960310/java-priorityqueue-how-to-heapify-a-collection-with-a-custom-comparator
 * - https://www.geeksforgeeks.org/heap-sort/
 * - https://www.javatpoint.com/heap-sort
 */


// class that defines all methods for building and sorting heaps 
public class HeapMethods {
    
    // method to perform heap sort on the input array 
    public static int[] heapSort(int[] array) {
        int length = array.length;
        int[] swaps = new int[2];
        swaps[0] = buildMaxHeapHeapify(array);

        // defining heap sort method 
        for (int i = length - 1; i > 0; i--) {
        	// swap the root (max element) w/ the last element 
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            // 'Heapify' the remaining elements to maintain the heap properties 
            swaps[1] += heapify(array, i, 0);
        }
        
        return swaps;
    }

    
    // method to build a max heap using 'Heapify'
    public static int buildMaxHeapHeapify(int[] array) {
        int swaps = 0;
        int length = array.length;
        
        for (int i = length / 2 - 1; i >= 0; i--) {
            swaps += heapify(array, length, i);
        }
        
        return swaps;
    }
    
    // method to build a max heap using one by one insertion 
    public static int buildMaxHeapOneByOne(int[] array) {
        int swaps = 0;
        int length = array.length;
        
        for (int i = 1; i < length; i++) {
            int child = i;
            while (child > 0 && array[child] > array[(child - 1) / 2]) {
                int parent = (child - 1) / 2;
                int temp = array[child];
                array[child] = array[parent];
                array[parent] = temp;
                swaps++;
                child = parent;
            }
        }
        
        return swaps;
    }
    
    
    // method to 'Heapify' a subtree w/ a defined index 
    public static int heapify(int[] array, int length, int index) {
        int swaps = 0;
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < length && array[left] > array[largest])
            largest = left;
        if (right < length && array[right] > array[largest])
            largest = right;

        if (largest != index) {
            int temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;
            swaps++;
            swaps += heapify(array, length, largest);
        }

        return swaps;
    }
    
    // method to print the elements in an array 
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    // method to generate a random array of a specified size 
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }
    
    // method to generate a sorted array of a specified size 
    public static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
}
