package HashTable;

import java.util.LinkedList;

// class representing a chained HashTable
public class ChainedHashTable {
    // class representing a linked hash node 
    class LinkedHash {
        String key; 		// key of the hash node 
        int value; 			// value associated w/ key 
        LinkedHash next; 	// reference to the next code (collision handling) 

        // constructor for the linked hash nodes (chaining) 
        LinkedHash(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // initialize variable for the length of the HashTable
    private int LEN; 
    
    // initialize array of linked lists to handle collisions (chaining) 
    private LinkedList<Student>[] table; 

    // constructor for ChainedHashTable
    @SuppressWarnings("unchecked")
    public ChainedHashTable() {
        LEN = 8; // Setting LEN to 8
        table = (LinkedList<Student>[]) new LinkedList[LEN];

        // initializing the hash table with empty linked lists
        for (int i = 0; i < LEN; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // method to get hash value of ID -- position in the array
    private int hashValue(int ID) {
        return ID % LEN;
    }
    

    // method to search for a student using ID number 
    public boolean search(int ID) {
        int index = hashValue(ID);
        for (Student student : table[index]) {
            if (student.ID == ID) {
            	// return true if the student is found 
                return true; 
            }
        }
        // return false if student is not found 
        return false; 
    }

    // method to retrieve the ID number of a student (if present in table) 
    public String retrieve(int ID) {
        int index = hashValue(ID);
        for (Student student : table[index]) {
            if (student.ID == ID) {
            	// return the name of the student if found in the table 
                return student.name; 
            }
        }
        return "No student with ID " + ID + " found in the hash table."; 
    }

    // method to insert a student into the HashTable 
    public void insert(int ID, String name) {
        int index = hashValue(ID);
        for (Student student : table[index]) {
            if (student.ID == ID) {
            	// update the name if a student already exists in the table 
                student.name = name; 
                return;
            }
        }
        // add a new student if they do not exist in the list 
        table[index].add(new Student(ID, name)); 
    }

    // method to delete a student from the HashTable (by ID number)
    public void delete(int ID) {
        int index = hashValue(ID);
        LinkedList<Student> list = table[index];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).ID == ID) {
            	// remove the student if the student is found in the table 
                list.remove(i); 
                return;
            }
        }
        System.out.println("No student with ID " + ID + " found in the hash table."); 
    }

    // string builder to print the contents of the HashTable 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LEN; i++) {
            sb.append(i).append(": [");
            for (int j = 0; j < table[i].size(); j++) {
                sb.append(table[i].get(j));
                if (j < table[i].size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
