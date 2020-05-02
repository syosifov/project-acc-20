package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.utils.U;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BdTest2 {

    // https://www.quora.com/What-can-COBOL-do-that-Java-or-C-cant
    /**
     Accountants have very specific and precise rules about rounding.
     In US money, they (at least normally) round to the nearest penny.
     So, if the third place after the decimal point is 0 through 4,
     you round down. If its 6 through 9, you round up.
     And if it’s a 5, you round so the preceding digit (the pennies)
     is an even number.
    */

    // total_wages = regular_hours * regular_rate + overtime_hours * overtime_rate;

    BigDecimal regularRate    = new BigDecimal("12.95");
    BigDecimal regularHours   = new BigDecimal("37.1");
    BigDecimal overTimeHours  = new BigDecimal("4.4");


    @Test
    public void t1() {

        BigDecimal overTimeRate   = regularRate.multiply(new BigDecimal("1.5"));
        BigDecimal regPay  = regularRate.multiply(regularHours);
        BigDecimal overPay = overTimeRate.multiply(overTimeHours);
        BigDecimal total   = regPay.add(overPay);
        System.out.println(total);
    }

    @Test
    public void t2() {

        BigDecimal overTimeRate   = regularRate
                .multiply(new BigDecimal("1.5"));
//                .setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal regPay  = regularRate.multiply(regularHours)
                .setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal overPay = overTimeRate.multiply(overTimeHours)
                .setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal total   = regPay.add(overPay);
        System.out.println(total);
    }



}
