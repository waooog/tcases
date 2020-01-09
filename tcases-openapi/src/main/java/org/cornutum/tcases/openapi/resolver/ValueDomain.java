//////////////////////////////////////////////////////////////////////////////
// 
//                    Copyright 2019, Cornutum Project
//                             www.cornutum.org
//
//////////////////////////////////////////////////////////////////////////////

package org.cornutum.tcases.openapi.resolver;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Defines a set of values that can be used by a request.
 */
public interface ValueDomain<T>
  {
  enum Type
    {
    ARRAY, BOOLEAN, INTEGER, NULL, NUMBER, OBJECT, STRING;

    /**
     * Returns the set containing only the specified type.
     */
    public static Type[] only( Type type)
      {
      return new Type[]{ type };
      }

    /**
     * Returns the set of all non-null types except for the specified excluded type.
     */
    public static Type[] not( Type excluded)
      {
      return
        Arrays.stream( Type.values())
        .filter( type -> !type.equals( NULL) && !type.equals( excluded))
        .toArray( Type[]::new);
      }

    /**
     * Returns the set of all non-null types.
     */
    public static Type[] any()
      {
      return not( null);
      }
    };
  
  /**
   * Returns a random sequence of values from this domain.
   */
  public Stream<T> values( Random random);

  /**
   * Returns true if the given value belongs to this domain.
   */
  public boolean contains( T value);

  /**
   * Return the type(s) of values that belong to this domain.
   */
  public Type[] getTypes();

  /**
   * Returns a random value from this domain.
   */
  default public T select( Random random)
    {
    return
      values( random)
      .findFirst()
      .orElseThrow( () -> new IllegalStateException( String.format( "Domain=%s is empty", this)));
    }

  /**
   * Returns a new {@link ValueDef} using this domain.
   */
  default public ValueDef<T> forValueDef()
    {
    return new ValueDef<T>( this);
    }
}