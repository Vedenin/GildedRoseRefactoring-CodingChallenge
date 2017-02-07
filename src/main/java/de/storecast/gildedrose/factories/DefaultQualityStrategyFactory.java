package de.storecast.gildedrose.factories;

import de.storecast.gildedrose.strategies.*;

import java.util.HashMap;
import java.util.Map;

import static de.storecast.gildedrose.Constants.AGED_BRIE;
import static de.storecast.gildedrose.Constants.BACKSTAGE;
import static de.storecast.gildedrose.Constants.CONJURED;

/**
 * pattern Factory
 */
public class DefaultQualityStrategyFactory implements QualityStrategyFactory {
    // Use for store Strategy for any common items
    private final static String DEFAULT_NAME = null;
    private final Map<String, QualityStrategy> map = new HashMap<>();

    public DefaultQualityStrategyFactory() {
        map.put(BACKSTAGE, new BackstageQualityStrategy());
        map.put(AGED_BRIE, new AgedBrieQualityStrategy());
        map.put(CONJURED, new ConjuredItemQualityStrategy());
        map.put(DEFAULT_NAME, new CommonItemQualityStrategy());
    }

    @Override
    public QualityStrategy getQualityStrategy(String name) {
        if(map.containsKey(name)) {
            return map.get(name);
        } else {
            return map.get(DEFAULT_NAME);
        }
    }

}
