import java.math.BigInteger;
import java.util.Random;

public class FermatTest {
    public static void main(String[] args) {
        int s = 3; // security parameter
        // create random BigInteger of 512 bits
        Random rnd = new Random();
        int numBits = 512;
        int numBits2 = 1024;

        BigInteger ninetySeven = new BigInteger("97");  // this is prime
        BigInteger ninetyEight = new BigInteger("98"); // this is not prime.

        // 512 bits
        BigInteger notPrime = new BigInteger(numBits, rnd); // random BigInteger of bit length numBits
        notPrime.multiply(BigInteger.TWO);                  // multiply by two to make sure its not prime.
        BigInteger notPrimeB = new BigInteger(numBits, rnd); 
        notPrimeB.multiply(BigInteger.TWO);                  
        BigInteger prime = BigInteger.probablePrime(numBits, rnd); // random prime of bit length numBits
        BigInteger primeB = BigInteger.probablePrime(numBits, rnd); 

        // 1024 bits
        BigInteger notPrime2 = new BigInteger(numBits2, rnd); // random BigInteger of bit length numBits2
        notPrime.multiply(BigInteger.TWO);                  // multiply by two to make sure its not prime.
        BigInteger notPrimeC = new BigInteger(numBits2, rnd); 
        notPrimeC.multiply(BigInteger.TWO);                  
        BigInteger prime2 = BigInteger.probablePrime(numBits2, rnd); // random prime of bit length numBits2
        BigInteger primeC = BigInteger.probablePrime(numBits2, rnd); 


        // Start testing
        System.out.println("Small ints (97: prime & 98: not prime)");
        System.out.println(testPrimality(ninetySeven, s));
        System.out.println(testPrimality(ninetyEight, s));
        System.out.println();


        System.out.println("Big ints to check (512 bits)");

        System.out.println("Non-primes:");
        System.out.println(testPrimality(notPrime, s));
        System.out.println(testPrimality(notPrimeB, s));

        System.out.println("Primes:");
        System.out.println(testPrimality(prime, s));
        System.out.println(testPrimality(primeB, s));

        System.out.println();
        //------------------------------------------------------
        System.out.println("Big ints to check (1024 bits)");

        System.out.println("Non-primes:");
        System.out.println(testPrimality(notPrime2, s));
        System.out.println(testPrimality(notPrimeC, s));


        System.out.println("Primes:");
        System.out.println(testPrimality(prime2, s));
        System.out.println(testPrimality(primeC, s));

        System.out.println();

        // Test Carmichael numbers
        //  all are being found to be composite.
        BigInteger carmichael1 = new BigInteger("1105");
        BigInteger carmichael2 = new BigInteger("15841");
        BigInteger carmichael3 = new BigInteger("75361");

        System.out.println("Test Carmichael numbers:");
        System.out.println(testPrimality(carmichael1, s));
        System.out.println(testPrimality(carmichael2, s));
        System.out.println(testPrimality(carmichael3, s));

        System.out.println();

    }

    public static void printBigInt(BigInteger x) {
        System.out.println(x.toString());
    }

    // This primality test will check if n is prime or not.
    // Will return true if prime.
    public static boolean testPrimality(BigInteger n, int s) {
        boolean prime = true;
        int counter = 0; // counter for numbers that we've already tried.

        while (counter <= s) {
            BigInteger a = pickRandomInRange(n);
            // System.out.print("a: ");
            //printBigInt(a);
            if (checkComposite(a, n)) {
                // System.out.print("a: ");
                // printBigInt(a);
                prime = false;
            }
            counter++;
        }
        return prime;
    }

    private static boolean checkComposite(BigInteger a, BigInteger n) {
        boolean composite = false;
        BigInteger diff = n.subtract(BigInteger.ONE);
        if (!a.modPow(diff, n).equals(BigInteger.ONE)) {
            composite = true;
        }
        return composite;
    }

    private static BigInteger pickRandomInRange(BigInteger p) {

        BigInteger max = p.subtract(BigInteger.ONE);
        BigInteger min = new BigInteger("2");
        BigInteger range = p.subtract(min);
        Random rand = new Random();
        int len = max.bitLength();
        BigInteger result = new BigInteger(len, rand);

        if (result.compareTo(min) < 0)
            result = result.add(min);
        if (result.compareTo(range) >= 0)
            result = result.mod(range).add(min);
        return result;
    }

}