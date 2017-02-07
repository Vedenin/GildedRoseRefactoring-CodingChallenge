package com.github.vedenin.gildedrose.strategies;

/**
 * Common items are constantly degrading in quality as they approach their sell by date.
 * Once the sell by date has passed, Quality degrades twice as fast
 * "Conjured" items degrade in Quality twice as fast as normal (common) items
 */
public class CommonItemQualityStrategy implements QualityStrategy {
    private final static int DEGRADED_SPEED_COMMON = 1;
    private final static int DEGRADED_SPEED_AFTER_SELL_DATE = 2;

    @Override
    public int getQuality(int quality, int sellIn) {
        return quality - (sellIn < 0 ? DEGRADED_SPEED_AFTER_SELL_DATE : DEGRADED_SPEED_COMMON);
    }
}
