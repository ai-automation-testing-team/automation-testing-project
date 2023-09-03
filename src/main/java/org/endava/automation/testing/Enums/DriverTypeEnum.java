package org.endava.automation.testing.Enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum DriverTypeEnum {

    GOOGLE_CHROME_DRIVER("chrome"),
    GOOGLE_CHROME_DRIVER_HEADLESS("chrome-headless");

    private final String text;

    private DriverTypeEnum(final String text) {
        this.text = text;
    }

    private static Map<String, DriverTypeEnum> values = populateMap();

    private static Map<String, DriverTypeEnum> populateMap() {
        Map<String, DriverTypeEnum> valuesMap = new HashMap<>();
        DriverTypeEnum[] values = values();
        Stream.of(values).forEach(x -> valuesMap.put(x.text, x));
        return valuesMap;
    }

    @Override
    public String toString() {
        return text;
    }

    public static DriverTypeEnum parse(String text) {
        if (!values.containsKey(text)) {
            throw new IllegalArgumentException("text");
        }
        return values.get(text);
    }

}
