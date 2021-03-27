package se.yrgo.schedule.model;

/** A School (new class representing the school of the assignment with a name and an address of the school)*/
public class School {

    private String name;
    private String address;

    /** A simple constructor
     * @param name Name of the school.
     * @param address The school address.
     * */
    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /** A simple getter
     * @return The name of the school.
     * */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "School [name=" + name + ", address=" + address + "]";
    }

    /** A simple getter
     * @return The address of the school.
     * */
    public String getAddress() {
        return address;
    }
}
