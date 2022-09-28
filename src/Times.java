import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Times {

    Date currentDate = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh.mm aa");
    Calendar calendar = Calendar.getInstance();


    public void times() {
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(timeFormat.format(calendar.getTime()));
    }


    public Integer minConverter(String timeToConvert) {
        return Integer.parseInt(timeToConvert) / 60;
    }


//    Todo: count from current time to the player time wanted to play

    public String timeWantedToPLay(int currentTime, int halfHour, int currentTimeMinutes) {
        if (currentTime == 0) {
            calendar.set(Calendar.MINUTE, currentTimeMinutes + halfHour);
            calendar.set(Calendar.SECOND, 0);
        } else {
            calendar.set(Calendar.HOUR, currentTime);
        }
//        System.out.println(timeFormat.format(calendar.getTime()));
        return timeFormat.format(calendar.getTime());
    }


    public String getCurrentTime() {
        return timeFormat.format(calendar.getTime());

    }
    public int getDays() {
        Calendar months = Calendar.getInstance();
        return months.get(Calendar.DAY_OF_MONTH);
    }
    public LocalDate getMonths() {

        return LocalDate.now();
    }


}
