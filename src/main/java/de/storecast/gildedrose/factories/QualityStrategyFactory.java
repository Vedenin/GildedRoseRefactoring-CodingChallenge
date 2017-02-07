package de.storecast.gildedrose.factories;

import de.storecast.gildedrose.strategies.QualityStrategy;

/**
 * pattern Factory
 */
public interface QualityStrategyFactory {
    QualityStrategy getQualityStrategy(String name);
}
