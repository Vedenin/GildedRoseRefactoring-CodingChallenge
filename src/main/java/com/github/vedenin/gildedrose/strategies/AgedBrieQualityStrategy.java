package com.github.vedenin.gildedrose.strategies;

/**
 * "Aged Brie" actually increases in Quality the older it gets
 * Once the sell by date has passed, Quality increases twice as fast
 */
public class AgedBrieQualityStrategy implements QualityStrategy {
    private final static int QUALITY_SPEED_COMMON = 1;
    private final static int QUALITY_SPEED_AFTER_SELL_DATE = 2;

    @Override
    public int getQuality(int quality, int sellIn) {
        return quality + (sellIn <= 0 ? QUALITY_SPEED_AFTER_SELL_DATE : QUALITY_SPEED_COMMON);
    }
}
