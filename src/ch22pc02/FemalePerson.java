package ch22pc02;

/**
 * 
 * @author frank
 */
public class FemalePerson extends Person {

    // Constructors
    public FemalePerson(String name) {
        this.name = name;
        gender = "Female";
    }
    
    public FemalePerson() {
        gender = "Female";
    }
}
