package com.github.vedenin.gildedrose.strategies;

/**
 * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
 * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
 * Quality drops to 0 after the concert
 */
public class BackstageQualityStrategy implements QualityStrategy {
    private final static int QUALITY_SPEED_COMMON = 1;
    private final static int QUALITY_SPEED_AFTER_FIRST_DATE = 2;
    private final static int QUALITY_SPEED_AFTER_SECOND_DATE = 3;
    private final static int FIRST_DATE = 10;
    private final static int SECOND_DATE = 5;

    @Override
    public int getQuality(int quality, int sellIn) {
        if (sellIn < 0) {
            return 0;
        } else {
            return quality + (
                    sellIn < SECOND_DATE ? QUALITY_SPEED_AFTER_SECOND_DATE :
                    sellIn < FIRST_DATE ? QUALITY_SPEED_AFTER_FIRST_DATE : QUALITY_SPEED_COMMON);
        }
    }
}
