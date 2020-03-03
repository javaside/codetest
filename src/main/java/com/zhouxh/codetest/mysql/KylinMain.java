package com.zhouxh.codetest.mysql;

import com.zhouxh.codetest.comm.ScreenUtils;

import java.sql.*;
import java.util.Properties;

public class KylinMain {
    private static String name = "mod";
    private static String pass = "RTBAsia@0912.sel";

    private static String url = "jdbc:kylin://kylin.rtbasia.com//DD_PRO";
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //stm();
        preparestm();

        ScreenUtils.pause();
        System.out.println("Goodbye!");
    }

    public static void stm() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();

        Properties info = new Properties();
        info.put("user", name);
        info.put("password", pass);
        try(Connection conn = driver.connect(url, info);
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from dd_hive_isp limit 10");) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }

        System.out.println("stm end.");
    }
    public static void preparestm() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
        Properties info = new Properties();
        info.put("user", name);
        info.put("password", pass);
        try(Connection conn = driver.connect(url, info);
        PreparedStatement state = conn.prepareStatement("select * from dd_hive_isp limit 11");
        //state.setInt(1, 10);
        ResultSet resultSet = state.executeQuery();
        ) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }

        System.out.println("preparestm end.");
    }
}
