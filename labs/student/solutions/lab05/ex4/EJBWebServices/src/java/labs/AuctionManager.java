package labs;

import Model.Auction;
import Model.Bid;
import Model.Item;
import Model.User;
import Model.dao.ejbs.AuctionDAO;
import Model.dao.ejbs.ItemDAO;
import Model.dao.ejbs.UserDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;


@WebService(serviceName="AuctionManagerService")
@Stateless
public class AuctionManager {

  public Auction createAuction( long userId, long itemId, int nDays,
                                double startPrice ) {
    Item item = itemDAO.find(itemId);
    User user = userDAO.find(userId);

    Auction auction = auctionDAO.add(user, item, nDays, startPrice);
    return auction;

  }

  public Auction findAuction( long auctionId ) {
    Auction auction =  auctionDAO.find(auctionId);
    return auction;

  }

  public Bid placeBid( long userId, long auctionId, double bidAmount ) {
    User user = userDAO.find(userId);
    Auction auction = auctionDAO.find(auctionId);
    Bid bid = auction.placeBid(user, bidAmount);
    return bid;
  }

  public String listAuctions() {
    List<Auction> auctions = auctionDAO.query("SELECT a FROM Auction a");
    
    StringBuilder sb = new StringBuilder();
    for (Auction auction:auctions){
      sb = sb.append("Auction Id:" + auction.getId() + " UserId:" +
            auction.getSeller().getUsername() + " Item:" + auction.getItem().getDescription() +
            " Expires:" + auction.getEndDate() + " Price:" + auction.getStartPrice() + "\n");
    }
    return sb.toString();
  }

  public String listBids(long auctionId) {
    Auction auction = auctionDAO.find(auctionId);
    List<Bid> bids = auction.getBids();

    StringBuilder sb = new StringBuilder();
    for (Bid bid:bids){
      sb = sb.append("Bid Id:" + bid.getId() + " UserId:" +
            bid.getBidder().getUsername() + " Amount:"+ bid.getCurrentValue()
            + "\n");
    }
    return sb.toString();
  }


  public Bid getHighBid( long auctionId ) {
    Auction auction = auctionDAO.find(auctionId);
    Bid bid = auction.getHighBid();
    return bid;
    
  }

  @EJB private ItemDAO itemDAO = new ItemDAO();
  @EJB private UserDAO userDAO = new UserDAO();
  @EJB private AuctionDAO auctionDAO = new AuctionDAO();
}
