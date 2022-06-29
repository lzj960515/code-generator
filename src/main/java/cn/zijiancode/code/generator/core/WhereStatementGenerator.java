package cn.zijiancode.code.generator.core;

import cn.zijiancode.code.generator.util.StringUtils;

import java.util.List;

/**
 * where
 * @author Zijian Liao
 * @since 1.0.0
 */
public class WhereStatementGenerator {

    private static final String IF_STRING_PATTERN = "<if test=\"#param.#name != null and #param.#name != ''\">";
    private static final String IF_PATTERN = "<if test=\"#param.#name != null\">";
    private static final String IF_AND = "  and #prefix`#snake_name` = #{#param.#name}";
    private static final String IF_END = "</if>";
    private static final String PARAM = "#param";
    private static final String NAME = "#name";
    private static final String PREFIX = "#prefix";
    private static final String SNAKE_NAME = "#snake_name";

    public static String generateWhereStatement(String prefix){
        return generateWhereStatement("param", prefix);
    }


    public static String generateWhereStatement(String param, String prefix){
        StringBuilder sb = new StringBuilder();
        List<FieldInfo> fieldInfoList = JavaClassParser.getFieldInfoList();
        for (FieldInfo fieldInfo : fieldInfoList) {
            String name = fieldInfo.getName();
            if(fieldInfo.isString()){
                sb.append(IF_STRING_PATTERN.replaceAll(PARAM, param).replaceAll(NAME, name));
            }else {
                sb.append(IF_PATTERN.replaceAll(PARAM, param).replaceAll(NAME, name));
            }
            sb.append("\n");
            sb.append(IF_AND.replaceAll(PREFIX, getPrefix(prefix))
                    .replaceAll(SNAKE_NAME, getSnakeName(name))
                    .replaceAll(PARAM, param)
                    .replaceAll(NAME, name));
            sb.append("\n");
            sb.append(IF_END);
            sb.append("\n");
        }
        return sb.toString();
    }


    private static String getSnakeName(String name){
        return StringUtils.camelToUnderline(name);
    }

    private static String  getPrefix(String prefix){
        if (StringUtils.isBlank(prefix)) {
            return "";
        }
        return prefix + ".";
    }
}
