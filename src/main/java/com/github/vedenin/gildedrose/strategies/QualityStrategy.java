package com.github.vedenin.gildedrose.strategies;

import com.github.vedenin.gildedrose.factories.QualityStrategyFactory;

/**
 * The Quality of an item is never negative (MIN_QUALITY)
 * The Quality of an item is never more than 50 (MAX_QUALITY)
 * getQualityInCorrectRange possible to override in Strategy class for new items in future
 *
 * pattern Strategy
 * @see QualityStrategyFactory
 */
public interface QualityStrategy {
    int MIN_QUALITY = 0;
    int MAX_QUALITY = 50;

    int getQuality(int quality, int sellIn);

    default int getQualityInCorrectRange(int quality) {
        return quality < MIN_QUALITY ? MIN_QUALITY :
               quality > MAX_QUALITY ? MAX_QUALITY : quality;
    }
}
