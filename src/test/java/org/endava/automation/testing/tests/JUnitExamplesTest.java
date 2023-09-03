package org.endava.automation.testing.tests;

import java.math.BigDecimal;
import java.util.List;
import org.endava.automation.testing.service.ServiceExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitExamplesTest {

    @Test
    public void exampleTestNested() {
        List<Float> list = List.of(1F, 3F, 5F, -40F);
        String sum = ServiceExample.sum(list);
        Assertions.assertEquals(sum, "9");
    }

    @Test
    public void exampleTestInsideMethod() {
        String stringNumber = "one";
        BigDecimal bigDecimal = new BigDecimal(stringNumber);
        Assertions.assertEquals(BigDecimal.valueOf(1), bigDecimal);
    }
}
