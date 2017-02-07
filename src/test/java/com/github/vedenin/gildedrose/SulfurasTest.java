package com.github.vedenin.gildedrose;

import com.github.vedenin.gildedrose.oldversion.GildedRoseOld;
import org.junit.Assert;
import org.junit.Test;

public class SulfurasTest {
    private static final int INIT_SELL_IN = 11;
    private static final int INIT_QUALITY = 80;
    private static final int MAX_DAYS = 30;

    @Test
    public void updateQualityNewVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item(Constants.SULFURAS, INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRose(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    @Test
    public void updateQualityOldVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item(Constants.SULFURAS, INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRoseOld(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    private int[] getResult() {
        int[] result = new int[MAX_DAYS];
        for (int days = 0; days < MAX_DAYS; days++) {
            result[days] = INIT_QUALITY;
        }
        return result;
    }

    private static void startAndCheckNextDay(GildedRoseInterface gildedRose, Item[] items, int days, int[] result) {
        gildedRose.updateQuality();

        checkQuality(items, result[days], days);
        checkSellIIn(items, days);
    }

    private static void checkQuality(Item[] items, int result, int days) {
        Assert.assertEquals("Quality isn't equals in " + days + " days:", result, items[0].quality);
    }

    private static void checkSellIIn(Item[] items, int days) {
        Assert.assertEquals("SellIIn isn't equals in " + days + " days:", INIT_SELL_IN, items[0].sellIn);
    }

}
