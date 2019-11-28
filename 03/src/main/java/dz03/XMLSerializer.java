package dz03;

public class XMLSerializer extends MySerializer {
    @Override
    void writeObjectBegin(StringBuilder sb) {
        sb.append("\n");
    }

    @Override
    void writeObjectEnd(StringBuilder sb, String prefix) {
        sb.append(prefix);
    }

    @Override
    void writeArrayBegin(StringBuilder sb) {
        sb.append("\n");
    }

    @Override
    void writeArrayEnd(StringBuilder sb, String prefix) {
        sb.append(prefix);
    }

    @Override
    void writeBegin(StringBuilder sb, String name, String prefix) {
        sb.append(prefix).append("<").append(name).append(">\n");
    }

    @Override
    void writeFieldBegin(StringBuilder sb, String name) {
        sb.append("<").append(name).append(">");
    }

    @Override
    void writeCounterBegin(StringBuilder sb, int counter, String prefix) {
        sb.append(prefix).append("<").append(counter).append(">");
    }

    @Override
    void writeCounterEnd(StringBuilder sb, int counter, String prefix) {
        sb.append("</").append(counter).append(">\n");
    }

    @Override
    void writePrimitiveValue(StringBuilder sb, Object o) {
        sb.append(o.toString());
    }

    @Override
    void writeFieldEnd(StringBuilder sb, String name) {
        sb.append("</").append(name).append(">");
    }

    @Override
    void writeEnd(StringBuilder sb, String name, String prefix) {
        sb.append(prefix).append("</").append(name).append(">");
    }
}
