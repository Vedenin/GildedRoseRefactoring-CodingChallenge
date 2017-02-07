package com.github.vedenin.gildedrose;

import com.github.vedenin.gildedrose.oldversion.GildedRoseOld;
import org.junit.Assert;
import org.junit.Test;

public class AgedBrieTest {
    private static final int INIT_SELL_IN = 11;
    private static final int INIT_QUALITY = 40;
    private static final int MAX_DAYS = 30;

    @Test
    public void updateQualityNewVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item(Constants.AGED_BRIE, INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRose(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    @Test
    public void updateQualityOldVersionTest() {
        Item[] items = new Item[1];
        items[0] = new Item(Constants.AGED_BRIE, INIT_SELL_IN, INIT_QUALITY);
        GildedRoseInterface gildedRose = new GildedRoseOld(items);

        int[] result = getResult();
        for (int days = 0; days < MAX_DAYS; days++) {
            startAndCheckNextDay(gildedRose, items, days, result);
        }
    }

    private int[] getResult() {
        int days = 0;
        int[] result = new int[MAX_DAYS];
        result[days] = 41;
        days++;
        result[days] = 42;
        days++;
        result[days] = 43;
        days++;
        result[days] = 44;
        days++;
        result[days] = 45;
        days++;
        result[days] = 46;
        days++;
        result[days] = 47;
        days++;
        result[days] = 48;
        days++;
        result[days] = 49;
        days++;
        for (int i = days; i < MAX_DAYS; i++) {
            result[i] = 50;
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
