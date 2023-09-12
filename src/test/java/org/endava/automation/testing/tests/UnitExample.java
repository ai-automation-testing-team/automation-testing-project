package org.endava.automation.testing.tests;

import org.ai.automation.test_automation.annotations.AnalysisAI;
import org.endava.automation.testing.Utils.BaseTest;
import org.endava.automation.testing.service.ServiceExample;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class UnitExample extends BaseTest {


    @Test
    @AnalysisAI
    public void exampleTestNested() {
        List<Float> list = List.of(1F, 3F, 5F, -40F);
        String sum = ServiceExample.sum(list);
        Assert.assertEquals(sum, "9");
    }


    @Test
    @AnalysisAI
    public void exampleTestInsideMethod() {
        String stringNumber = "one";
        BigDecimal bigDecimal = new BigDecimal(stringNumber);
        Assert.assertEquals(BigDecimal.valueOf(1), bigDecimal);
    }

}
