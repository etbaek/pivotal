package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId){
        TimeEntry entry = timeEntryRepository.find(timeEntryId);
        ResponseEntity entity = null;

        if(entry != null)
        {
            entity = new ResponseEntity( entry, HttpStatus.OK);
        }
        else
        {
            entity = new ResponseEntity( entry, HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry entry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>( entry, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable(name="id") long timeEntryId, @RequestBody TimeEntry timeEntry){
        TimeEntry entry = timeEntryRepository.update(timeEntryId, timeEntry);
        ResponseEntity entity = null;

        if(entry == null)
        {
            return  new ResponseEntity(entry,HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity(entry,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(name="id") long timeEntryId){
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
