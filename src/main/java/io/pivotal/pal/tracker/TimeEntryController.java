package io.pivotal.pal.tracker;

import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    //InMemoryTimeEntryRepository inMemoryTimeEntryRepository = new InMemoryTimeEntryRepository();
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;

    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {


        ResponseEntity responseEntity = new ResponseEntity(timeEntryRepository.create(timeEntry),HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        if(timeEntryRepository.find(id)!=null) {

            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(timeEntryRepository.find(id), HttpStatus.OK);

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

            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(updateTimeEntry,HttpStatus.OK);

            return responseEntity;
        }else{
            ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        ResponseEntity<TimeEntry> responseEntity = new ResponseEntity(timeEntryRepository.delete(id), HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @RequestMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> responseEntity = new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
        return responseEntity;
    }
}
