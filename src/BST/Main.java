package BST;

import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // generate a random int 'n' between 0-300
        int n = RandomNumberGenerator.generateRandomInt(300);

        // generate a random even int 'm' between 100-200
        int m = RandomNumberGenerator.generateRandomEvenInt(2,n);

        // print the generated values of 'n' and 'm' for testing
        System.out.println("Random integer n between 0 and 300: " + n);
        System.out.println("Random even integer m between 0 and n: " + m + "\n");

        // create a new instance of the binary search tree
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // create a loop that adds 'm' amount of random numbers to an empty BST
        int generatedCountM = 0;
        while (generatedCountM < m) {
            // generate a random number that includes 'n'
            int randomNumber = RandomNumberGenerator.generateRandomInt(n);
            // check if the element already exists in the BST
            if (!bst.contains(randomNumber)) {
                // add the 'randomNumber' to the BST
                bst.add(randomNumber);
                generatedCountM++;
            }
        }
        
        // print the number of nodes in the BST  
        int initialHeightBST = bst.findHeight();
        System.out.println("The initial height of the initial BST is " + initialHeightBST);
        
        // remove odd elements from the BST and create a new BST with even values
        BinarySearchTree<Integer> evenBST = bst.removeOddAndCreateEvenBST(); 
        
        int initialHeightEven = evenBST.findHeight();
        System.out.println("The initial height of the BST is " + initialHeightEven);

        int k = RandomNumberGenerator.generateRandomNumberInRange(50, 150);
        System.out.println("Random integer n between 50 and 150: " + k);

        List<Integer> heights = new ArrayList<>();
        List<Double> times = new ArrayList<>(); 

        int generatedCountK = 0;
        while (generatedCountK < k) {
            int randomNumber = RandomNumberGenerator.generateRandomInt(n);
            if (!evenBST.contains(randomNumber)) {
                long startTime = System.nanoTime();
                evenBST.add(randomNumber);
                long endTime = System.nanoTime();
                double elapsedTimeMs = (double) (endTime - startTime) / 1_000_000.0; 
                int height = evenBST.findHeight();
                System.out.println("Tree Height " + height + ": " + elapsedTimeMs + " milliseconds");
                heights.add(height);
                times.add(elapsedTimeMs); 
                generatedCountK++;
            }
        }
        plotGraph(heights, times);
    } 
    
    // add case handling for situations in w/c m>n and totalNodes event BST + k>n 
    
    private static void plotGraph(List<Integer> heights, List<Double> times) {

    	 // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("BST Height vs. Time").xAxisTitle("x: time (nanoseconds)").yAxisTitle("y: height").build();

        // Series
        chart.addSeries("Scatter Data", times, heights).setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}