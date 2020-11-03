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
        Tests.invertedPrimalityTesting(rnd, numBits, numBits2, s);

    }

    // This primality test will check if n is prime or not.
    // Will return true if prime.
    static boolean testPrimality(BigInteger n, int s) {
        boolean prime = true;
        int counter = 0; // counter for numbers that we've already tried.

        while (counter < s && prime) {
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

    static boolean invertedTestPrimality(BigInteger n, int s) throws IOException {
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
            }
            counter++;
        }
        file.write(counter + " the number of candidates for a for the candidate " + n + "\n");
        file.close();
        return !prime;

    }

}
