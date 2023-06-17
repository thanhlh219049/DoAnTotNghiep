package com.lethanh219049.application.utils;



import org.springframework.beans.BeanUtils;


public class BeanCopyUtils {
    public static void copyProperties(final Object dest, final Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
