package by.grodno.zagart.java.entities;

import java.io.Serializable;

/**
 * Created by Zagart on 22.07.2016.
 */
public interface IdentifiableEntity<PK extends Serializable> {

    PK getId();

    String getEntityName();

}
