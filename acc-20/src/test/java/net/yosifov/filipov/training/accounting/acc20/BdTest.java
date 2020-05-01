package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.utils.U;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BdTest {

    @Test
    public void t1() {
        BigDecimal bd = new BigDecimal("0.00");
        System.out.println(bd);
    }


    // https://www.baeldung.com/java-bigdecimal-biginteger
    public static BigDecimal calculateTotalAmount(BigDecimal quantity,
                                                  BigDecimal unitPrice,
                                                  BigDecimal discountRate,
                                                  BigDecimal taxRate) {
        BigDecimal amount = quantity.multiply(unitPrice);
        BigDecimal discount = amount.multiply(discountRate);
        BigDecimal discountedAmount = amount.subtract(discount);
        BigDecimal tax = discountedAmount.multiply(taxRate);
        BigDecimal total = discountedAmount.add(tax);

        // round to 2 decimal places using HALF_EVEN
        BigDecimal roundedTotal = total.setScale(2, RoundingMode.HALF_EVEN);

        return roundedTotal;
    }

    public static BigDecimal calculateTotalAmount2(BigDecimal quantity,
                                                  BigDecimal unitPrice,
                                                  BigDecimal discountRate,
                                                  BigDecimal taxRate) {
        BigDecimal amount = quantity.multiply(unitPrice).setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal discount = amount.multiply(discountRate).setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal discountedAmount = amount.subtract(discount).setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal tax = discountedAmount.multiply(taxRate).setScale(U.SCALE, RoundingMode.HALF_EVEN);
        BigDecimal total = discountedAmount.add(tax).setScale(U.SCALE, RoundingMode.HALF_EVEN);

        // round to 2 decimal places using HALF_EVEN
        BigDecimal roundedTotal = total.setScale(U.SCALE, RoundingMode.HALF_EVEN);

        return roundedTotal;
    }

    @Test
    public void givenPurchaseTxn_whenCalculatingTotalAmount_thenExpectedResult() {
        BigDecimal quantity = new BigDecimal("4.5");
        BigDecimal unitPrice = new BigDecimal("2.69");
        BigDecimal discountRate = new BigDecimal("0.10");
        BigDecimal taxRate = new BigDecimal("0.0725");

        BigDecimal amountToBePaid = calculateTotalAmount(
                quantity,
                unitPrice,
                discountRate,
                taxRate);

        System.out.println(amountToBePaid);
        assertEquals("11.68", amountToBePaid.toString());

        BigDecimal amountToBePaid2 = calculateTotalAmount2(
                quantity,
                unitPrice,
                discountRate,
                taxRate);
        System.out.println(amountToBePaid2);
    }

}
