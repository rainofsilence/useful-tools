package cn.sumi.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static List<String> getMonthBetween(String start, String end) {
        List<String> list = new ArrayList<>();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        while (startDate.getTime() <= endDate.getTime()) {
            list.add(sdf.format(startDate));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH, 1);

            startDate = calendar.getTime();
        }
        return list;
    }

}
