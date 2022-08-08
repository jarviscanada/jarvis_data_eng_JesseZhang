package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DashboardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
      QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a traderAccountView by trader ID - get trader account by id - get trader info
   * by id - create and return a traderAccountView
   *
   * @param traderId must not be null
   * @return traderAccountView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {

    if(traderId==null){
      throw new IllegalArgumentException("The traderId is null");
    }
    Account account = findAccountByTraderId(traderId);
    Trader trader = traderDao.findById(traderId).orElseThrow(()->new IllegalArgumentException("Invalid traderId"));

    TraderAccountView traderAccountView = new TraderAccountView(trader, account);

    return traderAccountView;
  }

  /**
   * Create and return portfolioView by trader ID - get account by trader id - get position by
   * account id - create and return a portfolioView
   *
   * @param traderId must not be null
   * @return portfolioView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {

    if(traderId==null){
      throw new IllegalArgumentException("The traderId is null");
    }

    Account account = findAccountByTraderId(traderId);
    Iterable<Position> positions = positionDao.findAllByAccountId(account.getId());





    return null;
  }

  private Account findAccountByTraderId(Integer traderId) {
    return accountDao.findByTraderId(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }

}
