package cn.zijiancode.code.generator.core;

/**
 * 字段信息
 *
 * @author Zijian Liao
 * @since 1.0.0
 */
public class FieldInfo {

    /**
     * 字段名
     */
    private String name;

    /**
     * 是否为字符串
     */
    private boolean isString;

    public FieldInfo(String name, boolean isString) {
        this.name = name;
        this.isString = isString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isString() {
        return isString;
    }

    public void setString(boolean string) {
        isString = string;
    }
}
