package com.corporate_domain_name.app_name.componet.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by codeest on 2017/2/26.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface TBLiveUrl {

}
