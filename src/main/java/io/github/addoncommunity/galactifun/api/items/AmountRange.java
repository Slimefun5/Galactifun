package io.github.addoncommunity.galactifun.api.items;

/**
 * An inclusive range of item amounts.
 */
public final class AmountRange {

    private final int min;
    private final int max;

    public AmountRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }
}
