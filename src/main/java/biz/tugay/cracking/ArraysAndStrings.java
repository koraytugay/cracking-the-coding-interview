package biz.tugay.cracking;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

class ArraysAndStrings {

    boolean isAllUnique(String string) {
        Set<Character> chars = new HashSet<>();
        return string.codePoints().mapToObj(integer -> (char) integer).allMatch(chars::add);
    }

    boolean checkPermutation(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        Arrays.sort(s1Chars);
        Arrays.sort(s2Chars);

        return Arrays.equals(s1Chars, s2Chars);
    }

    char[] urlify(char[] chars, int trueLength) {
        return new String(chars).substring(0, trueLength).replaceAll(" ", "%20").toCharArray();
    }

    boolean palindromePermutation(String s) {
        BitSet bitSet = new BitSet();
        for (char c : s.toCharArray())
            bitSet.flip(c);

        return bitSet.nextSetBit(0) == -1 || bitSet.nextSetBit(bitSet.nextSetBit(0) + 1) == -1;
    }

    boolean oneAway(String first, String second) {
        if (abs(first.length() - second.length()) > 1)
            return false;

        int off = 0;
        if (first.length() == second.length())
            for (int i = 0; i < first.length(); i++)
                if (first.charAt(i) != second.charAt(i))
                    off++;

        String shorter = first.length() < second.length() ? first : second;
        String longer = first.length() > second.length() ? first : second;

        for (int shortItr = 0, longItr = 0; shortItr < shorter.length(); longItr++)
            if (shorter.charAt(shortItr) != longer.charAt(longItr))
                off++;
            else
                shortItr++;

        return off < 2;
    }

    String stringCompression(String original) {
        if (original == null || original.length() < 2)
            return original;

        char[] chars = original.toCharArray();

        char currentChar = chars[0];
        int currentCharCount = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            currentCharCount++;
            if (i == chars.length - 1) {
                currentCharCount++;
                sb.append(currentCharCount);
                sb.append(currentChar);
            }
            if (original.charAt(i) != currentChar) {
                sb.append(currentCharCount);
                sb.append(currentChar);
                currentChar = original.charAt(i);
                currentCharCount = 0;
            }
        }

        return sb.toString().length() < original.length() ? sb.toString() : original;
    }

    int[][] rotateMatrix(int[][] matrix) {
        int[][] rotated = new int[matrix.length][matrix.length];

        int rotatedRow = -1;
        for (int i = matrix.length - 1; i >= 0; i--)
            for (int j = 0; j < matrix.length; j++) {
                if (j % 4 == 0) rotatedRow++;
                rotated[rotatedRow][j] = matrix[j][i];
            }

        return rotated;
    }

    void zeroMatrix(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }

        for (int row : rows)
            for (int i = 0; i < matrix[row].length; i++)
                matrix[row][i] = 0;

        for (int col : cols)
            for (int i = 0; i < matrix.length; i++)
                matrix[i][col] = 0;
    }

    boolean stringRotation(String s1, String s2) {
        return s1.length() == s2.length() && s1.concat(s1).contains(s2);
    }

}
