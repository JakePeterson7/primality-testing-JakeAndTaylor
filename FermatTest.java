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
    // This primality test will check if n is prime or not.
    // Will return true if prime.
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

    private static boolean checkComposite(BigInteger a, BigInteger n){
        boolean composite = true;
        BigInteger one = new BigInteger("1");
        BigInteger diff = n.subtract(one);
        if(a.modPow(diff, n).equals(1) ){
            composite = false;
        }
        return composite;
    }
    // Stub
    private static BigInteger pickRandomInRange(BigInteger p) {
        return new BigInteger(512, new Random());
    }

}