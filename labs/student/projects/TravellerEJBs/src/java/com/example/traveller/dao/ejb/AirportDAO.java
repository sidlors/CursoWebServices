/*
 */

package com.example.traveller.dao.ejb;

import com.example.traveller.model.Airport;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Data Access Object implementation for persistent
 * class {@see com.example.traveller.model.Airport}.
 * @author education@oracle.com
 */
@Stateless
public class AirportDAO extends GenericDAO<Airport> {

  /**
   * Adds a new Airport to the persistent store.
   * @param code airport code for new Airport
   * @param name airport name for new Airport
   * @return newly created persistent Airport.
   */
  public Airport add( String code, String name ) {
    return add( new Airport( code, name ) );
  }

  /**
   * Find a persistent Airport instance by its airport code.
   * @param code airport code for Airport to be found
   * @return persistent Airport by given code.
   */
  public Airport findByCode( String code ) {
    String sqlQuery =
      "SELECT a from Airport a WHERE a.code = :code";
    TypedQuery<Airport> query =
      em.createQuery(sqlQuery,Airport.class);
    query.setParameter( "code", code );
    List<Airport> results = query.getResultList();
    if (results.size() != 1)
      throw new QueryException();
    return results.get(0);
  }

  public Airport[] findByName( String name ) {
    TypedQuery<Airport> query =
      em.createQuery("SELECT a FROM Airport a WHERE a.name LIKE :name",Airport.class);
    query.setParameter( "name", "%" + name + "%" );
    List<Airport> results = query.getResultList();
    return results.toArray( new Airport[0] );
  }

  /**
   * Removes the persistent Airport instance by the given code.
   * @param code airport code for persistent Airport instance to remove
   */
  public void removeByCode( String code ) {
    Airport instance = findByCode( code );
    remove( instance );
  }

  public Airport[] findNeighbors( String code ) {
    String sqlQuery =
      "SELECT DISTINCT f.arrives FROM Flight f WHERE f.departs.code = :source";
    TypedQuery<Airport> query = em.createQuery( sqlQuery, returnedClass() );
    query.setParameter( "source", code );
    List<Airport> neighbors = query.getResultList();
    return neighbors.toArray( new Airport[0] );
  }

  /**
   * Retrieves all airport codes known.
   * @return list of airport codes known, as Strings
   */
  public List<String> getAllCodes() {
    String sqlQuery =
      "SELECT DISTINCT a.code FROM Airport a";
    TypedQuery<String> query = em.createQuery( sqlQuery, String.class );
    List<String> codes = query.getResultList();
    return codes;
  }

  /**
   * Retrieves all airports known.
   * @param em - EntityManager to use for operation, if known
   * @return list of airports known.
   */
  public List<Airport> list() {
    String sqlQuery = "SELECT a FROM Airport a";
    TypedQuery<Airport> query = em.createQuery( sqlQuery, Airport.class );
    List<Airport> as = query.getResultList();
    return as;
  }
}
