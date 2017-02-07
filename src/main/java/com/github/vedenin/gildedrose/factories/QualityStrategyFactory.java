package com.github.vedenin.gildedrose.factories;

import com.github.vedenin.gildedrose.strategies.QualityStrategy;

/**
 * pattern Factory
 */
public interface QualityStrategyFactory {
    QualityStrategy getQualityStrategy(String name);
}
