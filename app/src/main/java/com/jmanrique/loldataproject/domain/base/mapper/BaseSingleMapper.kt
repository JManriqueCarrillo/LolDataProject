package com.jmanrique.loldataproject.domain.base.mapper


/**
 * Abstract class that defines a reactive mapper function. Converts type T objects into type S objects.
 * T type of object to convert.
 * S converted object type.
 */

abstract class BaseSingleMapper<T, S> {

    /**
     * Method to transform object T into object S.
     * @param dataModel object to transform.
     *
     * @return converted object.
     */
    protected abstract fun transform(dataModel: T): S;

    /**
     * Gets map function.
     * @return Func1<T,S> instance.
     */
    fun getTransformMapper(): (T) -> (S)? = {
        transform(it)
    }


}