/*
 */

package Model.dao.ejbs;

import Model.Auction;
import Model.Item;
import Model.SimpleAuction;
import Model.User;
import javax.ejb.Stateless;

/**
 * Data Access Object implementation for persistent
 * class {@see Model.Auction}.
 * @author education@oracle.com
 */
@Stateless
public class AuctionDAO extends GenericDAO<Auction> {

  /**
   * Adds a new Auction to the persistent store.
   * @param seller the User selling the item on this new auction
   * @param item the Item being sold on this auction
   * @param nDays the number of days that the auction will run.  Time
   * for the auction starts when the Auction instance is created, and
   * ends these many days in the future.
   * @param startPrice the initial asking price for the item on this
   * auction.
   * @return newly created persistent Item.
   * If the function had to create its own persistence context, the
   * return instance will be detached, as its persistence context will
   * have disappeared by the time the function returns.
   */
  public
  Auction add(User seller, Item item,
           int nDays, double startPrice) {
    return add(new SimpleAuction(seller, item, nDays, startPrice));
  }
}
