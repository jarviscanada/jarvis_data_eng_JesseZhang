package ca.jrvs.apps.trading.model.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * https://iexcloud.io/docs/api/#quote
 */
public class IexQuote {

  private int avgTotalVolume;
  private String calculationPrice;
  private double change;
  private double changePercent;
  private Object close;
  private String closeSource;
  private Object closeTime;
  private String companyName;
  private String currency;
  private Object delayedPrice;
  private Object delayedPriceTime;
  private Object extendedChange;
  private Object extendedChangePercent;
  private Object extendedPrice;
  private Object extendedPriceTime;
  private Object high;
  private String highSource;
  private long highTime;
  private double iexAskPrice;
  private int iexAskSize;
  private double iexBidPrice;
  private int iexBidSize;
  private double iexClose;
  private long iexCloseTime;
  private long iexLastUpdated;
  private double iexMarketPercent;
  private double iexOpen;
  private long iexOpenTime;
  private double iexRealtimePrice;
  private int iexRealtimeSize;
  private int iexVolume;
  private long lastTradeTime;
  private double latestPrice;
  private String latestSource;
  private String latestTime;
  private long latestUpdate;
  private Object latestVolume;
  private Object low;
  private String lowSource;
  private long lowTime;
  private long marketCap;
  private Object oddLotDelayedPrice;
  private Object oddLotDelayedPriceTime;
  private Object open;
  private Object openTime;
  private String openSource;
  private double peRatio;
  private double previousClose;
  private int previousVolume;
  private String primaryExchange;
  private String symbol;
  private Object volume;
  private double week52High;
  private double week52Low;
  private double ytdChange;
  private boolean isUSMarketOpen;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public int getAvgTotalVolume() {
    return avgTotalVolume;
  }

  public void setAvgTotalVolume(int avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }

  public String getCalculationPrice() {
    return calculationPrice;
  }

  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }

  public double getChange() {
    return change;
  }

  public void setChange(double change) {
    this.change = change;
  }

  public double getChangePercent() {
    return changePercent;
  }

  public void setChangePercent(double changePercent) {
    this.changePercent = changePercent;
  }

  public Object getClose() {
    return close;
  }

  public void setClose(Object close) {
    this.close = close;
  }

  public String getCloseSource() {
    return closeSource;
  }

  public void setCloseSource(String closeSource) {
    this.closeSource = closeSource;
  }

  public Object getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Object closeTime) {
    this.closeTime = closeTime;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Object getDelayedPrice() {
    return delayedPrice;
  }

  public void setDelayedPrice(Object delayedPrice) {
    this.delayedPrice = delayedPrice;
  }

  public Object getDelayedPriceTime() {
    return delayedPriceTime;
  }

  public void setDelayedPriceTime(Object delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }

  public Object getExtendedChange() {
    return extendedChange;
  }

  public void setExtendedChange(Object extendedChange) {
    this.extendedChange = extendedChange;
  }

  public Object getExtendedChangePercent() {
    return extendedChangePercent;
  }

  public void setExtendedChangePercent(Object extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }

  public Object getExtendedPrice() {
    return extendedPrice;
  }

  public void setExtendedPrice(Object extendedPrice) {
    this.extendedPrice = extendedPrice;
  }

  public Object getExtendedPriceTime() {
    return extendedPriceTime;
  }

  public void setExtendedPriceTime(Object extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }

  public Object getHigh() {
    return high;
  }

  public void setHigh(Object high) {
    this.high = high;
  }

  public String getHighSource() {
    return highSource;
  }

  public void setHighSource(String highSource) {
    this.highSource = highSource;
  }

  public long getHighTime() {
    return highTime;
  }

  public void setHighTime(long highTime) {
    this.highTime = highTime;
  }

  public double getIexAskPrice() {
    return iexAskPrice;
  }

  public void setIexAskPrice(double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  public int getIexAskSize() {
    return iexAskSize;
  }

  public void setIexAskSize(int iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  public double getIexBidPrice() {
    return iexBidPrice;
  }

  public void setIexBidPrice(double iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  public int getIexBidSize() {
    return iexBidSize;
  }

  public void setIexBidSize(int iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  public double getIexClose() {
    return iexClose;
  }

  public void setIexClose(double iexClose) {
    this.iexClose = iexClose;
  }

  public long getIexCloseTime() {
    return iexCloseTime;
  }

  public void setIexCloseTime(long iexCloseTime) {
    this.iexCloseTime = iexCloseTime;
  }

  public long getIexLastUpdated() {
    return iexLastUpdated;
  }

  public void setIexLastUpdated(long iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }

  public double getIexMarketPercent() {
    return iexMarketPercent;
  }

  public void setIexMarketPercent(double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }

  public double getIexOpen() {
    return iexOpen;
  }

  public void setIexOpen(double iexOpen) {
    this.iexOpen = iexOpen;
  }

  public long getIexOpenTime() {
    return iexOpenTime;
  }

  public void setIexOpenTime(long iexOpenTime) {
    this.iexOpenTime = iexOpenTime;
  }

  public double getIexRealtimePrice() {
    return iexRealtimePrice;
  }

  public void setIexRealtimePrice(double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }

  public int getIexRealtimeSize() {
    return iexRealtimeSize;
  }

  public void setIexRealtimeSize(int iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }

  public int getIexVolume() {
    return iexVolume;
  }

  public void setIexVolume(int iexVolume) {
    this.iexVolume = iexVolume;
  }

  public long getLastTradeTime() {
    return lastTradeTime;
  }

  public void setLastTradeTime(long lastTradeTime) {
    this.lastTradeTime = lastTradeTime;
  }

  public double getLatestPrice() {
    return latestPrice;
  }

  public void setLatestPrice(double latestPrice) {
    this.latestPrice = latestPrice;
  }

  public String getLatestSource() {
    return latestSource;
  }

  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  public String getLatestTime() {
    return latestTime;
  }

  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }

  public long getLatestUpdate() {
    return latestUpdate;
  }

  public void setLatestUpdate(long latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  public Object getLatestVolume() {
    return latestVolume;
  }

  public void setLatestVolume(Object latestVolume) {
    this.latestVolume = latestVolume;
  }

  public Object getLow() {
    return low;
  }

  public void setLow(Object low) {
    this.low = low;
  }

  public String getLowSource() {
    return lowSource;
  }

  public void setLowSource(String lowSource) {
    this.lowSource = lowSource;
  }

  public long getLowTime() {
    return lowTime;
  }

  public void setLowTime(long lowTime) {
    this.lowTime = lowTime;
  }

  public long getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(long marketCap) {
    this.marketCap = marketCap;
  }

  public Object getOddLotDelayedPrice() {
    return oddLotDelayedPrice;
  }

  public void setOddLotDelayedPrice(Object oddLotDelayedPrice) {
    this.oddLotDelayedPrice = oddLotDelayedPrice;
  }

  public Object getOddLotDelayedPriceTime() {
    return oddLotDelayedPriceTime;
  }

  public void setOddLotDelayedPriceTime(Object oddLotDelayedPriceTime) {
    this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
  }

  public Object getOpen() {
    return open;
  }

  public void setOpen(Object open) {
    this.open = open;
  }

  public Object getOpenTime() {
    return openTime;
  }

  public void setOpenTime(Object openTime) {
    this.openTime = openTime;
  }

  public String getOpenSource() {
    return openSource;
  }

  public void setOpenSource(String openSource) {
    this.openSource = openSource;
  }

  public double getPeRatio() {
    return peRatio;
  }

  public void setPeRatio(double peRatio) {
    this.peRatio = peRatio;
  }

  public double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(double previousClose) {
    this.previousClose = previousClose;
  }

  public int getPreviousVolume() {
    return previousVolume;
  }

  public void setPreviousVolume(int previousVolume) {
    this.previousVolume = previousVolume;
  }

  public String getPrimaryExchange() {
    return primaryExchange;
  }

  public void setPrimaryExchange(String primaryExchange) {
    this.primaryExchange = primaryExchange;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Object getVolume() {
    return volume;
  }

  public void setVolume(Object volume) {
    this.volume = volume;
  }

  public double getWeek52High() {
    return week52High;
  }

  public void setWeek52High(double week52High) {
    this.week52High = week52High;
  }

  public double getWeek52Low() {
    return week52Low;
  }

  public void setWeek52Low(double week52Low) {
    this.week52Low = week52Low;
  }

  public double getYtdChange() {
    return ytdChange;
  }

  public void setYtdChange(double ytdChange) {
    this.ytdChange = ytdChange;
  }

  public boolean isIsUSMarketOpen() {
    return isUSMarketOpen;
  }

  public void setIsUSMarketOpen(boolean isUSMarketOpen) {
    this.isUSMarketOpen = isUSMarketOpen;
  }

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}