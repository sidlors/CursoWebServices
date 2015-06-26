/*
 */

package com.example.jaxrs.resources.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author HSen@vertrax.com (modified by education.com)
 */
@Provider
@Produces(MediaType.APPLICATION_XML)
public class MapMessageBodyWriter
implements MessageBodyWriter<Map<String, String>>{
  // ...
  @Override
  public long getSize(Map<String, String> m, Class<?> type,
          Type genericType, Annotation[] annotations,
          MediaType mediaType) {
    return -1;
  }
  @Override
  public boolean isWriteable(Class<?> type,Type generic,
          Annotation[] annotations, MediaType mediaType) {
    MediaType targetMedia = MediaType.APPLICATION_XML_TYPE;
    return
      (generic.equals(targetType()) &&
       mediaType.isCompatible(targetMedia));
  }
  @Override
  public void writeTo(Map<String, String> m,
          Class<?> type, Type genericType,
          Annotation[] annotations, MediaType mediaType,
          MultivaluedMap<String, Object> httpHeaders,
          OutputStream entityStream) 
  throws IOException, WebApplicationException {
    StringBuffer sb = new StringBuffer("<map>");
    for (Map.Entry<String, String> entry : m.entrySet()) {
      String key = entry.getKey(), val = entry.getValue();
      sb.append("<entry>");
      sb.append("<key>").append(key).append("</key>");
      sb.append("<value>").append(val).append("</value>");
      sb.append("</entry>");
    }
    sb.append("</map>");
    entityStream.write(sb.toString().getBytes());
  }
  private Type targetType() {
     Type[] types = getClass().getGenericInterfaces();
     ParameterizedType myIf = (ParameterizedType) types[0];
     return myIf.getActualTypeArguments()[0];
  }
}
