package ch22pc02;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author frank
 */
public class Person {
    String name;
    int age;
    String gender;
    Set<Person> siblings;
    Set<Person> children;
    Person spouse;
    Set<Person> parents;
    
    // Constructor
    public Person(String name) {
        this.name = name;
    }
    
    // Constructor
    public Person() {}
    
    
    /**
     * Method to get the person's spouse
     * @return Person spouse
     */
    public Person getSpouse() {
        return spouse;
    }
    
    /**
     * Method to set the person's spouse
     * @param spouse Person The person's spouse
     */
    public void setSpouse(Person spouse) {
        this.spouse = spouse;
        if(null == spouse.getSpouse()) {
            spouse.setSpouse(this);
        }
        this.setChildren(spouse.getChildren());
    }
    
    /**
     * Method to set the parents for the person
     * @param parent Set<Person> The set of Person classes that represent the parents
     */
    private void setParents(Set<Person> parent) {
        this.parents = parent;
    }
    
    /**
     * Method to set the children for the person
     * @param children Set<Person> The set of Person classes that represent the children
     */
    private void setChildren(Set<Person> children) {
        this.children = children;
    }
    
    /**
     * Method to add a parent to the person
     * @param parent Person The person that is the parent
     */
    public void addParent(Person parent) {
        if(null != parents) {
            if(!isPersonAlreadyAdded(parents, parent) && parents.size() < 2) {
                parents.add(parent);
            } else {
                return;
            }
        } else {
            parents = new HashSet<Person>(2);
            parents.add(parent);
        }
        Set<Person> tempSiblings = getSiblings();
        if(null != tempSiblings) {
            for(Person sib:tempSiblings) {
                sib.addParent(parent);
            }
        }
        parent.addChild(this);
        if(null != parent.getSpouse()) {
            parent.getSpouse().addChild(this);
        }
    }
    
    /**
     * Method to get the name of the person
     * @return String The name of the person
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method to set the name of the person
     * @param name String The name of the person
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method to get the age of the person
     * @return int The age of the person
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Method to set the age of the person
     * @param age int The age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Method to get the person's gender
     * @return String The person's gender
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Method to set the person's gender
     * @param gender String The person's gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Method to add a sibling to the person
     * @param person Person The class of Person that is the person's sibling
     */
    public void addSibling(Person person) {
        if(null != siblings) {
            if(!isPersonAlreadyAdded(siblings,person)) {
                siblings.add(person);
            } else {
                return;
            }
        } else {
            siblings = new HashSet<Person>();
            siblings.add(person);
        }
        
        if(null != this.parents) {
            if(null != person.getParents()) {
                if(this.parents.size() > person.getParents().size()) {
                    person.setParents(this.parents);
                } else if (this.parents.size() < person.getParents().size()) {
                    this.setParents(person.parents);
                }
            } else {
                for(Person parent:this.parents) {
                    person.addParent(parent);
                }
            }
        }
        person.addSibling(this);
    }
    
    /**
     * Method to get the person's parents
     * @return Set<Person> The set of Persons that represent this person's parents
     */
    public Set<Person> getParents() {
        return parents;
    }
    
    /**
     * Boolean method to determine if the Person is already added
     * @param relations Set<Person> The set of people to be determined if they have been added to the person
     * @param person Person The person to be compared with to determine if the relationships already exist
     * @return 
     */
    private boolean isPersonAlreadyAdded(Set<Person> relations, Person person) {
        boolean isAdded = false;
        for(Person per:relations) {
            if(per.equals(person)) {
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }
    
    /**
     * Method to add a child to the person
     * @param person Person The person to be added as a child
     */
    public void addChild(Person person) {
        if(null != children) {
            if(!isPersonAlreadyAdded(children, person)) {
                children.add(person);
            } else {
                return;
            }
        } else {
            children = new HashSet<Person>();
            children.add(person);
        }
        
        person.addParent(this);
        
        if(null != this.getSpouse()) {
            this.getSpouse().addChild(person);
        }
    }
    
    /**
     * Method to get the set of Persons who are the person's siblings
     * @return Set<Person> The set of Persons who are the person's siblings
     */
    public Set<Person> getSiblings() {
        return siblings;
    }
    
    /**
     * Method to get the set of Persons who are the person's children
     * @return Set<Person> The set of Persons who are the person's children
     */
    public Set<Person> getChildren() {
        return children;
    }
    
    /**
     * Method to get the person's mother
     * @return Person The person's mother
     */
    public Person getMother() {
        if (null != parents) {
            for(Person parent : parents) {
                if (parent instanceof FemalePerson) { // This needs to look at the person's gender
                    return parent;
                }
            }
        }
        return null;
    }
    
    /**
     * Method to get the person's father
     * @return Person The person's father
     */
    public Person getFather() {
        if (null != parents) {
            for(Person parent : parents) {
                if (parent instanceof MalePerson) { // This needs to look at the person's gender
                    return parent;
                }
            }
        }
        return null;
    }
    
    /**
     * Method to print the name of the person
     * @return 
     */
    @Override
    public String toString() {
        if (null != getName()) {
            return getName();
        }
        return super.toString();
    }
}