import java.util.Arrays;

public class KaratsubaMultiplication {

    public static int multiply(int firstMultiplier, int secondMultiplier) {
        boolean[] firstNumber = representInBinaryForm(firstMultiplier);
        boolean[] secondNumber = representInBinaryForm(secondMultiplier);
        boolean[] product = multiplyByKaratsuba(firstNumber, secondNumber);
        return representInDecimalForm(product);
    }

    private static boolean[] multiplyByKaratsuba(boolean[] firstMultiplier, boolean[] secondMultiplier) {
        if (firstMultiplier.length == 1) {
            boolean[] product = new boolean[secondMultiplier.length];
            if (firstMultiplier[0]) {
                System.arraycopy(secondMultiplier, 0, product, 0, product.length);
            }
            return product;
        }
        if (secondMultiplier.length == 1) {
            boolean[] product = new boolean[firstMultiplier.length];
            if (secondMultiplier[0]) {
                System.arraycopy(firstMultiplier, 0, product, 0, product.length);
            }
            return product;
        }
        int length = Math.max(firstMultiplier.length, secondMultiplier.length);
        int middle = length / 2;
        boolean[] left1 = new boolean[length - middle];
        boolean[] right1 = new boolean[middle];
        boolean[] left2 = new boolean[length - middle];
        boolean[] right2 = new boolean[middle];
        System.arraycopy(firstMultiplier, 0, right1, 0, Integer.min(middle, firstMultiplier.length));
        System.arraycopy(secondMultiplier, 0, right2, 0, Integer.min(middle, secondMultiplier.length));
        for (int i = middle; i < length; i++) {
            if (i < firstMultiplier.length) {
                left1[i - middle] = firstMultiplier[i];
            }
            if (i < secondMultiplier.length) {
                left2[i - middle] = secondMultiplier[i];
            }
        }
        boolean[] z1 = multiplyByKaratsuba(left1, left2);
        boolean[] z2 = multiplyByKaratsuba(addition(left1, right1), addition(left2, right2));
        boolean[] z3 = multiplyByKaratsuba(right1, right2);
        return addition(shift(z1, 2 * middle), addition(shift(subtraction(z2, addition(z1, z3)), middle), z3));
    }

    private static boolean[] addition(boolean[] firstTerm, boolean[] secondTerm) {
        boolean[] largestTerm = firstTerm.length > secondTerm.length ? firstTerm : secondTerm;
        int max = Integer.max(firstTerm.length, secondTerm.length);
        int min = Integer.min(firstTerm.length, secondTerm.length);
        boolean[] sum = new boolean[max + 1];
        int index = 0;
        int count = 0;
        for (int i = 0; i < min; i++) {
            if (firstTerm[i]) {
                count++;
            }
            if (secondTerm[i]) {
                count++;
            }
            if (count % 2 == 1) {
                sum[i] = true;
                index = i;
            }
            count = count > 1 ? 1 : 0;
        }
        for (int i = min; i < max; i++) {
            if (largestTerm[i]) {
                count++;
            }
            if (count == 1) {
                sum[i] = true;
                index = i;
            }
            count = count == 2 ? 1 : 0;
        }
        if (count == 1) {
            sum[max] = true;
            index = max;
        }
        if (index != max) {
            return Arrays.copyOfRange(sum,0,index + 1);
        }
        return sum;
    }

    private static boolean[] subtraction(boolean[] minuend, boolean[] subtrahend) {
        boolean[] sub = new boolean[minuend.length];
        int count = 0;
        int index = 0;
        for (int i = 0; i < minuend.length; i++) {
            if (minuend[i]) {
                count++;
            }
            if (i < subtrahend.length && subtrahend[i]) {
                count--;
            }
            if (count == 1) {
                sub[i] = true;
                index = i;
                count = 0;
            }
            if (count < 0) {
                if (count == -1) {
                    sub[i] = true;
                    index = i;
                }
                count = -1;
            }
        }
        if (index != sub.length - 1) {
            return Arrays.copyOfRange(sub,0,index + 1);
        }
        return sub;
    }

    private static boolean[] shift(boolean[] digits, int k) {
        boolean[] result = new boolean[digits.length + k];
        System.arraycopy(digits, 0, result, k, result.length - k);
        return result;
    }

    private static boolean[] representInBinaryForm(int number) {
        int length = (int) Math.floor(Math.log(number) / Math.log(2)) + 1;
        boolean[] digits = new boolean[length > 0 ? length : 1];
        for (int i = 0; number > 0; i++) {
            digits[i] = (number % 2 == 1);
            number /= 2;
        }
        return digits;
    }

    private static int representInDecimalForm(boolean[] digits) {
        int number = 0;
        int n = 1;
        for (boolean digit : digits) {
            if (digit) {
                number += n;
            }
            n *= 2;
        }
        return number;
    }

}
