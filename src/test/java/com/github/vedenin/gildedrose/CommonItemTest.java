package com.github.vedenin.gildedrose;

import com.github.vedenin.gildedrose.oldversion.GildedRoseOld;
import org.junit.Assert;
import org.junit.Test;

public class CommonItemTest {
    private static final int INIT_SELL_IN = 11;
    private static final int INIT_QUALITY = 50;
    private static final int MAX_DAYS = 35;

    @Test
    public void updateQualityNewVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item("Other item", INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRose(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    @Test
    public void updateQualityOldVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item("Other item", INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRoseOld(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    private int[] getResult() {
        int[] result = new int[MAX_DAYS];
        int days = 0;
        for (; days < 11; days++) {
            result[days] = INIT_QUALITY - (days + 1);
        }
        for (; days < 30; days++) {
            result[days] = INIT_QUALITY - (days + 1) * 2 + 11;
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
