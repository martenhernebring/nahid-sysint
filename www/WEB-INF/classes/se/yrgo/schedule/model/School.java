package se.yrgo.schedule.model;

public class School {

    private String name;
    private String address;

    // A School (new class representing the school of the assignment with a name and an address of the school)
    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "School [name=" + name + ", address=" + address + "]";
    }

    public String getAddress() {
        return address;
    }
}
