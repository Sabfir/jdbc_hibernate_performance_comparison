package com.opinta;

import com.opinta.dao.StatusTrackingDaoHibernate;
import com.opinta.entity.StatusTracking;
import java.util.List;

import static com.opinta.entity.Constant.REPEAT_COUNT;
import static java.lang.String.format;

public class App_2 {

    public static void main(String[] args) {
        System.out.println("=== HIBERNATE START ===");

        long startTimeAll = System.currentTimeMillis();
        for (int i = 0; i < REPEAT_COUNT; i++) {
            long startTime = System.currentTimeMillis();

            StatusTrackingDaoHibernate statusTrackingDao = new StatusTrackingDaoHibernate();
            List<StatusTracking> statusTrackings = statusTrackingDao.getAll();

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println(format("%s (ms) - spent to get %s elements", elapsedTime, statusTrackings.size()));
            System.out.println("------------------------------");
        }
        long stopTimeAll = System.currentTimeMillis();
        long elapsedTimeAll = stopTimeAll - startTimeAll;
        System.out.println(format("=== HIBERNATE END %s queries in === in %s (ms)", REPEAT_COUNT, elapsedTimeAll));
    }
}
