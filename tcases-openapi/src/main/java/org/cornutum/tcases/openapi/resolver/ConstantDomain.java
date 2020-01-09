//////////////////////////////////////////////////////////////////////////////
// 
//                    copyright 2019, Cornutum Project
//                             www.cornutum.org
//
//////////////////////////////////////////////////////////////////////////////

package org.cornutum.tcases.openapi.resolver;

import org.cornutum.tcases.util.ToString;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Defines a singleton value set.
 */
public abstract class ConstantDomain<T> implements ValueDomain<T>
  {
  /**
   * Creates a new ConstantDomain instance.
   */
  protected ConstantDomain( Type type, T value)
    {
    type_ = type;
    value_ = value;
    }

  /**
   * Returns the constant value for this domain.
   */
  public T getValue()
    {
    return value_;
    }

  /**
   * Returns a random sequence of values from this domain.
   */
  public Stream<T> values( Random random)
    {
    return Stream.of( value_);
    }

  /**
   * Returns true if the given value belongs to this domain.
   */
  public boolean contains( T value)
    {
    return Objects.equals( value, value_);
    }

  /**
   * Return the type(s) of values that belong to this domain.
   */
  public Type[] getTypes()
    {
    return Type.only( type_);
    }

  public String toString()
    {
    return
      ToString.getBuilder( this)
      .append( getValue())
      .toString();
    }

  private final T value_;
  private final Type type_;
  }