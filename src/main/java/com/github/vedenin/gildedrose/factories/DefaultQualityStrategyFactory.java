package com.github.vedenin.gildedrose.factories;

import com.github.vedenin.gildedrose.strategies.*;

import java.util.HashMap;
import java.util.Map;

import static com.github.vedenin.gildedrose.Constants.AGED_BRIE;
import static com.github.vedenin.gildedrose.Constants.BACKSTAGE;
import static com.github.vedenin.gildedrose.Constants.CONJURED;

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
