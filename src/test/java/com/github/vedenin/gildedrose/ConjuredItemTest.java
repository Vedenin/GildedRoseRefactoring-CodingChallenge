package com.github.vedenin.gildedrose;

import org.junit.Assert;
import org.junit.Test;

public class ConjuredItemTest {
    private static final int INIT_SELL_IN = 11;
    private static final int INIT_QUALITY = 50;
    private static final int MAX_DAYS = 30;

    @Test
    public void updateQualityNewVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item(Constants.CONJURED, INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRose(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    private int[] getResult() {
        int[] result = new int[MAX_DAYS];
        int days = 0;
        for (; days < 11; days++) {
            result[days] = INIT_QUALITY - (days + 1) * 2;
        }
        for (; days < 18; days++) {
            result[days] = INIT_QUALITY - (days + 1) * 4 + 22;
        }
        return result;
    }

    private static void startAndCheckNextDay(GildedRoseInterface gildedRose, Item[] items, int days, int[] result) {
        gildedRose.updateQuality();

        checkQuality(items, result[days], days);
        checkSellIIn(items, INIT_SELL_IN - days - 1, days);
    }

    private static void checkQuality(Item[] items, int result, int days) {
        Assert.assertEquals("Quality isn't equals in " + days + " days:", result, items[0].quality);
    }

    private static void checkSellIIn(Item[] items, int sellIn, int days) {
        Assert.assertEquals("SellIIn isn't equals in " + days + " days:", sellIn, items[0].sellIn);
    }

}
