package app;


import model.Address;
import model.Name;
import model.Person;

import java.util.*;

public class FindPeopleRelations {

    private Map<Person, Set<Person>> relationGraph = new HashMap<>();

    public void init(Person[] people) {
        Map<Name, Set<Person>> namesMap = new HashMap<>();
        Map<Address, Set<Person>> addressesMap = new HashMap<>();

        // map names and address to people
        for (int i = 0; i < people.length; i++) {
            Person person = people[i];
            Name personName = person.getFullName();
            Address personAddress = person.getAddress();

            if (namesMap.containsKey(personName)) {
                Set<Person> personSet = namesMap.get(personName);
                personSet.add(person);
                namesMap.put(personName, personSet);
            }
            else {
                Set<Person> personSet = new HashSet<>();
                personSet.add(person);
                namesMap.put(personName, personSet);
            }
            
            if (addressesMap.containsKey(personAddress)) {
                Set<Person> personSet = addressesMap.get(personAddress);
                personSet.add(person);
                addressesMap.put(personAddress, personSet);
            }
            else {
                Set<Person> personSet = new HashSet<>();
                personSet.add(person);
                addressesMap.put(personAddress, personSet);
            }
        }
        
        // create graph with relationships
        for (int i = 0; i < people.length; i++) {
            Person person = people[i];
            Name personName = person.getFullName();
            Address personAddress = person.getAddress();

            Set<Person> relationSet = namesMap.get(personName);

            relationSet.addAll(addressesMap.get(personAddress));
            relationSet.remove(person); // remove himself

            relationGraph.put(person, relationSet);
        }
    }

    public int findMinRelationLevel(Person source, Person dest) {
        int path = 0;
        Map<Person, Boolean> visitedPersonMap = new HashMap<>();
        Map<Person, Person> prevMap = new HashMap<>();
        List<Person> relationPath = new ArrayList<>(); // this will hold the path
        Queue<Person> queue = new LinkedList();

        Person current = source;
        queue.add(current);
        visitedPersonMap.put(current, true);
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.equals(dest)) {
                break;
            }
            else {
                for (Person p : relationGraph.get(current)) {
                    if (!visitedPersonMap.containsKey(p)) {
                        queue.add(p);
                        visitedPersonMap.put(p, true);
                        prevMap.put(p, current);
                    }
                }
            }
        }

        if (!current.equals(dest)) {
            return -1;
        }
        for (Person p = dest; p != null; p = prevMap.get(p)) {
            relationPath.add(p);
            path ++;
        }
        return path;
    }
}
