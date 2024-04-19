package Graph;

/*
 * the following resources were used to develop the solution for this exercise: 
 * - https://stackoverflow.com/questions/18092115/comparator-class-implementation-for-priority-queue-used-in-dijkstras-algorithm 
 * - https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
 * - https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
 * - https://www.baeldung.com/java-dijkstra
 * - https://dzenanhamzic.com/2016/12/14/dijkstras-algorithm-implementation-in-java/
 * - https://www.geeksforgeeks.org/comparator-interface-java/
 * - https://www.baeldung.com/java-comparator-comparable
 * - https://www.codecademy.com/resources/docs/java/comparator
 * - https://www.w3schools.com/java/java_user_input.asp
 * - https://www.programiz.com/java-programming/scanner
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * - exception handling has been implemented for string values (will prompt user to re-enter input) 
 * - exception handling has been added for integers outside of the predefined location range (will prompt user to re-enter input)
 */

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numWarehouses;
            while (true) {
                try {
                    System.out.print("Number of warehouses: ");
                    numWarehouses = scanner.nextInt();
                    break; // exit loop if input is valid 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input format! Please enter an integer.");
                    scanner.nextLine(); 
                }
            }

            int numLocations;
            while (true) {
                try {
                    System.out.print("Number of delivery locations: ");
                    numLocations = scanner.nextInt();
                    break; // exit loop if input is valid 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input format! Please enter an integer.");
                    scanner.nextLine(); 
                }
            }

            int numRoads;
            while (true) {
                try {
                    System.out.print("Number of roads connecting locations: ");
                    numRoads = scanner.nextInt();
                    // exit loop if input is valid 
                    break; 
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input format! Please enter an integer.");
                    scanner.nextLine(); 
                }
            }

            // initialize a list to store all roads 
            List<Road> roads = new ArrayList<>();

            for (int i = 0; i < numRoads; i++) {
                System.out.println("Enter road " + (i + 1) + " information:");
                int start, destination, distance;
                do {
                    try {
                        System.out.print("Start (between 0 and " + numLocations + "): ");
                        start = scanner.nextInt();
                        if (start < 0 || start > numLocations) {
                            System.out.println("Invalid input! Start must be between 0 and " + numLocations + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format! Please enter an integer.");
                        scanner.nextLine(); 
                        // repeat loop by setting an invalid value
                        start = -1; 
                    }
                } while (start < 0 || start > numLocations);

                do {
                    try {
                        System.out.print("Destination (between 0 and " + numLocations + "): ");
                        destination = scanner.nextInt();
                        if (destination < 0 || destination > numLocations) {
                            System.out.println("Invalid input! Destination must be between 0 and " + numLocations + ".");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format! Please enter an integer.");
                        scanner.nextLine(); 
                        // repeat loop by setting an invalid value
                        destination = -1; 
                    }
                } while (destination < 0 || destination > numLocations);

                System.out.print("Distance: ");
                distance = scanner.nextInt();
                roads.add(new Road(start, destination, distance));
            }

            // initialize a new city graph that includes the central warehouse 
            CityGraph cityGraph = new CityGraph(numLocations + 1); 

            // add roads to the graph
            for (Road road : roads) {
                cityGraph.addEdge(road.start, road.destination, road.distance);
            }

            // run Dijkstra's algorithm to find shortest paths -- starting from 0 (central warehouse)
            DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(cityGraph);
            dijkstra.runDijkstra(0); 

            // format output for shortest delivery routes and distances
            for (int location = 1; location <= numLocations; location++) {
                List<Integer> shortestRoute = dijkstra.getShortestRoute(location);
                int distance = dijkstra.getShortestDistance(location);
                if (distance == Integer.MAX_VALUE) {
                    System.out.println("Delivery Location " + location + " - Shortest Route: No route exists, Distance: Infinity");
                    System.out.println("(Location " + location + " is unreachable from the central warehouse)");
                } else {
                    System.out.print("Delivery Location " + location + " - Shortest Route: ");
                    
                    // initialize all outputs starting from 0 (denotes the central warehouse) 
                    System.out.print("0"); 
                    for (int i = 0; i < shortestRoute.size(); i++) {
                        System.out.print(" -> " + shortestRoute.get(i));
                    }
                    System.out.println(", Distance: " + distance);
                }
            }
        }
    }
}


// class for constructing the city graph 
class CityGraph {
    private final int numVertices;
    private final List<List<Edge>> adjacencyList;

    public CityGraph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // add an edge to the graph 
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    // get the number of vertices in the graph
    public int getNumVertices() {
        return numVertices;
    }

    // get the adjacency list of a vertex
    public List<Edge> getAdjacencyList(int vertex) {
        return adjacencyList.get(vertex);
    }
}

// class for representing an edge in  graph
class Edge {
	// initialize variables for the destination + weight 
    private final int destination;
    private final int weight;

    // constructor to create an edge w/ destination + weight 
    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    // method to get the destination of an edge 
    public int getDestination() {
        return destination;
    }

    // method to get the weight of an edge 
    public int getWeight() {
        return weight;
    }
}

// class represents Dijkstra's algorithm for getting the shortest path in a graph 
class DijkstraAlgorithm {
    private final CityGraph graph;
    private final int[] distances;
    private final List<Integer>[] shortestRoutes;

    @SuppressWarnings("unchecked")
    public DijkstraAlgorithm(CityGraph graph) {
        this.graph = graph;
        distances = new int[graph.getNumVertices()];
        shortestRoutes = new List[graph.getNumVertices()];
    }

    // method to run Dijkstra's algorithm from a specified source vertex (aka warehouse) 
    public void runDijkstra(int source) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        for (int i = 0; i < graph.getNumVertices(); i++) {
            distances[i] = Integer.MAX_VALUE;
            shortestRoutes[i] = new ArrayList<>();
        }
        
        // distance from the source to itself is 0
        distances[source] = 0;
        minHeap.offer(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int currentVertex = current.getDestination();

            List<Edge> neighbors = graph.getAdjacencyList(currentVertex);
            for (Edge neighbor : neighbors) {
                int newDistance = distances[currentVertex] + neighbor.getWeight();
                
                // update shortest route to neighbor 
                if (newDistance < distances[neighbor.getDestination()]) {
                    distances[neighbor.getDestination()] = newDistance;
                    List<Integer> shortestRouteToNeighbor = new ArrayList<>(shortestRoutes[currentVertex]);
                    shortestRouteToNeighbor.add(neighbor.getDestination());
                    shortestRoutes[neighbor.getDestination()] = shortestRouteToNeighbor;
                    
                    // add neighbor to priority queue 
                    minHeap.offer(new Edge(neighbor.getDestination(), newDistance));
                }
            }
        }
    }

    // method to get the shortest distance to a specified vertex
    public int getShortestDistance(int destination) {
        return distances[destination];
    }

    // method to get the shortest route to a specified vertex 
    public List<Integer> getShortestRoute(int destination) {
        return shortestRoutes[destination];
    }
}


// class representing a road b/w two locations 
class Road {
    int start;
    int destination;
    int distance;

    // constructor to initialize a road w/ start, destination and distance (weight) 
    public Road(int start, int destination, int distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
    }
}