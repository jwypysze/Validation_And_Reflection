package com.kodilla.reflection;

import com.kodilla.validation.Range;

public class Numbers {

    @Range(min = "1", max = "5")
    private int z;
    @Range(min = "1", max = "5")
    private int n;

    public int getZ() {
        return z;
    }

    public int getN() {
        return n;
    }
}
