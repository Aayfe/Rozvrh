package cz.uhk.timetable.utils.impl;

import cz.uhk.timetable.model.Activity;
import cz.uhk.timetable.model.LocationTimetable;
import cz.uhk.timetable.utils.TimetableProvider;

import java.time.LocalTime;
import java.util.List;

public class MockTimetableProvider implements TimetableProvider {
    @Override
    public LocationTimetable read(String building, String room) {
        var tt = new LocationTimetable(building, room);
        var activities = List.of(
                new Activity("PRO1", "Programování", "Úterý", LocalTime.of(12,25), LocalTime.of(13,55),"Kozel"),
        new Activity("UPROM", "Úvod do programování", "Pondělí", LocalTime.of(10,25), LocalTime.of(11,55),"Novák"),
        new Activity("ZMAT", "Základy matematiky", "Středa", LocalTime.of(12,25), LocalTime.of(13,55),"Nový")

        );

        tt.setActivities(activities);
        return tt;
    }
}
