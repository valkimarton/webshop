package com.bmeonlab.valki.webshop.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NullAwareBeanUtils {

    public static void copyNonNullProperties(Object source, Object destination){
        BeanUtils.copyProperties(source, destination,
                getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set emptyNames = new HashSet();
        for(java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || srcValue.equals(0)) emptyNames.add(pd.getName());
        }
        Object[] resultObjects = emptyNames.toArray();
        String[] result = Arrays.stream(resultObjects).toArray(String[]::new);
        return result;
    }
}
