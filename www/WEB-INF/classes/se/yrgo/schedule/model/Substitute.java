package se.yrgo.schedule.model;

/**A Substitute (new class representing the substitute teacher with a name)*/
public class Substitute {

    private String name;

    /** Simple constructor
     * 
     * @param name Name of the teacher substitute
     */
    public Substitute(String name) {
        this.name = name;
    }

    /** Simple getter
     * 
     * @return Name of the substitute teacher
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Substitute [name=" + name + "]";
    }
}
