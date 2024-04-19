package Heap;

public class Main {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        
        System.out.print("Original array: ");
        HeapMethods.printArray(array);
        
        System.out.println("\nSorted array: ");
        
        int maxHeapMethod1 = HeapMethods.buildMaxHeapHeapify(array);
        int[] heapSortMethod1 = HeapMethods.heapSort(array); 
        int heapifySwapsMethod1 = heapSortMethod1[1]; 
        
        int totalSwapsMethod1 = maxHeapMethod1 + heapifySwapsMethod1; 
        System.out.println("\nMethod 1 (Heapify): " + maxHeapMethod1 + " swaps during max heap construction + " + heapifySwapsMethod1 + " swaps during heap sort = " + totalSwapsMethod1 + " total swaps"); 

        
        int buildMaxHeapOneByOneSwaps = HeapMethods.buildMaxHeapOneByOne(array); 
        int heapSortOneByOneSwaps = HeapMethods.heapSort(array)[1]; 
        
        int totalSwapsMethod2 = buildMaxHeapOneByOneSwaps + heapSortOneByOneSwaps; 
        System.out.println("Method 2 (OneByOne): " + buildMaxHeapOneByOneSwaps + " swaps during max heap construction + " + heapSortOneByOneSwaps + " swaps during heap sort = " + totalSwapsMethod2 + " total swaps"); 

        

        System.out.println("\nTotal swaps for Method 1 and Method 2: "  + (totalSwapsMethod1 + totalSwapsMethod2)); 
        
        int[] randomArray = HeapMethods.generateRandomArray(1000);
        int[] sortedArray = HeapMethods.generateSortedArray(1000);
        
        System.out.println("\nPART A"); 
        System.out.println("Sorted Array:"); 
        int[] sortedSwaps = HeapMethods.heapSort(sortedArray);
        System.out.println("Heapify swaps: " + sortedSwaps[0]); 
        System.out.println("One-by-One swaps: " + sortedSwaps[1]); 

        System.out.println("\n\nPART B"); 
        System.out.println("Random Array:"); 
        int[] randomSwaps = HeapMethods.heapSort(randomArray);
        System.out.println("Heapify swaps: " + randomSwaps[0]); 
        System.out.println("One-by-One swaps: " + randomSwaps[1]); 
    }
}
