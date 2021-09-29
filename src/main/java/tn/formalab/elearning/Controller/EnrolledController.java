package tn.formalab.elearning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearning.models.Enrolled;
import tn.formalab.elearning.models.Lesson;
import tn.formalab.elearning.models.User;
import tn.formalab.elearning.repositories.CourseRepository;
import tn.formalab.elearning.repositories.EnrolledRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("enrolled")


public class EnrolledController {

    private EnrolledRepository enrolledRepository;

    @Autowired
    public EnrolledController(EnrolledRepository enrolledRepository){
        this.enrolledRepository = enrolledRepository;
    }




    @PostMapping(path = "add")
    public ResponseEntity<Enrolled> addLesson(@RequestBody Enrolled enrolled){

        System.out.println(enrolled);

        Enrolled savedLesson = this.enrolledRepository.save(enrolled);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }


    @GetMapping(path = "all")
    public ResponseEntity<List<Enrolled>> getAllLesson(){
        List<Enrolled> enrolleds=this.enrolledRepository.findAll();

        return  ResponseEntity.status(HttpStatus.OK).body(enrolleds);

    }

    @GetMapping(path = "mycourse")
    public ResponseEntity<List<Enrolled>> getAllmycourse(@RequestBody User user){
        List<Enrolled> enrolleds=this.enrolledRepository.findByUser(user);

        return  ResponseEntity.status(HttpStatus.OK).body(enrolleds);

    }

    @PatchMapping(path = "courseenrolled")
    public ResponseEntity<List<Enrolled>> getAllmycourse(@RequestBody Enrolled enrolled){
        List<Enrolled> enrolleds=this.enrolledRepository.findByUserAndCourse(enrolled.user,enrolled.course);

        return  ResponseEntity.status(HttpStatus.OK).body(enrolleds);

    }







}
