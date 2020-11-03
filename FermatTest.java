import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class FermatTest {
    public static void main(String[] args) throws IOException {

        Random rnd = new Random();
        int numBits = 512;
        int numBits2 = 1024;
        int s = 10;

        Tests.primalityTests(s);
        Tests.carmichaelTests(s);

        // Finding Large Primes
        // 512 bits
        FileWriter file = new FileWriter("results.txt"); // writes some results to a separate file and overwrites the
                                                         // previous results.txt if one exists.
        file.write("512 bits\n");
        file.close();
        file = new FileWriter("results.txt", true);
        BigInteger findingPrime = new BigInteger(numBits, rnd);
        int i = 0;
        while (invertedTestPrimality(findingPrime, s)) {
            findingPrime = new BigInteger(numBits, rnd);
            i++;
        }
        System.out.println(findingPrime + " is the found prime!");
        System.out.println(testPrimality(findingPrime, s));
        System.out.println(i + " candidates were tried.");
        file.write(findingPrime + " is the found prime!\n" + i + " candidates were tried.\n");
        file.close();

        BigInteger findingPrime2 = new BigInteger(numBits, rnd);
        file = new FileWriter("results.txt", true);
        int j = 0;
        while (invertedTestPrimality(findingPrime2, s)) {
            findingPrime2 = new BigInteger(numBits, rnd);
            j++;
        }
        System.out.println(findingPrime2 + " is the found prime!");
        System.out.println(testPrimality(findingPrime2, s));
        System.out.println(j + " candidates were tried.");
        file.write(findingPrime2 + " is the found prime!\n" + j + " candidates were tried.\n");
        file.close();

        // 1024 bits
        file = new FileWriter("results.txt", true);
        file.write("1024 bits\n");
        BigInteger findingPrime3 = new BigInteger(numBits2, rnd);
        int k = 0;
        while (invertedTestPrimality(findingPrime3, s)) {
            findingPrime3 = new BigInteger(numBits, rnd);
            k++;
        }
        System.out.println(findingPrime3 + " is the found prime!");
        System.out.println(testPrimality(findingPrime3, s));
        System.out.println(k + " candidates were tried.");
        file.write(findingPrime3 + " is the found prime!\n" + k + " candidates were tried.\n");
        file.close();

        BigInteger findingPrime4 = new BigInteger(numBits2, rnd);
        file = new FileWriter("results.txt", true);
        int l = 0;
        while (invertedTestPrimality(findingPrime4, s)) {
            findingPrime4 = new BigInteger(numBits, rnd);
            l++;
        }
        System.out.println(findingPrime4 + " is the found prime!");
        System.out.println(testPrimality(findingPrime4, s));
        System.out.println(l + " candidates were tried.");
        file.write(findingPrime4 + " is the found prime!\n" + l + " candidates were tried.\n");
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
            // printBigInt(a);
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
            // printBigInt(a);
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
