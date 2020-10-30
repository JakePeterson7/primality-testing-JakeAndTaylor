import java.math.BigInteger;
import java.util.Random;

public class FermatTest{
    public static void main(String[] args){
        int s = 10; // security parameter
        // create random BigInteger of 512 bits
        Random rnd = new Random();
        int numBits = 512;
        BigInteger p = new BigInteger(numBits, rnd); // random BigInteger of bit length numBits
        BigInteger prime = BigInteger.probablePrime(numBits, rnd); // random prime of bit length numBits
        
        printBigInt(p);

    }
    public static void printBigInt(BigInteger x){
        System.out.println(x.toString());
    }
    
    public static boolean testPrimality(BigInteger n, int s){
        boolean prime = true;
        int counter = 0;  //counter for numbers that we've already tried.

        while(counter <= s){
            BigInteger a = pickRandomInRange(n);
            if(checkComposite(a, n)){
                prime = false;
            }
            s++;
        }
        return prime;
    }

    private static boolean checkComposite(BigInteger a, BigInteger p){
        return true;
    }

    private static BigInteger pickRandomInRange(BigInteger p) {
        return new BigInteger(512, new Random());
    }

}