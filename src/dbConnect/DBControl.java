package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import webParser.RawData;

public class DBControl {
    public void create(List<RawData> rawDatas) {
        Connection con = null;
        Statement stmt = null;
        String query = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", ConstValue.id, ConstValue.pw);
            stmt = con.createStatement();
            for (int i = 0; i < rawDatas.size(); i++) {
                RawData raw = rawDatas.get(i);
                query = String.format("insert into roadplus(date, roadNumber, road, fromTo, fromToNumber, roadName, roadNameOrder, distance, speed, color, status) "
                        + "values('%s', %d, '%s', '%s', %d, '%s', %d, %f, %f, '%s', '%s');"
                        , raw.getDate(), raw.getRoadNumber(), raw.getRoad(), raw.getFromTo(), raw.getFromToNumber(), raw.getRoadName(), raw.getRoadNameNumber(), raw.getDistance(), raw.getSpeed(),
                        raw.getColor(), raw.getStatus());
                stmt.execute(query);
            }
            
            
            stmt.close();
            con.close();
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println(err);
            e.printStackTrace();
        } finally {}
    }
}
