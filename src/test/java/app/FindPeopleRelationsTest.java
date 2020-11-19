package app;

import model.Address;
import model.Name;
import model.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindPeopleRelationsTest {
    private FindPeopleRelations findPeopleRelations = new FindPeopleRelations();

    @Test
    public void initPeopleTest() {
        findPeopleRelations.init(initPeopleArray());
    }

    @Test
    public void findPath() {
        Person[] people = initPeopleArray();
        Person ohad = people[0];
        Person johny = people[3];

        findPeopleRelations.init(people);
        int minRelationLevel = findPeopleRelations.findMinRelationLevel(ohad, johny);
        System.out.println(minRelationLevel);
        assertEquals(3, minRelationLevel);
    }

    private Person[] initPeopleArray() {
        Name ohad = new Name("Ohad", "Green");
        Address a1 = new Address("s1", "c1");

        Name geva = new Name("Geva", "Green");
        Address a2 = new Address("s2", "c2");

        Name johny = new Name("John", "Doe");
        Name rachel = new Name("Rachel", "Green");

        Person p1 = new Person(ohad, a1);
        Person p2 = new Person(geva, a1);
        Person p3 = new Person(geva, a2);
        Person p4 = new Person(johny, a2);
        Person p5 = new Person(rachel, a2);

        Person[] peopleArray = {p1, p2, p3, p4, p5};
        return peopleArray;
    }
}