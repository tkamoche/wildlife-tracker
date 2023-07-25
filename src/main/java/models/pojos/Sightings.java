package models.pojos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sightings {
    private String location;
    private String rangerName;
    private LocalDateTime sightingTime;
    private int id;

    public Sightings(int id, String location, String rangerName) {
        this.id  = id;
        this.location = location;
        this.rangerName = rangerName;
        this.sightingTime = java.time.LocalDateTime.now();

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public LocalDateTime getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(LocalDateTime sightingTime) {
        this.sightingTime = sightingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sightings sighting = (Sightings) o;
        return id == sighting.id && location.equals(sighting.location) && rangerName.equals(sighting.rangerName) && sightingTime.equals(sighting.sightingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, rangerName, sightingTime, id);
    }
}
