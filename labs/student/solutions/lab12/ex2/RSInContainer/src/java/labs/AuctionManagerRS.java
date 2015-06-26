package labs;

import Model.Auction;
import Model.Bid;
import Model.Item;
import Model.User;
import Model.dao.pojo.AuctionDAO;
import Model.dao.pojo.ItemDAO;
import Model.dao.pojo.UserDAO;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@Path("/auctions")
public class AuctionManagerRS {

  @POST @Path("/create")
  public Response createAuction( @FormParam("userId") long userId,
    @FormParam("itemId") long itemId, @FormParam("nDays") int nDays,
    @FormParam("startPrice") double startPrice ) throws URISyntaxException{

    Item item = itemDAO.find(null,itemId);
    User user = userDAO.find(null,userId);

    if ((user == null) || (item == null)) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    Auction newAuction =
      auctionDAO.add(null, user, item, nDays, startPrice);
    
    URI location = new URI("http://localhost:8080/RSInContainer/rest/auctions/" +
            newAuction.getId());

    if (user == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }else {
      return Response.created(location).build();
    }
  }

  @GET @Path("/{id}")
  @Produces("application/xml")
  public Response findAuction( @PathParam("id") long auctionId ) {
    Auction auction =  auctionDAO.find(null,auctionId);

    if (auction == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(auction).build();
    }

  }

  @POST @Path("/bid/place")
  @Produces("application/xml")
  public Response placeBid( @FormParam("userId") long userId,
                            @FormParam("auctionId") long auctionId,
                            @FormParam("bidAmount") double bidAmount ) {


    User user = userDAO.find(null, userId);
    Auction auction = auctionDAO.find(null, auctionId);

    if ((user == null) || (auction == null)) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    Bid newBid = auction.placeBid(user, bidAmount);
    auctionDAO.update(null, auction);
    
    if (newBid == null) {
      return Response.notAcceptable(null).build();
    } else {
      return Response.ok(newBid).build();
    }
  }

  @GET @Path("/list")
  @Produces("application/xml")
  public Response listAuctions() {
    List<Auction> auctions = auctionDAO.query(null,"SELECT a FROM Auction a");

    StringBuilder sb = new StringBuilder();
    sb.append("<auctions>");
    for(Auction auction:auctions){
      sb.append(marshallMe(auction));
    }
    sb.append("</auctions>");

    if (auctions == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(sb.toString()).build();
    }
  }


  @GET @Path("/{id}/listbids")
  @Produces("application/xml")
  public Response listBids(@PathParam("id") long auctionId) {
    Auction auction = auctionDAO.find(null, auctionId);
    if (auction == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    List<Bid> bids = auction.getBids();
    if (bids == null) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("<bids>");
    for(Bid bid:bids){
      sb.append(marshallMe(bid));
    }
    sb.append("</bids>");

    return Response.ok(sb.toString()).build();
    
  }


  @GET @Path("/{id}/highbid")
  @Produces("application/xml")
  public Response getHighBid( @PathParam("id") long auctionId ) {
    Auction auction = auctionDAO.find(null,auctionId);
    Bid highBid = auction.getHighBid();

    if (highBid == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(highBid).build();
    }
  
  }

  public String marshallMe(Object object){
    String xml = "<message>Error</message>";
    try {
      StringWriter st = new StringWriter();

      JAXBContext context = JAXBContext.newInstance(Bid.class, Auction.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
      marshaller.marshal(object , st);

      xml = st.toString();
    } catch (Exception e){
      e.printStackTrace();
    }

    return xml;
  }

  private ItemDAO itemDAO = new ItemDAO();
  private UserDAO userDAO = new UserDAO();
  private AuctionDAO auctionDAO = new AuctionDAO();
}
