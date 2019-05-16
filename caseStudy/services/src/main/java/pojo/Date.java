package pojo;
import java.util.*;

public class Date {
    private Integer month;
    private Integer day;
    private Integer year;

    public Date (int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public static Comparator<pojo.Date> dateComparator = new Comparator<pojo.Date>() {

        public int compare(pojo.Date d1, pojo.Date d2) {
            int compareYear = (d1.year).compareTo(d2.year);
            int compareMonth = (d1.month).compareTo(d2.month);
            int compareDay = (d1.day).compareTo(d2.day);

            if (compareYear != 0) return compareYear;
            else if (compareMonth != 0) return compareMonth;
            else return compareDay;
        }
    };

    public static pojo.Date dateOfString(String date) {
        String[] dateList = date.split("_");
        Integer month = Integer.parseInt(dateList[0]);
        Integer day = Integer.parseInt(dateList[1]);
        Integer year = Integer.parseInt(dateList[2]);

        return new pojo.Date (month, day, year);
    }

    public static String stringOfDate(pojo.Date date) {
        return date.month + "_" + date.day + "_" + date.year;
    }

    public static List<String> consecutiveListOfDates(String startDate, String endDate, String[] strDateSet) {
        List<Date> dateList = new ArrayList<Date>();
        for(int i = 0; i < dateList.size(); i++) {
            dateList.add(dateOfString(strDateSet[i]));
        }

        Collections.sort(dateList, dateComparator);
        System.out.println(dateList);

        pojo.Date start = dateOfString(startDate);
        pojo.Date end = dateOfString(endDate);

        int startIndex = dateList.indexOf(start);
        int endIndex = dateList.indexOf(end);

        List<Date> dateSubList = dateList.subList(startIndex, endIndex+1);

        List<String> stringDateSubList = new ArrayList<String>();
        for(Date date : dateSubList) {
            stringDateSubList.add(stringOfDate(date));
        }
        return stringDateSubList;

    }
}