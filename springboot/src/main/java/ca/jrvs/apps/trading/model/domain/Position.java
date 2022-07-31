package ca.jrvs.apps.trading.model.domain;

public class Position implements Entity<Integer> {

  private Integer account_id;
  private String ticker;
  private Integer size;

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  @Override
  public Integer getId() {
    return account_id;
  }

  @Override
  public void setId(Integer integer) {
    this.account_id = integer;
  }
}
