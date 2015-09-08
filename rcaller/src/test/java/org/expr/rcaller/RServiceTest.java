package org.expr.rcaller;

import org.expr.rcaller.Globals;
import org.expr.rcaller.RService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RServiceTest {

    static RService service;
    double delta = 1 / 10000;

    public RServiceTest() {
        Globals.detect_current_rscript();
        if (service == null) {
            service = new RService(Globals.R_current);
        }
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestVersion() {
        String version = service.version();
        Assert.assertTrue(version.contains("R version"));
    }

    @Test
    public void TestMajorVersion() {
        String major = service.major();
        Assert.assertTrue(Integer.parseInt(major) >= 3);
    }

    @Test
    public void TestGetVariableSequential() {
        service.getRCode().clear();
        service.getRCode().addInt("x", 5);
        service.getRCode().addInt("y", 6);
        Object[] o = service.get("z", "list(z=x+y)", RService.type_Integer);
        Assert.assertEquals(11, ((Integer) o[0]).intValue());

        service.getRCode().clear();
        service.getRCode().addDoubleArray("x", new double[]{1, 2, 3});
        service.getRCode().addDoubleArray("y", new double[]{2, 4, 6});
        o = service.get("coefficients", "lm(y~x)", RService.type_double);

        Assert.assertEquals(0.0, ((Double) o[0]), delta);
        Assert.assertEquals(2.0, ((Double) o[1]), delta);
    }

}
