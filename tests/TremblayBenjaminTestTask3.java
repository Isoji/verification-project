/*
Verification Project Task 3
Author: Benjamin Tremblay
Date: March 23rd, 2022
*/
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TremblayBenjaminTestTask3 {
    @Test
    public void RateValidNormalRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9,17));
        normalPeriods.add(new Period(20,24));
        reducedPeriods.add(new Period(0,9));

        BigDecimal normalRate = new BigDecimal(5);
        BigDecimal reducedRate = new BigDecimal(2);

        assertDoesNotThrow(() -> new Rate(CarParkKind.STUDENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalRateBoundary1(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(5,10));
        normalPeriods.add(new Period(12,20));
        reducedPeriods.add(new Period(0,5));

        BigDecimal normalRate = new BigDecimal(0);
        BigDecimal reducedRate = new BigDecimal(3);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalRateBoundary2(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12,17));
        normalPeriods.add(new Period(20,24));
        reducedPeriods.add(new Period(5,12));

        BigDecimal normalRate = new BigDecimal(-1);
        BigDecimal reducedRate = new BigDecimal(3);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.MANAGEMENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateValidReducedRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10,22));
        reducedPeriods.add(new Period(22,24));
        reducedPeriods.add(new Period(0,4));

        BigDecimal normalRate = new BigDecimal(6);
        BigDecimal reducedRate = new BigDecimal(3);

        assertDoesNotThrow(() -> new Rate(CarParkKind.STUDENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateReducedRateBoundary1(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(5,12));
        reducedPeriods.add(new Period(12,22));

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(0);

        assertDoesNotThrow(() -> new Rate(CarParkKind.STAFF,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateReducedRateBoundary2(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9,11));
        normalPeriods.add(new Period(13,17));
        reducedPeriods.add(new Period(17,24));

        BigDecimal normalRate = new BigDecimal(7);
        BigDecimal reducedRate = new BigDecimal(-1);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.MANAGEMENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalRateGreaterThanReducedRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12,20));
        reducedPeriods.add(new Period(0,12));
        reducedPeriods.add(new Period(20,24));

        BigDecimal normalRate = new BigDecimal(7);
        BigDecimal reducedRate = new BigDecimal(4);

        assertDoesNotThrow(() -> new Rate(CarParkKind.STUDENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalRateEqualsReducedRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,22));
        reducedPeriods.add(new Period(22,24));

        BigDecimal normalRate = new BigDecimal(5);
        BigDecimal reducedRate = new BigDecimal(5);

        assertDoesNotThrow(() -> new Rate(CarParkKind.STUDENT,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalRateLessThanReducedRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12,20));
        normalPeriods.add(new Period(22,24));
        reducedPeriods.add(new Period(0,6));

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(4);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalPeriodsOverlap(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9,17));
        normalPeriods.add(new Period(14,24));
        reducedPeriods.add(new Period(0,9));
        reducedPeriods.add(new Period(7,12));

        BigDecimal normalRate = new BigDecimal(5);
        BigDecimal reducedRate = new BigDecimal(3);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateReducedPeriodsOverlap(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10,12));
        reducedPeriods.add(new Period(15,19));
        reducedPeriods.add(new Period(18,24));

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(2);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void RateNormalPeriodsAndReducedPeriodsOverlap(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12,18));
        normalPeriods.add(new Period(20,24));
        reducedPeriods.add(new Period(16,22));

        BigDecimal normalRate = new BigDecimal(4);
        BigDecimal reducedRate = new BigDecimal(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }

    @Test
    public void calculateValidStartHour(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertEquals(new BigDecimal(10), r.calculate(new Period(12,17)));
    }

    @Test
    public void calculateStartHourBoundary1(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(24,13)));
    }

    @Test
    public void calculateStartHourBoundary2(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertEquals(new BigDecimal(10), r.calculate(new Period(0,9)));
    }

    @Test
    public void calculateStartHourBoundary3(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(-1,20)));
    }

    @Test
    public void calculateStartHourBoundary4(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(25,15)));
    }

    @Test
    public void calculateValidEndHour(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertEquals(new BigDecimal(19), r.calculate(new Period(6,12)));
    }

    @Test
    public void calculateEndHourBoundary1(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertEquals(new BigDecimal(18), r.calculate(new Period(18,24)));
    }

    @Test
    public void calculateEndHourBoundary2(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(5,0)));
    }

    @Test
    public void calculateEndHourBoundary3(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(10,-1)));
    }

    @Test
    public void calculateEndHourBoundary4(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(19,25)));
    }

    @Test
    public void calculateStartHourLessThanEndHour(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertEquals(new BigDecimal(10), r.calculate(new Period(7,9)));
    }

    @Test
    public void calculateStartHourEqualsEndHour(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(7,7)));
    }

    @Test
    public void calculateStartHourGreaterThanEndHour(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.STUDENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);

        assertThrows(IllegalArgumentException.class, ()-> r.calculate(new Period(10,9)));
    }

    /*
    -------------------------------------- Task 2 White Box Tests --------------------------------------
    */
    @Test
    public void RateReducedPeriodsEqualsNull() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = null;
        normalPeriods.add(new Period(10, 16));
        normalPeriods.add(new Period(18, 24));

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(2);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods));
    }

    @Test
    public void RateNormalPeriodsEqualsNull() {
        ArrayList<Period> normalPeriods = null;
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(10, 16));
        reducedPeriods.add(new Period(18, 24));

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(2);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods));
    }

    @Test
    public void RateReducedRateEqualsNull() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(6, 10));
        reducedPeriods.add(new Period(12, 19));

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = null;

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods));
    }

    @Test
    public void RateNormalRateEqualsNull() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(6, 10));
        reducedPeriods.add(new Period(12, 19));

        BigDecimal normalRate = null;
        BigDecimal reducedRate = new BigDecimal(3);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods));
    }

    @Test
    public void RateInvalidNormalPeriods() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));
        normalPeriods.add(new Period(8, 12));
        normalPeriods.add(new Period(0, 5));
        reducedPeriods.add(new Period(20, 24));

        BigDecimal normalRate = new BigDecimal(6);
        BigDecimal reducedRate = new BigDecimal(3);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.STUDENT, normalRate, reducedRate, reducedPeriods, normalPeriods));
    }

    @Test
    public void RateNormalPeriodsAndReducedPeriodsOverlap2(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12,18));
        normalPeriods.add(new Period(20,24));
        reducedPeriods.add(new Period(16,22));
        reducedPeriods.add(new Period(4,8));

        BigDecimal normalRate = new BigDecimal(4);
        BigDecimal reducedRate = new BigDecimal(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Rate(CarParkKind.VISITOR,normalRate,reducedRate,reducedPeriods,normalPeriods));
    }
    /*-------------------------------------------------------------------------------------------------
    -------------------------------------- Task 3 TDD Expanded Tests ----------------------------------
    -------------------------------------------------------------------------------------------------*/
    @Test
    public void calculateVisitorCarParkKindRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period (17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.VISITOR, new BigDecimal(5), new BigDecimal(2),
                reducedPeriods, normalPeriods);

        assertEquals(BigDecimal.valueOf(4.5), r.calculate(new Period(0,12)));
    }

    @Test
    public void calculateManagementCarParkKindRate(){
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7,10));
        normalPeriods.add(new Period(17,20));
        reducedPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(20,24));

        Rate r = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(5), new BigDecimal(2), reducedPeriods,
                normalPeriods);

        assertEquals(BigDecimal.valueOf(4), r.calculate(new Period(10,11)));
    }
}