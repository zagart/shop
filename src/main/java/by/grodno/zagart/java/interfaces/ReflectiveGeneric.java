package by.grodno.zagart.java.interfaces;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Zagart on 23.07.2016.
 */
public interface ReflectiveGeneric {

    default Object getGenericObject(int parameterPosition, Logger logger) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[parameterPosition];
        Constructor<?> constructor = clazz.getConstructors()[0];
        Object object = null;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return object;
    }

}