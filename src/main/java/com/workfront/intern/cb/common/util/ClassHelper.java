package com.workfront.intern.cb.common.util;

public class ClassHelper {
    public static String getBeanName(Class type) {
        String fullName = type.getName();
        String name = fullName.contains(".") ? fullName.substring(1 + fullName.lastIndexOf(".")) : fullName;

        return StringHelper.deCapitalize(name);
    }
}