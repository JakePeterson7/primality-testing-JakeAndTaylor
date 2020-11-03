import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;


public class FermatTest {
    public static void main(String[] args) throws IOException {
        int s = 10; // security parameter
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

        //Finding Large Primes
        //512 bits
        FileWriter file = new FileWriter("results.txt"); //writes some results to a separate file.
        file.write("512 bits\n");
        BigInteger findingPrime = new BigInteger(numBits, rnd);
        int i = 0;
        while (invertedTestPrimality(findingPrime, s)){
            findingPrime = new BigInteger(numBits, rnd);
            i++;
        } System.out.println(findingPrime + " is the found prime!");
        System.out.println(testPrimality(findingPrime, s));
        System.out.println(i + " candidates were tried.");
        file.write(findingPrime + " is the found prime!\n" +  i + " candidates were tried.\n" );
        file.close();



        BigInteger findingPrime2 = new BigInteger(numBits, rnd);
        file = new FileWriter("results.txt", true);
        int j = 0;
        while (invertedTestPrimality(findingPrime2, s)){
            findingPrime2 = new BigInteger(numBits, rnd);
            j++;
        } System.out.println(findingPrime2 + " is the found prime!");
        System.out.println(testPrimality(findingPrime2, s));
        System.out.println(j + " candidates were tried.");
        file.write(findingPrime2 + " is the found prime!\n" +  j + " candidates were tried.\n" );
        file.close();

        //1024 bits
        file = new FileWriter("results.txt", true);
        file.write("1024 bits\n");
        BigInteger findingPrime3 = new BigInteger(numBits2, rnd);
        int k = 0;
        while (invertedTestPrimality(findingPrime3, s)){
            findingPrime3 = new BigInteger(numBits, rnd);
            k++;
        } System.out.println(findingPrime3 + " is the found prime!");
        System.out.println(testPrimality(findingPrime3, s));
        System.out.println(k + " candidates were tried.");
        file.write(findingPrime3 + " is the found prime!\n" +  k + " candidates were tried.\n" );
        file.close();

        BigInteger findingPrime4 = new BigInteger(numBits2, rnd);
        file = new FileWriter("results.txt", true);
        int l = 0;
        while (invertedTestPrimality(findingPrime4, s)){
            findingPrime4 = new BigInteger(numBits, rnd);
            l++;
        } System.out.println(findingPrime4 + " is the found prime!");
        System.out.println(testPrimality(findingPrime4, s));
        System.out.println(l + " candidates were tried.");
        file.write(findingPrime4 + " is the found prime!\n" +  l + " candidates were tried.\n" );
        file.close();
        }



    public static void printBigInt(BigInteger x) {
        System.out.println(x.toString());
    }

    // This primality test will check if n is prime or not.
    // Will return true if prime.
    public static boolean testPrimality(BigInteger n, int s) {
        boolean prime = true;
        int counter = 0; // counter for numbers that we've already tried.

        while (counter < s && prime == true) {
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
        System.out.println("This was tested " + counter + " times.");
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

    private static boolean invertedTestPrimality(BigInteger n, int s) throws IOException {
        boolean prime = true;
        int counter = 0; // counter for numbers that we've already tried.
        FileWriter file = new FileWriter("results.txt", true);

        while (counter < s && prime) {
            BigInteger a = pickRandomInRange(n);
            // System.out.print("a: ");
            //printBigInt(a);
            if (checkComposite(a, n)) {
            // System.out.print("a: ");
            // printBigInt(a);
            prime = false;
            file.write(a + " the number of candidates a for each random candidate.\n");
        }
        counter++;
    }
    file.write(counter + " the average number of operations per candidate a.\n\n");
        file.close();
        return !prime;


}

}
