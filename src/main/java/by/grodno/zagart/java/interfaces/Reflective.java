package by.grodno.zagart.java.interfaces;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * This interface defines ability of objects of this class
 * to use methods that based on reflection.
 */
public interface Reflective extends Loggable {

    /**
     * The method creates object of class which is
     * in generic parameters at pointed position.
     *
     * @param parameterPosition
     * @return
     */
    default Object getGenericObject(int parameterPosition) {
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
