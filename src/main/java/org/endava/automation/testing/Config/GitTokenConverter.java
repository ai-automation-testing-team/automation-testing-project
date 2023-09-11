package org.endava.automation.testing.Config;


import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class GitTokenConverter implements Converter<String> {


    @Override
    public String convert(final Method method, final String s) {
        if (s == null || s.isEmpty()) {
            return System.getProperty("git.token");
        }
        return s;
    }

}
