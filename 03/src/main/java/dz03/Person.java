package dz03;

import java.util.List;

class Person {
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final List<String> phoneNumbers;

    Person(String firstName, String lastName, Address address, List<String> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }
}
