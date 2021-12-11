package sample.Model;

/**
 * This class contains the variables and methods that manage appointment reports.
 */
public class AppointmentReport {
    String types;
    int count;

    public void setTypes(String types) {
        this.types = types;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    String month;

    /**
     * Gets the type of appointment.
     * @return This returns the type of appointment.
     */

    public String getTypes() {
        return types;
    }

    /**
     * This sets the type of appointment.
     * @param type This sets the type of appointment.
     */

    public void setType(String type) {
        this.types = types;
    }

    /**
     * This gets the count of appointments.
     * @return This returns the count of appointments.
     */

    public int getCount() {
        return count;
    }

    /**
     * This sets the count of appointments.
     * @param count This passes an integer value to set the count of appointments.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * This is the constructor of the appointment report class.
     * @param types This is a string value that represents the type of appointment.
     * @param count This is a integer value that represents the count of appointments.
     */

    public AppointmentReport(String types, int count, String month) {
        this.types = types;
        this.count = count;
        this.month = month;
    }



}
