package ca.jrvs.apps.trading.model.domain;

public class Quote implements Entity<String> {

  private String ticker;
  private Float lastPrice;
  private Float bidPrice;
  private Integer bidSize;
  private Float askPrice;
  private Integer askSize;

  public Quote() {
  }

  public Quote(String ticker, Float lastPrice, Float bidPrice, Integer bidSize, Float askPrice,
      Integer askSize) {
    this.ticker = ticker;
    this.lastPrice = lastPrice;
    this.bidPrice = bidPrice;
    this.bidSize = bidSize;
    this.askPrice = askPrice;
    this.askSize = askSize;
  }

  @Override
  public String getId() {
    return ticker;
  }

  @Override
  public void setId(String s) {
    this.ticker = s.toUpperCase();
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Float getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Float lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Float getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Float bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Integer getBidSize() {
    return bidSize;
  }

  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  public Float getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Float askPrice) {
    this.askPrice = askPrice;
  }

  public Integer getAskSize() {
    return askSize;
  }

  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "ticker='" + ticker + '\'' +
        ", lastPrice=" + lastPrice +
        ", bidPrice=" + bidPrice +
        ", bidSize=" + bidSize +
        ", askPrice=" + askPrice +
        ", askSize=" + askSize +
        '}';
  }
}
