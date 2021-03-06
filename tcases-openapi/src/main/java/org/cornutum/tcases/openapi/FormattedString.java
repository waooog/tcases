//////////////////////////////////////////////////////////////////////////////
// 
//                    Copyright 2019, Cornutum Project
//                             www.cornutum.org
//
//////////////////////////////////////////////////////////////////////////////

package org.cornutum.tcases.openapi;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

/**
 * Defines a formatted string for a specific object.
 */
public abstract class FormattedString
  {
  /**
   * Creates a FormattedString for the given object.
   */
  public static FormattedString of( String format, Object object)
    {
    return
      object == null?
      new Null() :
      
      "binary".equals( format)?
      new Base64( (byte[]) object) :
      
      "byte".equals( format)?
      new Base64( (byte[]) object) :
      
      "date".equals( format)?
      new Date( (java.util.Date) object) :
      
      "date-time".equals( format)?
      new DateTime( (java.util.Date) object) :
      
      "uuid".equals( format)?
      new Uuid( (UUID) object) :
      
      new Native( object);
    }

  /**
   * Creates a FormattedString for each of the given objects.
   */
  public static List<FormattedString> of( String format, List<?> objects)
    {
    return objects.stream().map( object -> of( format, object)).collect( toList());
    }

  public String toString()
    {
    return formatted();
    }

  /**
   * Returns the formatted string representation of this object.
   */
  public abstract String formatted();

  /**
   * Uses the null string representation of this object.
   */
  public static class Null extends FormattedString
    {
    /**
     * Creates a new Null instance.
     */
    private Null()
      {
      }

    public String formatted()
      {
      return null;
      }
    }

  /**
   * Uses the native string representation of this object.
   */
  public static class Native extends FormattedString
    {
    /**
     * Creates a new Native instance.
     */
    private Native( Object object)
      {
      object_ = object;
      }

    public String formatted()
      {
      return object_.toString();
      }

    private Object object_;
    }

  /**
   * Uses the base64 string representation of this object.
   */
  public static class Base64 extends FormattedString
    {
    /**
     * Creates a new Base64 instance.
     */
    private Base64( byte[] object)
      {
      object_ = object;
      }

    public String formatted()
      {
      return java.util.Base64.getEncoder().encodeToString( object_);
      }

    private byte[] object_;
    }

  /**
   * Uses the RFC3339 full-date representation of this object.
   */
  public static class Date extends FormattedString
    {
    /**
     * Creates a new Date instance.
     */
    private Date( java.util.Date object)
      {
      object_ = object;
      }

    public String formatted()
      {
      return new SimpleDateFormat("yyyy-MM-dd").format( object_);
      }

    private java.util.Date object_;
    }

  /**
   * Uses the RFC3339 date-time representation of this object.
   */
  public static class DateTime extends FormattedString
    {
    /**
     * Creates a new DateTime instance.
     */
    private DateTime( java.util.Date object)
      {
      object_ = object;
      }

    public String formatted()
      {
      return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format( object_);
      }

    private java.util.Date object_;
    }

  /**
   * Uses the UUID string representation of this object.
   */
  public static class Uuid extends FormattedString
    {
    /**
     * Creates a new Uuid instance.
     */
    private Uuid( UUID object)
      {
      object_ = object;
      }

    public String formatted()
      {
      return object_.toString();
      }

    private UUID object_;
    }

  }
