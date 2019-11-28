package dz03;

public class JSONSerializer extends MySerializer {

    @Override
    void writeObjectBegin(StringBuilder sb) {
        sb.append("{\n");
    }

    @Override
    void writeObjectEnd(StringBuilder sb, String prefix) {
        sb.setLength(sb.length()-2);
        sb.append("\n").append(prefix).append("}");
    }

    @Override
    void writeArrayBegin(StringBuilder sb) {
        sb.append("[\n");
    }

    @Override
    void writeArrayEnd(StringBuilder sb, String prefix) {
        sb.setLength(sb.length()-2);
        sb.append("\n").append(prefix).append("]");
    }

    @Override
    void writeBegin(StringBuilder sb, String name, String prefix) {
        sb.append(prefix).append("{\n");
    }

    @Override
    void writeFieldBegin(StringBuilder sb, String name) {
        sb.append("\"").append(name).append("\": ");
    }

    @Override
    void writeCounterBegin(StringBuilder sb, int counter, String prefix) {
        sb.append(prefix);
    }

    @Override
    void writeCounterEnd(StringBuilder sb, int counter, String prefix) {
        sb.append(",\n");
    }

    @Override
    void writePrimitiveValue(StringBuilder sb, Object o) {
        sb.append("\"").append(o.toString()).append("\"");
    }

    @Override
    void writeFieldEnd(StringBuilder sb, String name) {
        sb.append(",");
    }

    @Override
    void writeEnd(StringBuilder sb, String name, String prefix) {
        sb.setLength(sb.length()-2);
        sb.append("\n").append(prefix).append("}");
    }
}
