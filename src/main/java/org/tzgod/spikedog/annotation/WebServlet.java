package org.tzgod.spikedog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName WebServlet
 * @Description
 * @Author ChenChuan
 * @Date 2020/7/16 12:18
 * @Version V1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {

    String value();
}
