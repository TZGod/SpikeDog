package org.tzgod.spikedog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName WebServlet
 * @Description
 * @Author TZGod
 * @Version V1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {

    String value();
}
