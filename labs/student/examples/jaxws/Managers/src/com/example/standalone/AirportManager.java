
package com.example.standalone;

import com.example.traveller.dao.pojo.AirportDAO;

/**
 *
 * @author education.com
 */
public class AirportManager {
  public long
  addAirport(String code, String name) {
    return dao.add(null, code, name).getId();
  }
  private AirportDAO dao = new AirportDAO();
}
