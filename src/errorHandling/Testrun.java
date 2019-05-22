package errorHandling;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.*;

public class Testrun {

    /*
     * public static void main(String[] args) throws SecurityException, IOException
     * {
     * 
     * Logger l=Logger.getLogger(Testrun.class.getName());
     * 
     * 
     * Handler h=new FileHandler("testrun.txt"); l.addHandler(h); //l.info("dfsd");
     * //l.info("dfdsd");
     * 
     * LogRecord lr=new LogRecord(Level.INFO, "D"); long mil=lr.getMillis();
     * 
     * //l.log(lr); Instant i=Instant.ofEpochMilli(mil); LocalDateTime
     * ldt=LocalDateTime.ofInstant(i, ZoneId.systemDefault());
     * //System.out.println(ldt);
     * 
     * String str=ldt.toString(); System.out.println(str);
     * System.out.println(parseTimeString(str).toString()); }
     * 
     * static LocalDateTime parseTimeString(String str) { String []
     * strs=str.split("T"); String date=strs[0]; String []dates=date.split("-");
     * String []times=strs[1].split(":"); String []second=times[2].split("\\.");
     * return LocalDateTime.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]),
     * Integer.valueOf(dates[2]), Integer.valueOf(times[0]),
     * Integer.valueOf(times[1]), Integer.valueOf(second[0])); }
     */
}
