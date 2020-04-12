package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private static final Pattern REPLACE_PLACEHOLDER_PATTERN = Pattern.compile("(#\\{.*?\\})");

    /**
     * 替换占位符
     */
    public static void replacePlaceholder() {
        String sql = "select * from #{tableName} where c=#{c}";
        Matcher matcher = REPLACE_PLACEHOLDER_PATTERN.matcher(sql);
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
        sql = matcher.replaceAll("your_table");
        System.out.println(sql);
    }

    public static void main(String[] args) {
        RegexUtils.replacePlaceholder();
    }
}
