import java.math.BigDecimal;

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
        return null;
    }
}
