package dz03;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person Ivanov=new Person("Иван", "Иванов", new Address("Москва","101101"), List.of("123-1234-523", "432-23-232-23"));

        Person Petrov=new Person(null, "Петров", new Address("Москва",null), List.of("123-1234-523", "432-23-232-23"));

        Serializer xmlSerializer=new XMLSerializer();

        System.out.println(xmlSerializer.serialize(Ivanov));
        System.out.println(xmlSerializer.serialize(Petrov));

        Serializer jsonSerializer=new JSONSerializer();
        System.out.println(jsonSerializer.serialize(Ivanov));
        System.out.println(jsonSerializer.serialize(Petrov));
    }
}
