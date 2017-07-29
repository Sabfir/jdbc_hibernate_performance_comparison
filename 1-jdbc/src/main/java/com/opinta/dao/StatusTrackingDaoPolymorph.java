package com.opinta.dao;

import com.opinta.entity.StatusTracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StatusTrackingDaoPolymorph {
    public List<StatusTracking> getAll(Connection conn) {
        List<StatusTracking> statusTrackings = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM status_tracking";
            ResultSet rs = stmt.executeQuery(sql);

            StatusTracking statusTracking;
            while(rs.next()){
                statusTracking = new StatusTracking();

                //Retrieve by column name
                statusTracking.setId(rs.getInt("id"));
                statusTracking.setBarcode(rs.getString("barcode"));
                statusTracking.setName(rs.getString("name"));
//                statusTracking.setStatusDate(rs.getDate("status_date").toLocalDate());
                statusTracking.setEvent(rs.getString("event"));
                statusTracking.setCountry(rs.getString("country"));
                statusTracking.setEventReason(rs.getString("event_reason"));
                statusTracking.setMailType(rs.getInt("mail_type"));
                statusTracking.setIndexOrder(rs.getInt("index_order"));

                statusTrackings.add(statusTracking);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e) {
            //Handle errors for JDBC
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) {
                    stmt.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statusTrackings;
    }
}
