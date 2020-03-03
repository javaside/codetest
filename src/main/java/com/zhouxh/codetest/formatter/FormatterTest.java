package com.zhouxh.codetest.formatter;

import java.util.Formatter;

public class FormatterTest {

    private static Formatter formatter = new Formatter(System.out);

    /**
     * 控制台打印格式化后的字符串
     * @param format 格式化模板
     * @param args 格式化参数
     */
    private static void printf(String format, Object... args){
        formatter.format(format, args);
    }

    public static void main(String[] args) {
        printf("%1$-10.10s %2$10s%n","Name","Age");
        printf("%1$-10.10s %1$10s%n","------");
        printf("%1$-10.10s %2$10d%n","zs",25);
        printf("%1$-10.10s %2$10.3f%n","ls",99.0);
        printf("%1$-10.10s %2$10.3f%n","1234567890123456789",0.19);
        printf("%1$-10.10s %2$10.3a%n","16x",0.19);
        printf("%1$-10.10s %2$10g%n","16x",0.19);
        printf("%1$-10.10s %2$10f%n","16x",0.19);
        printf("%1$-10.10s %2$10.3e%n","16x",0.19);
    }
}
