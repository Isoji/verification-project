import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Rate {
    private IRateStrategy rateStrategy;
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyReducedRate;
    private ArrayList<Period> reduced;
    private ArrayList<Period> normal;

    public Rate(IRateStrategy carParkRate, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> reducedPeriods
            , ArrayList<Period> normalPeriods) {
        if (reducedPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || reducedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(reducedRate) < 0) {
            throw new IllegalArgumentException("The normal rate cannot be less to the reduced rate");
        }
        if (!isValidPeriods(reducedPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.rateStrategy = carParkRate;
        this.hourlyNormalRate = normalRate;
        this.hourlyReducedRate = reducedRate;
        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1 the first collection to check for multi collection overlap
     * @param periods2 the second collection to check for multi collection overlap
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid){
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * Checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            int i = 0;
            int lastIndex = list.size()-1;
            // If the Period collection is of size 2, only one iteration of the following while loop will occur
            // since i = 0, and lastIndex = 1
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * Checks if a period is a valid addition to a collection of periods
     * @param period the Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collection of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }

    /**
     * Calculates the charge to be paid
     * @param periodStay the Period of stay in the car park
     * @return the calculated charge fee as a BigDecimal
     */
    public BigDecimal calculate(Period periodStay) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        BigDecimal charge = (this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
                this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        if(this.kind == CarParkKind.VISITOR){
            charge = charge.subtract(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(2), 1, RoundingMode.CEILING);
        }
        else if (this.kind == CarParkKind.MANAGEMENT){
            if(charge.compareTo(BigDecimal.valueOf(4)) < 0){
                charge = BigDecimal.valueOf(4);
            }
        }
        else if (this.kind == CarParkKind.STUDENT){
            if(charge.compareTo(BigDecimal.valueOf(5.5)) > 0){
                BigDecimal amtAbove = charge.subtract(BigDecimal.valueOf(5.5));
                // Subtract 25% of amount above 5.50
                charge = charge.subtract(amtAbove.divide(BigDecimal.valueOf(4)))
                        .setScale(2, RoundingMode.CEILING);
            }
        }
        else if (this.kind == CarParkKind.STAFF){
            if(charge.compareTo(BigDecimal.valueOf(16)) > 0){
                // Sets charge to maximum value of 16
                charge = BigDecimal.valueOf(16);
            }
        }
        return charge;
    }
}