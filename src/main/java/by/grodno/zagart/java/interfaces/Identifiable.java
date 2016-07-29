package by.grodno.zagart.java.interfaces;

import java.io.Serializable;

/**
 * This interface defines ability of class to have unique
 * identifier and to have text definition of entity. Mainly
 * used for convenience to make logging more informative.
 */
public interface Identifiable<PK extends Serializable> {

    PK getId();

}
