package com.zhouxh.codetest.comm;

import java.io.IOException;

public class ScreenUtils {

    public static void pause(){
        System.out.print("请输入任何字符按回车键退出程序。");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
