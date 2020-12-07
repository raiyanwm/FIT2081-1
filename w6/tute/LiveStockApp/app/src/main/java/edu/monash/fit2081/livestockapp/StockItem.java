package edu.monash.fit2081.livestockapp;

public class StockItem {
    private String itemTitle;
    private String itemOpen;
    private String itemClose;
    private String itemVolume;

    public StockItem(String itemTitle, String itemOpen, String itemClose, String itemVolume) {
        this.itemTitle = itemTitle;
        this.itemOpen = itemOpen;
        this.itemClose = itemClose;
        this.itemVolume = itemVolume;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemOpen() {
        return itemOpen;
    }

    public void setItemOpen(String itemOpen) {
        this.itemOpen = itemOpen;
    }

    public String getItemClose() {
        return itemClose;
    }

    public void setItemClose(String itemClose) {
        this.itemClose = itemClose;
    }

    public String getItemVolume() {
        return itemVolume;
    }

    public void setItemVolume(String itemVolume) {
        this.itemVolume = itemVolume;
    }
}
