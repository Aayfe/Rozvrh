package cz.uhk.timetable.model;

import java.util.ArrayList;
import java.util.List;

public class LocationTimetable {

    private String building;
    private String room;

    private List<Activity> activities = new ArrayList<>();

    public LocationTimetable() {

    }

    public LocationTimetable(String room, String building) {

        this.room = room;
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
