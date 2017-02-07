package com.github.vedenin.gildedrose.strategies;

/**
 * Common items are constantly degrading in quality as they approach their sell by date.
 * Once the sell by date has passed, Quality degrades twice as fast
 */
public class ConjuredItemQualityStrategy implements QualityStrategy {
    private final static int DEGRADED_SPEED_COMMON = 2;
    private final static int DEGRADED_SPEED_AFTER_SELL_DATE = 4;

    @Override
    public int getQuality(int quality, int sellIn) {
        return quality - (sellIn < 0 ? DEGRADED_SPEED_AFTER_SELL_DATE : DEGRADED_SPEED_COMMON);
    }
}
