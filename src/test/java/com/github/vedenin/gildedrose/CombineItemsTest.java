package com.github.vedenin.gildedrose;

import com.github.vedenin.gildedrose.oldversion.GildedRoseOld;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test for different items in system
 */
public class CombineItemsTest {

    private static final int AGED_BRIE = 0;
    private static final int BACKSTAGE = 1;
    private static final int COMMON_ITEM = 2;
    private static final int CONJURED = 3;
    private static final int SULFURAS = 4;

    private static final int SULFURAS_QUALITY = 80;
    private static final int INIT_SELL_IN = 11;
    private static final int INIT_QUALITY = 40;
    private static final int INIT_BACKSTAGE_QUALITY = 30;
    private static final String COMMON_ITEM_NAME = "Item1";
    private static final int NUMBER_ITEMS = 5;

    @Test
    public void updateQualityNewVersionTest() {
        Item[] items = getItems();

        GildedRoseInterface gildedRose = new GildedRose(items);
        int[][] resultByDays = getResultByDays();

        checkResultUpdateQuality(items, gildedRose, resultByDays);
    }

    private void checkResultUpdateQuality(Item[] items, GildedRoseInterface gildedRose, int[][] resultByDays) {
        for (int days = 0; days < resultByDays.length; days++) {
            startAndCheckNextDay(gildedRose, items, days, resultByDays[days]);
        }
    }

    private Item[] getItems() {
        Item[] items = new Item[NUMBER_ITEMS];
        items[AGED_BRIE] = new Item(Constants.AGED_BRIE, INIT_SELL_IN, INIT_QUALITY);
        items[BACKSTAGE] = new Item(Constants.BACKSTAGE, INIT_SELL_IN, INIT_BACKSTAGE_QUALITY);
        items[COMMON_ITEM] = new Item(COMMON_ITEM_NAME, INIT_SELL_IN, INIT_QUALITY);
        items[CONJURED] = new Item(Constants.CONJURED, INIT_SELL_IN, INIT_QUALITY);
        items[SULFURAS] = new Item(Constants.SULFURAS, INIT_SELL_IN, SULFURAS_QUALITY);
        return items;
    }

    @Test
    public void updateQualityOldVersionTest() {
        Item[] items = getItems();

        GildedRoseInterface gildedRose = new GildedRoseOld(items);
        int[][] resultByDays = getResultOldVersionByDays();

        checkResultUpdateQuality(items, gildedRose, resultByDays);
    }

    private static void startAndCheckNextDay(GildedRoseInterface gildedRose, Item[] items, int days, int[] resultByDays) {
        gildedRose.updateQuality();
        checkQuality(items, resultByDays, days);
        checkSellIIn(items, INIT_SELL_IN - days - 1, days);
    }

    private static void checkQuality(Item[] items, int[] resultByDays, int days) {
        String message = "Quality isn't equals in " + days + " days:";
        Assert.assertEquals(message, resultByDays[AGED_BRIE], items[AGED_BRIE].quality);
        Assert.assertEquals(message, resultByDays[BACKSTAGE], items[BACKSTAGE].quality);
        Assert.assertEquals(message, resultByDays[SULFURAS], items[SULFURAS].quality);
        Assert.assertEquals(message, resultByDays[COMMON_ITEM], items[COMMON_ITEM].quality);
        Assert.assertEquals(message, resultByDays[CONJURED], items[CONJURED].quality);
    }

    private static void checkSellIIn(Item[] items, int sellIn, int days) {
        String message = "SellIIn isn't equals in " + days + " days:";
        Assert.assertEquals(message, sellIn, items[AGED_BRIE].sellIn);
        Assert.assertEquals(message, sellIn, items[BACKSTAGE].sellIn);
        Assert.assertEquals(message, INIT_SELL_IN, items[SULFURAS].sellIn);
        Assert.assertEquals(message, sellIn, items[COMMON_ITEM].sellIn);
        Assert.assertEquals(message, sellIn, items[CONJURED].sellIn);
    }

    private int[][] getResultOldVersionByDays() {
        int[][] resultByDays = getResultByDays();
        Arrays.stream(resultByDays).forEach(resultByDay -> resultByDay[CONJURED] = resultByDay[COMMON_ITEM]);
        return resultByDays;
    }

    private int[][] getResultByDays() {
        return new int[][]{
                //agedBrie, backstage, common, conjured, sulfuras
                {41, 31, 39, 38, 80}, // sellIn = 11
                {42, 33, 38, 36, 80}, // sellIn = 10
                {43, 35, 37, 34, 80},
                {44, 37, 36, 32, 80},
                {45, 39, 35, 30, 80},
                {46, 41, 34, 28, 80},
                {47, 44, 33, 26, 80}, // sellIn = 5
                {48, 47, 32, 24, 80},
                {49, 50, 31, 22, 80},
                {50, 50, 30, 20, 80},
                {50, 50, 29, 18, 80},
                {50, 0, 27, 14, 80},  // sellIn = 0
                {50, 0, 25, 10, 80},  // sellIn = -1
                {50, 0, 23, 6, 80},
                {50, 0, 21, 2, 80},
                {50, 0, 19, 0, 80},   // sellIn = -4
        };
    }
}
