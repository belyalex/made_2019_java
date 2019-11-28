package dz03;

import java.lang.reflect.Field;
import java.util.Collection;

public abstract class MySerializer implements Serializer{
    private final String prefixStep="    ";
    @Override
    public String serialize(Object o) {
        StringBuilder sb=new StringBuilder();
        serialize(sb, o, null, "");
        return sb.toString();
    }

    private void serialize(StringBuilder sb, Object o, String name, String prefix) {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (name==null) {
            writeBegin(sb, clazz.getSimpleName(), prefix);
        }
        for (Field f : fields) {
            f.setAccessible(true);
            writeField(sb, f, o, prefix+prefixStep);
        }
        if (name==null) {
            writeEnd(sb, clazz.getSimpleName(), prefix);
        }
    }

    private void writeField(StringBuilder sb, Field f, Object o, String prefix) {
        sb.append(prefix);
        writeFieldBegin(sb, f.getName());
        writeFieldValue(sb, f, o, prefix);
        writeFieldEnd(sb, f.getName());
        sb.append("\n");
    }

    private void writeFieldValue(StringBuilder sb, Field f, Object o, String prefix) {
        Object obj=null;
        try {
            obj=f.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        writeObj(sb, f.getName(), prefix, obj);
    }

    private void writeObj(StringBuilder sb, String name, String prefix, Object obj) {
        if (obj==null) {
            writePrimitiveValue(sb,"null");
            return;
        }
        if (obj.getClass().isPrimitive()||(obj instanceof String)) {
            writePrimitiveValue(sb,obj);
        }
        else {
            if (obj.getClass().isArray()||(obj instanceof Collection)) {
                writeArrayBegin(sb);
                writeArray(sb, (Collection)obj, prefix+prefixStep);
                writeArrayEnd(sb, prefix);
            }
            else {
                writeObjectBegin(sb);
                serialize(sb, obj, name,prefix);
                writeObjectEnd(sb, prefix);
            }

        }
    }

    private void writeArray(StringBuilder sb, Collection objects, String prefix) {
        int counter=1;
        for (Object obj : objects) {
            writeCounterBegin(sb, counter,prefix);
            writeObj(sb, Integer.toString(counter), prefix, obj);
            writeCounterEnd(sb, counter,prefix);
            counter++;
        }
    }

    abstract void writeObjectBegin(StringBuilder sb);

    abstract void writeObjectEnd(StringBuilder sb, String prefix);

    abstract void writeArrayBegin(StringBuilder sb);

    abstract void writeArrayEnd(StringBuilder sb, String prefix);

    abstract void writeBegin(StringBuilder sb, String name, String prefix);

    abstract void writeFieldBegin(StringBuilder sb, String name);

    abstract void writeCounterBegin(StringBuilder sb, int counter, String prefix);

    abstract void writeCounterEnd(StringBuilder sb, int counter, String prefix);

    abstract void writePrimitiveValue(StringBuilder sb, Object o);

    abstract void writeFieldEnd(StringBuilder sb, String name);

    abstract void writeEnd(StringBuilder sb, String name, String prefix);
}
