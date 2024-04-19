package HashTable;

// class representing a student (e.g. name & ID number)
public class Student {
    int ID; 		// student ID 
    String name; 	// student name 

    // constructor to initialize a student w/ ID and name 
    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    // string builder to represent a student as a string 
    @Override
    public String toString() {
        return ID + ":" + name; 
    }
}