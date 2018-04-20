package de.cronus.runtime.util;

public class PrimitiveTypeUtil {

    private PrimitiveTypeUtil() {
    }

    public static final boolean isPrimitive(String typeName) {
        if (typeName == null)
            return false;
        if (typeName.equals("byte"))
            return true;
        if (typeName.equals("short"))
            return true;
        if (typeName.equals("int"))
            return true;
        if (typeName.equals("long"))
            return true;
        if (typeName.equals("char"))
            return true;
        if (typeName.equals("float"))
            return true;
        if (typeName.equals("double"))
            return true;
        if (typeName.equals("boolean"))
            return true;
        if (typeName.equals("void"))
            return true;
        return false;
    }

    public static final Class<?> getPrimitiveClass(String typeName) {
        if (typeName.equalsIgnoreCase("byte"))
            return byte.class;
        if (typeName.equalsIgnoreCase("short"))
            return short.class;
        if (typeName.equalsIgnoreCase("integer"))
            return int.class;
        if (typeName.equalsIgnoreCase("long"))
            return long.class;
        if (typeName.equalsIgnoreCase("char"))
            return char.class;
        if (typeName.equalsIgnoreCase("float"))
            return float.class;
        if (typeName.equalsIgnoreCase("double"))
            return double.class;
        if (typeName.equalsIgnoreCase("boolean"))
            return boolean.class;
        if (typeName.equalsIgnoreCase("void"))
            return void.class;
        return null;
    }

    public static final String getTypeDefaultValue(String typeName) {
        if (typeName.equals("byte"))
            return "0";
        if (typeName.equals("short"))
            return "0";
        if (typeName.equals("int"))
            return "0";
        if (typeName.equals("long"))
            return "0L";
        if (typeName.equals("char"))
            return "'\u0000'";
        if (typeName.equals("float"))
            return "0.0F";
        if (typeName.equals("double"))
            return "0.0";
        if (typeName.equals("boolean"))
            return "false";
        if (typeName.equals("void"))
            return "void";
        throw new IllegalArgumentException("Not primitive type : " + typeName);
    }

}