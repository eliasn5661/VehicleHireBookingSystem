package nofalelias.babcock.VehicleHireBookingSystem.common;

import java.time.LocalDate;

public class DateUtils {

    /**
     * Assuming start1 <= end1 and start2 <= end2
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static boolean isOverLaped(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {

        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
