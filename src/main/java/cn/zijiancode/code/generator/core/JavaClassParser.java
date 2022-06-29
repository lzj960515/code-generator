package cn.zijiancode.code.generator.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public final class JavaClassParser {

    private static final List<FieldInfo> FIELD_INFO_LIST = new ArrayList<>(10);

    private JavaClassParser() {
    }

    public static List<FieldInfo> getFieldInfoList() {
        return FIELD_INFO_LIST;
    }

    public static void clear() {
        FIELD_INFO_LIST.clear();
    }

    public static void parseField(String text) {
        // 分成一行行读取
        String[] lines = text.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("private") && line.endsWith(";")) {
                // 说明是个字段
                if (line.contains("static")) {
                    // 不读取静态常量
                    continue;
                }
                // private String name;
                String[] data = line.split("\\s+");
                FIELD_INFO_LIST.add(new FieldInfo(data[2].substring(0, data[2].length() - 1), "String".equals(data[1])));
            }
        }

    }

}
