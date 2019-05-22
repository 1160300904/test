package physicalObject;

/**
 * This is a factory interface of Athlete.
 *
 */
public class AthleteFactory {
    /**
     * get an instance of an athlete.
     */
    public static Athlete getInstance(String name, int number, String nation, int age, double bestscore) {
        return new ConcreteAthlete(name, number, nation, age, bestscore);
    }
}
