package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private QuoteDao quoteDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao,
      PositionDao positionDao) {
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.quoteDao = quoteDao;
    this.positionDao = positionDao;
  }

  /**
   * Execute a market order
   * <p>
   * - validate the order (e.g. size, and ticker) - create a securityOrder (for security_order
   * table) - handle buy or sell order - buy order : check account balance (calls helper method) -
   * sell order : check position for the ticker/symbol (calls helper method) - (please don't forget
   * to update securityOrder.status) - save and return securityOrder
   *
   * @param orderDto market order
   * @return securityOrder from security_order table
   * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
   * @throws IllegalArgumentException                    for invalid input
   */
  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {

    if (orderDto.getSize() == 0) {
      throw new IllegalArgumentException("Invalid order size");
    }

    Integer accountId = orderDto.getAccountId();

    if (!quoteDao.existsById(orderDto.getTicker())) {
      throw new IllegalArgumentException("The ticker is not found");
    }

    SecurityOrder securityOrder = new SecurityOrder();
    Account account;

    if (accountDao.findById(accountId).isPresent()) {
      account = accountDao.findById(accountId).get();
    } else {
      throw new IllegalArgumentException("The account ID is not found");
    }

    securityOrder.setAccount_id(orderDto.getAccountId());

    if (orderDto.getSize() > 0) {
      handleBuyMarketOrder(orderDto, securityOrder, account);
    } else {
      handleSellMarketOrder(orderDto, securityOrder, account);
    }

    securityOrderDao.save(securityOrder);

    return securityOrder;
  }


  /**
   * Helper method that execute a buy order
   *
   * @param marketOrderDto user order
   * @param securityOrder  to be saved in data database
   * @param account        account
   */
  protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
      Account account) {

    Integer size = marketOrderDto.getSize();
    securityOrder.setSize(size);
    String ticker = marketOrderDto.getTicker();
    securityOrder.setTicker(ticker);
    Float price = quoteDao.findById(ticker).get().getLastPrice();
    securityOrder.setPrice(price);
    Float amount = account.getAmount();
    if (amount < size * price) {
      securityOrder.setStatus(Status.CANCELED);
      securityOrder.setNotes("Insufficient fund. Order amount: " + amount);
    } else {
      securityOrder.setStatus(Status.FILLED);
      account.setAmount(amount - size * price);
    }

  }

  /**
   * Helper method that execute a sell order
   *
   * @param marketOrderDto user order
   * @param securityOrder  to be saved in data database
   * @param account        account
   */
  protected void handleSellMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
      Account account) {

    Integer size = marketOrderDto.getSize();
    securityOrder.setSize(size);
    String ticker = marketOrderDto.getTicker();
    securityOrder.setTicker(ticker);
    Float price = quoteDao.findById(ticker).get().getLastPrice();
    securityOrder.setPrice(price);

    Integer totalSize = positionDao.findByIds(account.getId(), ticker).get().getPosition();

//    System.out.println(positionDao.findByIds(account.getId(), ticker).get().toString());
//    System.out.println("totalsize: "+totalSize);
    Float amount = account.getAmount();
    if (size > totalSize) {
      securityOrder.setStatus(Status.CANCELED);
      securityOrder.setNotes("Insufficient stock available. Stock hold: " + totalSize);
    } else {
      securityOrder.setStatus(Status.FILLED);
      account.setAmount(amount + size * price);
    }

  }

}
