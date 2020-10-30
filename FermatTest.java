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
        BigInteger ninetySeven = new BigInteger("97");
        BigInteger ninetyEight = new BigInteger("98");

        System.out.println("Big ints to check");
        //non-primes
        printBigInt(ninetyEight);
        printBigInt(p);
        //primes
        printBigInt(ninetySeven);
        printBigInt(prime);

        System.out.println("Non-primes");
        //System.out.println(testPrimality(p, s));
        System.out.println(testPrimality(ninetyEight, s));

        System.out.println("Primes:");
      //  System.out.println(testPrimality(prime, s));
        System.out.println(testPrimality(ninetySeven, s));


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
            printBigInt(a);
            if(checkComposite(a, n)){
                prime = false;
            }
            counter++;
        }
        return prime;
    }

    private static boolean checkComposite(BigInteger a, BigInteger n){
        boolean composite = true;
        BigInteger one = new BigInteger("1");
        BigInteger diff = n.subtract(one);
        if(a.modPow(diff, n).equals(one)){
            composite = false;
        }
        return composite;
    }

    private static BigInteger pickRandomInRange(BigInteger p) {
        //find range
        BigInteger two = new BigInteger("2");
        BigInteger range = p.subtract(two);
        // create RNG and find bit length of the range
        Random randNum = new Random();
        int length = range.bitLength();
        //construct result using the bit length of the range and the RNG.
        BigInteger result = new BigInteger(length, randNum);

        return result;
    }

}