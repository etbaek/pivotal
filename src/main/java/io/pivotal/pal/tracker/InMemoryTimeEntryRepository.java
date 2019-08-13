package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> repo = new HashMap<>();
    private Long id = 0L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++id);
        repo.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry temp = null;
        temp = repo.get(id);
        if(temp != null)
        {
            temp = timeEntry;
            temp.setId(id);
            repo.replace(id, temp);
        }

        return temp;
    }

    @Override
    public void delete(Long id) {
        repo.remove(id);
    }
}
