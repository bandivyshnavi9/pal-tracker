package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    //InMemoryTimeEntryRepository inMemoryTimeEntryRepository = new InMemoryTimeEntryRepository();
    private TimeEntryRepository timeEntryRepository;
    private final CounterService counter;
    private final GaugeService gauge;


    public TimeEntryController(TimeEntryRepository timeEntryRepository, CounterService counter, GaugeService gauge) {
        this.timeEntryRepository=timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        ResponseEntity responseEntity = new ResponseEntity(createdTimeEntry,HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntryRepository.find(id)!=null) {
            counter.increment("TimeEntry.read");
            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(timeEntry, HttpStatus.OK);

            return responseEntity;
        }
        else{
            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
    }



    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry expected) {
        TimeEntry updateTimeEntry = timeEntryRepository.update(id, expected);

        if(updateTimeEntry !=null) {
            counter.increment("TimeEntry.updated");
            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(updateTimeEntry,HttpStatus.OK);

            return responseEntity;
        }else{
            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        ResponseEntity<TimeEntry> responseEntity = new ResponseEntity( HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @RequestMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        ResponseEntity<List<TimeEntry>> responseEntity = new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
        return responseEntity;
    }
}
