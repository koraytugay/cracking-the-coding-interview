package biz.tugay.cracking;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArraysAndStringsTest {

    ArraysAndStrings arraysAndStrings = new ArraysAndStrings();

    @Test
    public void isAllUnique() {
        assertThat(arraysAndStrings.isAllUnique(""), is(true));
        assertThat(arraysAndStrings.isAllUnique("a"), is(true));
        assertThat(arraysAndStrings.isAllUnique("aa"), is(false));
        assertThat(arraysAndStrings.isAllUnique("aba"), is(false));
        assertThat(arraysAndStrings.isAllUnique("abcdefg"), is(true));
    }

    @Test
    public void checkPermutation() {
        assertThat(arraysAndStrings.checkPermutation("abc", "cba"), is(true));
        assertThat(arraysAndStrings.checkPermutation("xox", "yox"), is(false));
        assertThat(arraysAndStrings.checkPermutation("koray", "yarok"), is(true));
        assertThat(arraysAndStrings.checkPermutation("abc", "ab"), is(false));
    }

    @Test
    public void urlify() {
        char[] johnSmith = "Mr John Smith    ".toCharArray();
        johnSmith = arraysAndStrings.urlify(johnSmith, 13);
        MatcherAssert.assertThat(johnSmith, equalTo("Mr%20John%20Smith".toCharArray()));
    }

    @Test
    public void palindromePermutation() {
        assertThat(arraysAndStrings.palindromePermutation("tacocat"), is(true));
        assertThat(arraysAndStrings.palindromePermutation("kaka"), is(true));
        assertThat(arraysAndStrings.palindromePermutation("koraytugay"), is(false));
    }

    @Test
    public void oneAway() {
        assertThat(arraysAndStrings.oneAway("pale", "pale"), is(true));
        assertThat(arraysAndStrings.oneAway("pales", "pale"), is(true));
        assertThat(arraysAndStrings.oneAway("pale", "ple"), is(true));
        assertThat(arraysAndStrings.oneAway("pale", "bale"), is(true));
        assertThat(arraysAndStrings.oneAway("pale", "bake"), is(false));
        assertThat(arraysAndStrings.oneAway("pale", "pale__"), is(false));
        assertThat(arraysAndStrings.oneAway("pale", "__pale"), is(false));
        assertThat(arraysAndStrings.oneAway("pale", "pe"), is(false));
    }

    @Test
    public void stringCompression() {
        assertThat(arraysAndStrings.stringCompression("aaaabbcc"), equalTo("4a2b2c"));
        assertThat(arraysAndStrings.stringCompression("aa"), equalTo("aa"));
        assertThat(arraysAndStrings.stringCompression("a"), equalTo("a"));
    }

    @Test
    public void rotateMatrix() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };

        int[][] expected = {
                {2, 4},
                {1, 3}
        };

        assertThat(arraysAndStrings.rotateMatrix(matrix), equalTo(expected));

        matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        expected = new int[][]{
                {3, 6, 9},
                {2, 5, 8},
                {1, 4, 7},
        };

        assertThat(arraysAndStrings.rotateMatrix(matrix), equalTo(expected));
        matrix = new int[][]{
                {10, 11, 12, 13},
                {14, 15, 16, 17},
                {18, 19, 20, 21},
                {22, 23, 24, 25},
        };

        expected = new int[][]{
                {13, 17, 21, 25},
                {12, 16, 20, 24},
                {11, 15, 19, 23},
                {10, 14, 18, 22},
        };

        assertThat(arraysAndStrings.rotateMatrix(matrix), equalTo(expected));
    }

    @Test
    public void zeroMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };

        int[][] expected = {
                {1, 0, 3},
                {0, 0, 0},
                {6, 0, 8}
        };

        arraysAndStrings.zeroMatrix(matrix);
        MatcherAssert.assertThat(matrix, equalTo(expected));

        matrix = new int[][]{
                {1, 0, 3},
        };

        expected = new int[][]{
                {0, 0, 0},
        };

        arraysAndStrings.zeroMatrix(matrix);

        MatcherAssert.assertThat(matrix, equalTo(expected));
        matrix = new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        expected = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 1, 1}
        };

        arraysAndStrings.zeroMatrix(matrix);
        MatcherAssert.assertThat(matrix, equalTo(expected));
    }

    @Test
    public void stringRotation() {
        assertThat(arraysAndStrings.stringRotation("erbottlewat", "waterbottle"), equalTo(true));
        assertThat(arraysAndStrings.stringRotation("king", "ngki"), equalTo(true));
        assertThat(arraysAndStrings.stringRotation("zo_om", "omzo"), equalTo(false));
    }
}
