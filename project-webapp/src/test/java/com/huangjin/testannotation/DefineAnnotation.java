package com.huangjin.testannotation;

/**
 * Created by huang on 2016-10-14.
 */
public @interface DefineAnnotation {
    String name() default "huangjin";
    String age() default "25";
}
