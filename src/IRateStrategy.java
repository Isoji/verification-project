import java.math.BigDecimal;

/**
 * A rate application strategy for different rate types
 */
public interface IRateStrategy {
    /**
     * Returns the cost with the applied rate type
     * @param cost the cost of the parking fee before a rate type is applied
     * @return the new cost
     */
    BigDecimal applyRate(BigDecimal cost);
}
