package sprint_1.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    public static void main(String[] args) throws Exception {
//        String dateStr1 = "2020-11-24T17:00:00.000Z"; // 1.test compareDates
//        String dateStr2 = "2020-11-24T17:00:00.000Z";
//        int result = compareDates(dateStr1, dateStr2);
//        if (result>0){
//            System.out.println("-->date1 is later than date2");
//        } else if(result<0){
//            System.out.println("-->date1 is earlier than date2");
//        } else {
//            System.out.println("-->date1 is equals to date2");
//        }
        String endDate = "2020-11-26"; // 2.Test deviation-hours.
        long endTime = 11;
        System.out.println("deviation-hours: " + getDiffTime(endDate, endTime));
    }

    public static long getDiffTime(String endDate, long endTime) throws ParseException {
        Date moment = new Date();
        Date enDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        long nowMillis = moment.getTime();
        long endTimeMillis = (long) (((endTime - 1) * 0.5 + 7) * 3600 * 1000 + enDate.getTime());
        long deviation = nowMillis - endTimeMillis;
        System.out.println("Deviation:" + deviation);
        return deviation/(1000*60*60); // --> hours
    }

    public static int compareDates(String dateStr1, String dateStr2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(dateStr1);
        Date date2 = sdf.parse(dateStr2);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.compareTo(date2) > 0) {
            return 1;
        } else if (date1.compareTo(date2) < 0) {
            return -1;
        } else if (date1.compareTo(date2) == 0) {
            return 0;
        } else {
            throw new Exception("Error happens...");
        }
    }
}