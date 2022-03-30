import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A visitor rate to be applied for visitor car parks
 */
public class VisitorRate implements IRateStrategy{
    /**
     * Returns the cost with the applied rate type
     * @param cost the cost of the parking fee before a rate type is applied
     * @return the new cost
     */
    @Override
    public BigDecimal applyRate(BigDecimal cost) {
        return cost.subtract(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(2), 1, RoundingMode.CEILING);
    }
}
