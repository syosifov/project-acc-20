package net.yosifov.filipov.training.accounting.acc20.utils;

import java.math.MathContext;
import java.math.RoundingMode;

public class U {

    public static final int SCALE = 2;
    public static MathContext mc2 = new MathContext(19, RoundingMode.HALF_EVEN);
    //public static MathContext mc4 = new MathContext(4, RoundingMode.HALF_EVEN);
}
