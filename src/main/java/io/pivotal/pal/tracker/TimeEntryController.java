package io.pivotal.pal.tracker;

import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    InMemoryTimeEntryRepository inMemoryTimeEntryRepository = new InMemoryTimeEntryRepository();

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

    }

    @PostMapping("/time-entries")
    public ResponseEntity create(TimeEntry timeEntry) {


        ResponseEntity responseEntity = new ResponseEntity(inMemoryTimeEntryRepository.create(timeEntry),HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<TimeEntry> read(long l) {

        ResponseEntity responseEntity = new ResponseEntity(inMemoryTimeEntryRepository.find(l),HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/time-entries")
    public ResponseEntity update(long l, TimeEntry expected) {
        return null;
    }

    @DeleteMapping("/time-entries")
    public ResponseEntity<TimeEntry> delete(long l) {
        return null;
    }

    @RequestMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return null;
    }
}
