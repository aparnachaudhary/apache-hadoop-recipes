package io.github.aparnachaudhary;

import java.sql.*;

public class PhoenixQueryServerConnector {

    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        ResultSet rset = null;


        Connection con = DriverManager.getConnection("jdbc:phoenix:thin:url=http://localhost:8765;serialization=PROTOBUF");
        stmt = con.createStatement();
        stmt.executeUpdate("create table employee (mykey integer not null primary key, firstname varchar)");
        stmt.executeUpdate("upsert into employee values (1,'Alpha')");
        stmt.executeUpdate("upsert into employee values (2,'Beta')");
        con.commit();

        PreparedStatement statement = con.prepareStatement("select * from employee");
        rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString("firstname"));
        }
        statement.close();
        con.close();
    }
}
