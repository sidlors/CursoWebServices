/*
 */

package com.example.traveller.dao.ejb;

import com.example.traveller.model.Airport;
import com.example.traveller.model.Flight;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Data Access Object implementation for persistent
 * class {@see com.example.traveller.model.Flight}.
 * @author education@oracle.com
 */
@Stateless
public class FlightDAO extends GenericDAO<Flight> {
  public Flight
  findByNumber(String number) {
    Flight result = null;
    return result;
  }
  public Map<Flight,Airport>
  findRoutesFrom(Airport start) {
    String sqlQuery =
      "SELECT f FROM Flight f WHERE f.departs = :start";
    TypedQuery<Flight> query = em.createQuery(sqlQuery, returnedClass());
    query.setParameter( "start", start );
    List<Flight> routes = query.getResultList();
    Map<Flight,Airport> result = new HashMap<Flight,Airport>();
    for (Flight f : routes)
      result.put(f, f.getArrives());
    return result;
  }
}
