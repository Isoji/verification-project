import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A student rate type to be applied for student car parks
 */
public class StudentRate implements IRateStrategy{
    /**
     * Returns the cost with the applied rate type
     * @param cost the cost of the parking fee before a rate type is applied
     * @return the new cost
     */
    @Override
    public BigDecimal applyRate(BigDecimal cost) {
        if(cost.compareTo(BigDecimal.valueOf(5.5)) > 0){
            BigDecimal amtAbove = cost.subtract(BigDecimal.valueOf(5.5));
            // Subtract 25% of amount above 5.50
            cost = cost.subtract(amtAbove.divide(BigDecimal.valueOf(4)))
                    .setScale(2, RoundingMode.CEILING);
        }
        return cost;
    }
}
