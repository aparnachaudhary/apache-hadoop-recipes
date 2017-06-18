package io.github.aparnachaudhary;

import java.sql.*;

public class SimplePhoenixConnector {

    public static void main(String[] args) throws SQLException {
        Statement stmt = null;
        ResultSet rset = null;

        Connection con = DriverManager.getConnection("jdbc:phoenix:localhost:2181");
        stmt = con.createStatement();
        stmt.executeUpdate("create table greeting (mykey integer not null primary key, mycolumn varchar)");
        stmt.executeUpdate("upsert into greeting values (1,'Hello')");
        stmt.executeUpdate("upsert into greeting values (2,'World!')");
        con.commit();

        PreparedStatement statement = con.prepareStatement("select * from greeting");
        rset = statement.executeQuery();
        while (rset.next()) {
            System.out.println(rset.getString("mycolumn"));
        }
        statement.close();
        con.close();
    }
}
