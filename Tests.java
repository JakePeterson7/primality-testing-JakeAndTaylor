import java.math.BigInteger;
import java.util.Random;

public class Tests {

	// Test Carmichael numbers
	public static void carmichaelTests(int s) {
	
	    BigInteger carmichael1 = new BigInteger("1105");
	    BigInteger carmichael2 = new BigInteger("15841");
	    BigInteger carmichael3 = new BigInteger("75361");
	
	    System.out.println("Test Carmichael numbers:");
	    System.out.println(FermatTest.testPrimality(carmichael1, s));
	    System.out.println(FermatTest.testPrimality(carmichael2, s));
	    System.out.println(FermatTest.testPrimality(carmichael3, s));
	
	    System.out.println();
	}

	public static void primalityTests(int s){
	    // create random BigInteger of 512 bits
	    Random rnd = new Random();
	    int numBits = 512;
	    int numBits2 = 1024;
	
	    BigInteger ninetySeven = new BigInteger("97"); // this is prime
	    BigInteger ninetyEight = new BigInteger("98"); // this is not prime.
	
	    // 512 bits
	    BigInteger notPrime = new BigInteger(numBits, rnd); // random BigInteger of bit length numBits
	    notPrime.multiply(BigInteger.TWO); // multiply by two to make sure its not prime.
	    BigInteger notPrimeB = new BigInteger(numBits, rnd);
	    notPrimeB.multiply(BigInteger.TWO);
	    BigInteger prime = BigInteger.probablePrime(numBits, rnd); // random prime of bit length numBits
	    BigInteger primeB = BigInteger.probablePrime(numBits, rnd);
	
	    // 1024 bits
	    BigInteger notPrime2 = new BigInteger(numBits2, rnd); // random BigInteger of bit length numBits2
	    notPrime.multiply(BigInteger.TWO); // multiply by two to make sure its not prime.
	    BigInteger notPrimeC = new BigInteger(numBits2, rnd);
	    notPrimeC.multiply(BigInteger.TWO);
	    BigInteger prime2 = BigInteger.probablePrime(numBits2, rnd); // random prime of bit length numBits2
	    BigInteger primeC = BigInteger.probablePrime(numBits2, rnd);
	
	    smallNumberTest(s, ninetySeven, ninetyEight);
	    testFiveTwelvePrimality(s, notPrime, notPrimeB, prime, primeB);
	    testTenTwentyFourPrimality(s, notPrime2, notPrimeC, prime2, primeC);
	}

    private static void testTenTwentyFourPrimality(int s, BigInteger notPrime2, BigInteger notPrimeC, BigInteger prime2,
            BigInteger primeC) {
        System.out.println("Big ints to check (1024 bits)");
	
	    System.out.println("Non-primes:");
	    System.out.println(FermatTest.testPrimality(notPrime2, s));
	    System.out.println(FermatTest.testPrimality(notPrimeC, s));
	
	    System.out.println("Primes:");
	    System.out.println(FermatTest.testPrimality(prime2, s));
	    System.out.println(FermatTest.testPrimality(primeC, s));
	
	    System.out.println();
    }

    private static void testFiveTwelvePrimality(int s, BigInteger notPrime, BigInteger notPrimeB, BigInteger prime,
            BigInteger primeB) {
        System.out.println("Big ints to check (512 bits)");
	
	    System.out.println("Non-primes:");
	    System.out.println(FermatTest.testPrimality(notPrime, s));
	    System.out.println(FermatTest.testPrimality(notPrimeB, s));
	
	    System.out.println("Primes:");
	    System.out.println(FermatTest.testPrimality(prime, s));
	    System.out.println(FermatTest.testPrimality(primeB, s));
	
	    System.out.println();
    }

    private static void smallNumberTest(int s, BigInteger ninetySeven, BigInteger ninetyEight) {
        // Start testing
	    System.out.println("Small ints (97: prime & 98: not prime)");
	    System.out.println(FermatTest.testPrimality(ninetySeven, s));
	    System.out.println(FermatTest.testPrimality(ninetyEight, s));
	    System.out.println();
    }
    
}
