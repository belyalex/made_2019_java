package dz03;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializerTests {

    Person Ivanov=new Person("Иван", "Иванов", new Address("Москва","101101"), List.of("123-1234-523", "432-23-232-23"));
    Person Petrov=new Person(null, "Петров", new Address("Москва",null), List.of("123-1234-523", "432-23-232-23"));

    String IvanovXML=
            "<Person>\n" +
            "    <firstName>Иван</firstName>\n" +
            "    <lastName>Иванов</lastName>\n" +
            "    <address>\n" +
            "        <city>Москва</city>\n" +
            "        <postalCode>101101</postalCode>\n" +
            "    </address>\n" +
            "    <phoneNumbers>\n" +
            "        <1>123-1234-523</1>\n" +
            "        <2>432-23-232-23</2>\n" +
            "    </phoneNumbers>\n" +
            "</Person>";

    String PetrovXML=
            "<Person>\n" +
            "    <firstName>null</firstName>\n" +
            "    <lastName>Петров</lastName>\n" +
            "    <address>\n" +
            "        <city>Москва</city>\n" +
            "        <postalCode>null</postalCode>\n" +
            "    </address>\n" +
            "    <phoneNumbers>\n" +
            "        <1>123-1234-523</1>\n" +
            "        <2>432-23-232-23</2>\n" +
            "    </phoneNumbers>\n" +
            "</Person>";

    String IvanovJSON=
            "{\n" +
            "    \"firstName\": \"Иван\",\n" +
            "    \"lastName\": \"Иванов\",\n" +
            "    \"address\": {\n" +
            "        \"city\": \"Москва\",\n" +
            "        \"postalCode\": \"101101\"\n" +
            "    },\n" +
            "    \"phoneNumbers\": [\n" +
            "        \"123-1234-523\",\n" +
            "        \"432-23-232-23\"\n" +
            "    ]\n" +
            "}";

    String PetrovJSON=
            "{\n" +
            "    \"firstName\": \"null\",\n" +
            "    \"lastName\": \"Петров\",\n" +
            "    \"address\": {\n" +
            "        \"city\": \"Москва\",\n" +
            "        \"postalCode\": \"null\"\n" +
            "    },\n" +
            "    \"phoneNumbers\": [\n" +
            "        \"123-1234-523\",\n" +
            "        \"432-23-232-23\"\n" +
            "    ]\n" +
            "}";

    @Test
    public void TestXMLSerializer() {
        Serializer serializer=new XMLSerializer();

        assertEquals(IvanovXML, serializer.serialize(Ivanov));
        assertEquals(PetrovXML, serializer.serialize(Petrov));
    }

    @Test
    public void TestJSONSerializeer() {
        Serializer serializer=new JSONSerializer();

        assertEquals(IvanovJSON, serializer.serialize(Ivanov));
        assertEquals(PetrovJSON, serializer.serialize(Petrov));
    }
}
