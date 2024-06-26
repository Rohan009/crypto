package model;

public class CryptoData {

    private double price;
    private int tradeVolume;
    private double highPrice;
    private double marketCap;

    public CryptoData(double price, int tradeVolume, double highPrice, double marketCap) {
        this.price = price;
        this.tradeVolume = tradeVolume;
        this.highPrice = highPrice;
        this.marketCap = marketCap;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(int tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }
}
