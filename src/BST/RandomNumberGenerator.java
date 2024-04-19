package BST;

import java.util.Random;

public class RandomNumberGenerator {
	
	// private static final Random rand = new Random();
    // function to generate a random 'int' b/w 0 and max
    public static int generateRandomInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1);
    }

    // function to generate a random even 'int' between 0 and max
    public static int generateRandomEvenInt(int min, int max) {
        Random rand = new Random();
        int randomNumber;
        do {
            randomNumber = rand.nextInt(max + 1);
            // keep generating until an even number is generated 
        } while (randomNumber % 2 != 0); 
        return randomNumber;
    }
    
    // function to generate a random number b/w 50-150
    public static int generateRandomNumberInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
