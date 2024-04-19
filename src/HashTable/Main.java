package HashTable;

public class Main {
    public static void main(String[] args) {
        ChainedHashTable hashTable = new ChainedHashTable();

        // Step 1: Insert the students
        hashTable.insert(20500120, "Bob");
        hashTable.insert(20700200, "Alice");
        hashTable.insert(30100230, "Cathy");
        hashTable.insert(20200156, "Ali");

        // Step 2: Print the entire database
        System.out.println("Database after initial insertions:");
        System.out.println(hashTable.toString());

        // Step 3: Update Bob's name to Bobby
        hashTable.insert(20500120, "Bobby");
        System.out.println("Updated database after inserting Bobby:");
        System.out.println(hashTable.toString());

        // Step 4: Search for ID 20500120
        boolean found = hashTable.search(20500120);
        System.out.println("Student with ID 20500120 found: " + found);

        // Step 5: Retrieve the value associated with ID 20700200
        String name = hashTable.retrieve(20700200);
        System.out.println("Name associated with ID 20700200: " + name);

        // Step 6: Remove the student with ID 20700200
        hashTable.delete(20700200);
        System.out.println("Database after removing student with ID 20700200:");
        System.out.println(hashTable.toString());

        // Step 7: Attempt to remove the student with ID 20700200 again
        hashTable.delete(20700200);
        System.out.println("Attempt to remove student with ID 20700200 again:");

        // Step 8: Attempt to retrieve the value associated with ID 20700200
        String nameAfterDelete = hashTable.retrieve(20700200);
        System.out.println("Name associated with ID 20700200 after delete: " + nameAfterDelete);

        // Step 9: Print the updated database
        System.out.println("Database after updates:");
        System.out.println(hashTable.toString());
        
    }
}
