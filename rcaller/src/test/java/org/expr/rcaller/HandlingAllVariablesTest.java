/*
 *
 RCaller, A solution for calling R from Java
 Copyright (C) 2010-2015  Mehmet Hakan Satman

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Mehmet Hakan Satman - mhsatman@yahoo.com
 * http://www.mhsatman.com
 * Google code project: https://github.com/jbytecode/rcaller
 * Please visit the blog page with rcaller label:
 * http://stdioe.blogspot.com.tr/search/label/rcaller
 */


package org.expr.rcaller;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;

public class HandlingAllVariablesTest {

    private final static double delta = 1.0 / 1000.0;

    @Test
    public void GetAllVariablesInEnvironmentTest() {
        RCaller caller = new RCaller();
        Globals.detect_current_rscript();
        caller.setRscriptExecutable(Globals.Rscript_current);

        RCode code = new RCode();

        code.addDouble("x", 5.65);
        code.addDouble("y", 8.96);
        code.addRCode("result <- as.list(.GlobalEnv)");

        caller.setRCode(code);

        caller.runAndReturnResult("result");

        ArrayList<String> names = caller.getParser().getNames();
        System.out.println("Names : " + names);

        System.out.println("x is " + caller.getParser().getAsDoubleArray("x")[0]);
        System.out.println("y is " + caller.getParser().getAsDoubleArray("y")[0]);

        Assert.assertEquals(caller.getParser().getAsDoubleArray("x")[0], 5.65, delta);
        Assert.assertEquals(caller.getParser().getAsDoubleArray("y")[0], 8.96, delta);
    }
    
    @Test
    public void GetAllVectorsInEnvironmentTest() {
        RCaller caller = new RCaller();
        Globals.detect_current_rscript();
        caller.setRscriptExecutable(Globals.Rscript_current);

        RCode code = new RCode();

        code.addDoubleArray("x", new double[]{1,2,3,4,5});
        code.addDoubleArray("y", new double[]{2,4,6,8,10});
        code.addRCode("result <- as.list(.GlobalEnv)");

        caller.setRCode(code);

        caller.runAndReturnResult("result");

        ArrayList<String> names = caller.getParser().getNames();
        System.out.println("Names : " + names);

        System.out.println("x[4] is " + caller.getParser().getAsDoubleArray("x")[4]);
        System.out.println("y[4] is " + caller.getParser().getAsDoubleArray("y")[4]);

        Assert.assertEquals(caller.getParser().getAsDoubleArray("x")[4], 5, delta);
        Assert.assertEquals(caller.getParser().getAsDoubleArray("y")[4], 10, delta);
    }
}
