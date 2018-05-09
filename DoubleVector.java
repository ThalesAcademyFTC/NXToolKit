package org.firstinspires.ftc.teamcode;

import java.util.Arrays;

/**
 * Created by dcrenshaw on 5/9/18.
 *
 * Simple vector implementation for the double datatype
 */

public class DoubleVector {
    //Indices are always 0-based!
    private double[] a = new double[8];
    private int s = 0;

    //Never pass negative indices and be prepared to handle null returns.
    public double get(int x) {
        if (s > x) {
            return a[x];
        }
        else {
            return 0.0; // This is just to avoid throwing an error. Pro tip: code well so this doesn't happen.
        }
    }
    public double[] get(int x, int z) { //Slice; returns a[x:z]
        if (s > x && s > z) {
            return Arrays.copyOfRange(a, x, z);
        }
        else {
            return null;
        }
    }
    public void remove(int x) {
        //O(n-x) time complexity. You have been warned.
        if (s - 1 > x) {
            while (x < s - 1) {
                a[x] = a[x + 1];
                x++;
            }
        }
        if (s > x) { //End of the array; the last element will always be null.
            a[x] = 0.0;
            s--;
        }
    }
    public void add(double x) {
        if (s == a.length) {
            double[] b = a;
            a = new double[a.length + 8];
            for (int y = 0; y < b.length; y++) {
                a[y] = b[y];
            }
            a[s] = x;
        }
        else {
            a[s] = x;
        }
        s++;
    }
    //Return the underlying array, sans null-initialized padding
    //Slightly faster than (but otherwise equivalent to) get(0, name.length() - 1);
    public double[] real() {
        return Arrays.copyOfRange(a, 0, s);
    }
    public int length() {
        return s;
    }
    public void clean() {
        s = 0; a = new double[8];
    }
    public void replace(int x, double y) {
        if (s > x) {
            a[x] = y;
        }
    }
    public void uSet(int x, double y) { //Unsafe set; not dynamic.
        a[x] = y;
    }
    public void uRemove(int x) { //Unsafe remove. Does not shift elements.
        a[x] = 0.0;
    }
}
