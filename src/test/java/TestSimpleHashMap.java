import homework02.SimpleHashMap;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.*;

public class TestSimpleHashMap {
    private SimpleHashMap<String, String> m=new SimpleHashMap<String,String>();

    @Test
    public void testInsert() {
        assertEquals("один", m.put("1","один"));
        assertTrue(m.size()==1);
        assertTrue(m.contains("1"));
        assertFalse(m.contains("2"));
        assertFalse(m.contains("3"));
        assertEquals("два",m.put("2","два"));
        assertTrue(m.size()==2);
        assertTrue(m.contains("1"));
        assertTrue(m.contains("2"));
        assertFalse(m.contains("3"));
        assertEquals("три",m.put("3","три"));
        assertTrue(m.size()==3);
        assertTrue(m.contains("1"));
        assertTrue(m.contains("2"));
        assertTrue(m.contains("3"));

        assertEquals("четыре",m.put("4","четыре"));
        assertEquals("пять",m.put("5","пять"));
        assertEquals("шесть",m.put("6","шесть"));
        assertEquals("семь",m.put("7","семь"));
        assertEquals("восемь",m.put("8","восемь"));
        assertEquals("девять",m.put("9","девять"));
        assertEquals("десять",m.put("10","десять"));
        assertEquals("одиннадцать",m.put("11","одиннадцать"));
        assertEquals("двенадцать",m.put("12","двенадцать"));
        System.out.println(m.values());
        //Здесь должен выполниться resize
        assertEquals("тринадцать",m.put("13","тринадцать"));
        System.out.println(m.values());
        assertEquals("четырнадцать",m.put("14","четырнадцать"));
        assertEquals("пятнадцать",m.put("15","пятнадцать"));
        assertEquals("шестнадцать",m.put("16","шестнадцать"));
        assertTrue(m.size()==16);

        System.out.println(m.values());
    }

    @Test
    public void testGetPut() {
        testInsert();
        assertTrue(m.get("13")=="тринадцать");
        assertEquals("ТРИНАДЦАТЬ",m.put("13","ТРИНАДЦАТЬ"));
        assertTrue(m.get("13")=="ТРИНАДЦАТЬ");
        assertTrue(m.size()==16);

        assertEquals("сорок два",m.put("сорок два","сорок два"));
        assertTrue(m.get("сорок два")=="сорок два");
        assertTrue(m.size()==17);

        assertEquals("СОРОК ДВА",m.put("СОРОК ДВА","СОРОК ДВА"));
        assertTrue(m.get("СОРОК ДВА")=="СОРОК ДВА");
        assertTrue(m.size()==18);

        System.out.println(m.values());
    }

    @Test
    public void testRemove() {
        testInsert();
        assertEquals("один", m.remove("1"));
        assertTrue(m.size()==15);
        assertEquals("тринадцать", m.remove("13"));
        assertTrue(m.size()==14);

        Set<String> keys=m.keySet();
        assertTrue(keys.contains("2"));
        assertFalse(keys.contains("1"));
        assertFalse(keys.contains("13"));

        Collection<String> values=m.values();

        assertTrue(values.contains("два"));
        assertFalse(values.contains("один"));
        assertFalse(values.contains("тринадцать"));

        System.out.println(m.values());
    }
}
