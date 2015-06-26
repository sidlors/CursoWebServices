/*
 */

package com.example.jaxrs.resources.providers;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author education.com
 */
@Provider
public class DAOExceptionMapper
  implements ExceptionMapper<PersistenceException> {
  public Response toResponse(PersistenceException ex) {
    if (ex instanceof EntityExistsException) {
      return Response.notAcceptable(null)
             .header("DAO-Message", ex)
             .build();
    } else {
      return
        Response.serverError().header("DAO-Message", ex)
        .build();
    }
  }
}
