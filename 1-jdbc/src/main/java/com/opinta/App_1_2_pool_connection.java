package com.opinta;

import com.opinta.dao.StatusTrackingDaoPolymorph;
import com.opinta.dao.datasource.BasicDataSourceDao;
import com.opinta.entity.StatusTracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.opinta.entity.Constant.DB_URL;
import static com.opinta.entity.Constant.PASSWORD;
import static com.opinta.entity.Constant.REPEAT_COUNT;
import static com.opinta.entity.Constant.USER;
import static java.lang.String.format;

public class App_1_2_pool_connection {

    public static void main(String[] args) {
        System.out.println("=== JDBC START ===");

        long startTimeAll = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_COUNT; i++) {
            long startTime = System.currentTimeMillis();

            StatusTrackingDaoPolymorph statusTrackingDao = new StatusTrackingDaoPolymorph();
            List<StatusTracking> statusTrackings = statusTrackingDao.getAll(BasicDataSourceDao.getConnection());

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(format("%s (ms) - spent to get %s elements", elapsedTime, statusTrackings.size()));
            System.out.println("------------------------------");
        }
        long stopTimeAll = System.currentTimeMillis();
        long elapsedTimeAll = stopTimeAll - startTimeAll;
        System.out.println(format("=== JDBC END %s queries in === in %s (ms)", REPEAT_COUNT, elapsedTimeAll));
    }
}
