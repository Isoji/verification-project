import java.math.BigDecimal;

/**
 * A staff rate type to be applied for staff car parks
 */
public class StaffRate implements IRateStrategy{
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
