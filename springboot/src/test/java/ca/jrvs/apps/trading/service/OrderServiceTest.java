package ca.jrvs.apps.trading.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

  //capture parameter when calling securityOrderDao.save
  @Captor
  ArgumentCaptor<SecurityOrder> captorSecurityOrder;

  //mock all dependencies
  @Mock
  private AccountDao accountDao;
  @Mock
  private SecurityOrderDao securityOrderDao;
  @Mock
  private QuoteDao quoteDao;
  @Mock
  private PositionDao positionDao;

  //injecting mocked dependencies to the testing class via constructor
  @InjectMocks
  private OrderService orderService;

  @Test
  public void executeMarketOrder() {

    MarketOrderDto orderDto = new MarketOrderDto();
    orderDto.setSize(2);
    orderDto.setAccountId(1);
    orderDto.setTicker("AAPL");

    Account account = new Account();
    account.setAmount(5000f);
    account.setTrader_id(1);
    account.setId(1);

    Quote quote = new Quote();
    quote.setTicker("AAPL");
    quote.setLastPrice(50f);

    Position position = new Position();
    position.setPosition(20);

    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setTicker("AAPL");
    securityOrder.setStatus(Status.FILLED);

    when(quoteDao.existsById(anyString())).thenReturn(true);
    when(quoteDao.findById("AAPL")).thenReturn(Optional.of(quote));
    when(accountDao.findById(1)).thenReturn(Optional.of(account));

    when(securityOrderDao.save(any())).thenReturn(securityOrder);

    // test the buying of a market order
    SecurityOrder securityOrderResult = orderService.executeMarketOrder(orderDto);

    assertEquals(Status.FILLED, securityOrderResult.getStatus());
    assertEquals("AAPL", securityOrderResult.getTicker());

    verify(securityOrderDao).save(captorSecurityOrder.capture());
    assertEquals("AAPL", captorSecurityOrder.getValue().getTicker());

    // test the selling of a market order

    orderDto.setSize(-2);
    when(positionDao.findByIds(1, "AAPL")).thenReturn(Optional.of(position));

    SecurityOrder securityOrderResult2 = orderService.executeMarketOrder(orderDto);

    assertEquals(Status.FILLED, securityOrderResult2.getStatus());
    assertEquals("AAPL", securityOrderResult2.getTicker());

    assertEquals("AAPL", captorSecurityOrder.getValue().getTicker());
  }
}
