package com.big0soft.resource.helper;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static final String NORMAL_TIME_FORMAT = "HH:mm:ss";
    public static final String NORMAL_DATE_FORMAT = "yyyy/MM/dd";
    public static final String FORMAT_DATE_1 = NORMAL_DATE_FORMAT + " " + NORMAL_TIME_FORMAT;
    public static final String FORMAT_WITHOUT_CODE = "yyyyMMdd HHmmss";
    //    2022-07-06 03:00
    public static final String FULL_DATE = "yyyy-MM-dd - hh:mm";
    public static final String COUNT_DOWN_FORMAT = "dd:hh:mm:ss";
    public static final String COUNT_DOWN_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static SimpleDateFormat simpleDateFormat;

    public static String dateHelper(String pattern) {
        return simpleDateFormat(pattern).format(new Date());
    }

    public static String compateDates() {
        return "";
    }

    public static SimpleDateFormat simpleDateFormat(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return simpleDateFormat;
    }

    public static class ComparisonDate {
        /**
         * @param date1 should not equals realtime
         * @param date2 should equals realtime
         * @return <p>
         * -1 the date end
         * </p>
         * <p>
         * 0 date will end today or you give the same date
         * </p>
         * <p>
         * 1 date not end
         * </p>
         * @throws ParseException if compar throw exception will return 0 value
         */
        public static int compar(String format, String date1, String date2) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            Date strDate;
            Date strDate2;
            try {
                strDate = sdf.parse(date1);
                strDate2 = sdf.parse(date2);
            } catch (ParseException e) {
                return 0;
            }
//            key not end -1
//            key will end roday 0
//            key end 1
            return strDate.compareTo(strDate2);
        }
    }

    public static class ConvertDateHelper {
        public static <T extends Context> boolean timeZoneIsAutomaticly(T context) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        }

        public static long dateConverter(int typefield, int numberIncrease) {
            SimpleDateFormat sdf = simpleDateFormat(COUNT_DOWN_FORMAT);
            String dt = sdf.format(new Date(System.currentTimeMillis()));
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(typefield, numberIncrease);
            // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = simpleDateFormat(COUNT_DOWN_FORMAT);
//            return sdf1.format(c.getTime());
            return c.getTime().getTime();
        }

        public static String convertFromMsTO_Date(long ms, String formant) {
            SimpleDateFormat formatter = new SimpleDateFormat(formant, Locale.ENGLISH);
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);
            return formatter.format(calendar.getTime());
        }

        public static long convertDateToCurrentTime(String date, String format) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            Date strDate2 = sdf.parse(DateHelper.dateHelper(format));
            return sdf.parse(date).getTime() - strDate2.getTime();
//            return sdf.parse(date).getTime()-strDate2.getTime();
        }

        public static String getDateByMS(long milliSeconds, String dateFormat) {
            simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return simpleDateFormat.format(calendar.getTime());
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public static LocalDateTime longMsToLocalDate(long ms) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault());
        }

        public static String formatDate(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_1, Locale.ENGLISH);
            return sdf.format(date);
        }
    }
}
