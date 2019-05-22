package applications;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This is an immutable type represents the date in the PersonalAppEcosystem. It
 * is IMMUTABLE.
 */
public class MyDate implements Comparable<MyDate> {

    private LocalDateTime t;

    /*
     * Abstract Function: 1.t means the time stamp of this MyDate class. 2.t is
     * immutable type so it is safer to use it as a field.
     */
    /*
     * Rep Invariant: none.
     */
    /**
     * Creator of the MyDate using an instance of LocalDateTime
     * 
     * @param lt the LocalDateTime you want to wrap to a MyDate.
     */
    MyDate(LocalDateTime lt) {
        // t is immutable type so it is safe not defensive copying.
        this.t = lt;
    }

    /**
     * Creator of the MyDate using concrete time.
     * 
     * @param year   year of the time you want to create.
     * @param month  month of the time you want to create.
     * @param day    day of the time you want to create.
     * @param hour   hour of the time you want to create.
     * @param minute minute of the time you want to create.
     * @param second second of the time you want to create.
     */
    MyDate(int year, int month, int day, int hour, int minute, int second) {
        assert year >= 0;
        assert month >= 0;
        assert day >= 0;
        assert hour >= 0;
        assert minute >= 0;
        assert second >= 0;
        t = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Get the time stamp of a MyDate instance as a form of LocalDateTime.
     * 
     * @return the time stamp of a MyDate instance as a form of LocalDateTime.
     */
    LocalDateTime getTimeStamp() {
        // t is immutable type so it is safe not defensive copying.
        assert this.t != null;
        return this.t;
    }

    /**
     * Add specified year to the MyDate object.
     * 
     * @param y years you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addYear(int y) {
        assert y >= 0;
        LocalDateTime l = t.plusYears(y);
        // defensive copy here to avoid rep exposure.
        return new MyDate(l);
    }

    /**
     * Add specified month to the MyDate object.
     * 
     * @param m month you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addMonth(int m) {
        assert m >= 0;
        LocalDateTime l = t.plusMonths(m);
        // defensive copy here to avoid rep exposure.
        return new MyDate(l);
    }

    /**
     * Add specified day to the MyDate object.
     * 
     * @param d day you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addDay(int d) {
        assert d >= 0;
        LocalDateTime l = t.plusDays(d);
        // defensive copy here to avoid rep exposure.
        return new MyDate(l);
    }

    /**
     * Add specified hour to the MyDate object.
     * 
     * @param y hour you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addHour(int y) {
        assert y >= 0;
        LocalDateTime l = t.plusHours(y);
        return new MyDate(l);
    }

    /**
     * Add specified minute to the MyDate object.
     * 
     * @param y minute you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addMinute(int y) {
        assert y >= 0;
        LocalDateTime l = t.plusMinutes(y);
        return new MyDate(l);
    }

    /**
     * Add specified second to the MyDate object.
     * 
     * @param y second you want to add.
     * @return a new instance of MyDate of the time you want.
     */
    MyDate addSecond(int y) {
        assert y >= 0;
        LocalDateTime l = t.plusSeconds(y);
        return new MyDate(l);
    }
    /**
     * truncate the MyDate instance by year.
     * 
     * @return A new Instance which all its fields are zero except for year based on
     *         its old time.
     */
    /*
     * MyDate truncYear() { LocalDateTime l=t.truncatedTo(ChronoUnit.YEARS); return
     * new MyDate(l); }
     */
    /**
     * truncate the MyDate instance by month.
     * 
     * @return A new Instance which all its fields are zero except for year and
     *         month based on its old time.
     */

    /*
     * MyDate truncMonth() { LocalDateTime l=t.truncatedTo(ChronoUnit.MONTHS);
     * return new MyDate(l); }
     */
    /**
     * truncate the MyDate instance by day.
     * 
     * @return A new Instance which all its fields are zero except for year, month
     *         and day based on its old time.
     */
    MyDate truncDay() {
        LocalDateTime l = t.truncatedTo(ChronoUnit.DAYS);
        return new MyDate(l);
    }

    /**
     * truncate the MyDate instance by hour.
     * 
     * @return A new Instance which all its fields are zero except for year, month
     *         and day and hour based on its old time.
     */
    MyDate truncHour() {
        LocalDateTime l = t.truncatedTo(ChronoUnit.HOURS);
        return new MyDate(l);
    }

    /**
     * truncate the MyDate instance by minute.
     * 
     * @return A new Instance which all its fields are zero except for year, month
     *         and day and hour and minute based on its old time.
     */
    MyDate truncMinute() {
        LocalDateTime l = t.truncatedTo(ChronoUnit.MINUTES);
        return new MyDate(l);
    }

    /**
     * truncate the MyDate instance by second.
     * 
     * @return A new Instance which all its fields are zero except for year, month
     *         and day and hour and minute and second based on its old time.
     */
    MyDate truncSecond() {
        LocalDateTime l = t.truncatedTo(ChronoUnit.SECONDS);
        return new MyDate(l);
    }

    /**
     * Get the year of the MyDate instance.
     * 
     * @return the year of the MyDate instance.
     */
    public int getYear() {
        return t.getYear();
    }

    /**
     * Get the month of the MyDate instance.
     * 
     * @return the month of the MyDate instance.
     */
    public int getMonth() {
        return t.getMonthValue();
    }

    /**
     * Get the day of the MyDate instance.
     * 
     * @return the day of the MyDate instance.
     */
    public int getDay() {
        return t.getDayOfMonth();
    }

    /**
     * Get the hour of the MyDate instance.
     * 
     * @return the hour of the MyDate instance.
     */
    public int getHour() {
        return t.getHour();
    }

    /**
     * Get the minute of the MyDate instance.
     * 
     * @return the minute of the MyDate instance.
     */
    public int getMinute() {
        return t.getMinute();
    }

    /**
     * Get the second of the MyDate instance.
     * 
     * @return the second of the MyDate instance.
     */
    public int getSecond() {
        return t.getSecond();
    }

    @Override
    public int compareTo(MyDate d) {
        return t.compareTo(d.getTimeStamp());
    }

    /**
     * Given three MyDate instance, return serial number of MyDate which has the
     * earliest time.
     * 
     * @param d1 One MyDate instance you want to compare.
     * @param d2 Another MyDate instance you want to compare.
     * @param d3 Another MyDate instance you want to compare.
     * @return 1 means d1 is the earliest, 2 means d2 is the earliest, 3 means d3 is
     *         the earliest.
     */
    public static int getMin(MyDate d1, MyDate d2, MyDate d3) {
        assert d1 != null;
        assert d2 != null;
        assert d3 != null;
        MyDate min = null;
        int retvalue;
        if (d1.compareTo(d2) <= 0) {
            if (d3.compareTo(d1) <= 0) {
                retvalue = 3;
            } else {
                retvalue = 1;
            }
        } else {
            if (d3.compareTo(d2) <= 0) {
                retvalue = 3;
            } else {
                retvalue = 2;
            }
        }
        // the return value is 1 or 2 or 3, so here use assert to check postcondition.
        assert (retvalue == 1) || (retvalue == 2) || (retvalue == 3);
        return retvalue;

    }

    /**
     * Truncate the MyDate instance based on the given strategy.
     * 
     * @param split     the truncate strategy used to truncate.
     * @param periodend The MyDate instance which is going to be truncated.
     * @return A new instance of MyDate after truncated.
     */
    public static MyDate TruncBaseOnSplit(String split, MyDate periodend) {
        /*
         * assert split.equals("Hour")||split.equals("Month")||
         * split.equals("Week")||split.equals("Day");
         */
        if (split.equals("Hour")) {
            return periodend.truncHour();
        } else if (split.equals("Month")) {
            return periodend.truncDay();
        } else if (split.equals("Week")) {
            return periodend.truncDay();
        } else if (split.equals("Day")) {
            return periodend.truncDay();
        }
        return null;
    }

    /**
     * Increase the MyDate instance based on the given strategy.
     * 
     * @param split     the strategy used to add time.
     * @param periodend The MyDate instance which is going to be increased.
     * @return A new instance of MyDate later than the old one.
     */
    public static MyDate addBaseOnSplit(String split, MyDate periodend) {
        if (split.equals("Hour")) {
            return periodend.addHour(1);
        } else if (split.equals("Month")) {
            return periodend.addMonth(1);
        } else if (split.equals("Week")) {
            return periodend.addDay(7);
        } else if (split.equals("Day")) {
            return periodend.addDay(1);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyDate))
            return false;
        MyDate m = (MyDate) o;
        if (this.compareTo(m) == 0)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return t.toString();
    }

    @Override
    public int hashCode() {
        return this.t.hashCode();
    }
}
