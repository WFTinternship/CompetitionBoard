package com.workfront.intern.cb.common.util;

import java.util.Collection;

/**
 * Created by Inmelet on 9/20/2016
 */
public class CollectionsHelper {

    public static boolean isBlank(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotBlank(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

}
