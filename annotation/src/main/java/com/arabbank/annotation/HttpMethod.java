package com.arabbank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpMethod {
    //    Target method down
    //    insert param
    //    insert type mutable Live data

    /*
       1- global value mutable live data,ApiService
       2- make constructor
       3- init value in constructor
       4- make method
       5- insert param inserted in method request
       6- enqueue http method
       7- in anonymous class get type insert in http method
     **/

    // TODO: 2/13/2022 1
    Class<?> mutableType() default Object.class;
}
