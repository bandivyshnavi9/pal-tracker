package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long,TimeEntry> dblist= new HashMap<Long, TimeEntry>();


    @Override
   public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(dblist.size() + 1);
        dblist.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry delete(long getId) {
        TimeEntry timeEntry = dblist.get(getId);
        dblist.remove(getId);
        return timeEntry;
    }

    @Override
    public TimeEntry update(long getId,TimeEntry obj) {
        return null;
    }

    @Override
    public TimeEntry find(long id) {
        return dblist.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>(dblist.values());

    return timeEntries;
    }
}
