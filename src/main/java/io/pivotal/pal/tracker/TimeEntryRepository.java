package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry obj);
    TimeEntry delete(long getId);
    TimeEntry update(long getId,TimeEntry obj);
    TimeEntry find(long getId);
    List<TimeEntry> list();



}
