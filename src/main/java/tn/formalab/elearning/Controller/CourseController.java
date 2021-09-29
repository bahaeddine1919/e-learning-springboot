package tn.formalab.elearning.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Lesson;
import tn.formalab.elearning.models.User;
import tn.formalab.elearning.repositories.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("courses")

public class CourseController {
    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    @PostMapping(path = "add")
    public ResponseEntity<Course> addProduct(@RequestBody Course course){

        System.out.println(course.name);

        Course savedCourse = this.courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }


    @GetMapping(path = "all")
    public ResponseEntity<List<Course>> getAllProduct(){
        List<Course> courses=this.courseRepository.findAll();

        return  ResponseEntity.status(HttpStatus.OK).body(courses);

    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Course> getProductById(@PathVariable Integer id){
        try {
            Course course =courseRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(course);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Course());
        }


    }



    @PatchMapping(path = "update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){

        Course updatedCourse =this.courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCourse);

    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String,String>> deleteById(@PathVariable Integer id) {
        this.courseRepository.deleteById(id);
        HashMap<String,String> obj =new HashMap<>();
        obj.put("message","course deleted Succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(obj) ;
    }

    @GetMapping(path = "mycourse")
    public ResponseEntity<List<Course>> getMyCourse(@RequestBody String nom ){
        List<Course> course = this.courseRepository.findByNomtuteur(nom);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }



    @GetMapping(path = "lesson")
    public ResponseEntity<List<Lesson>> getLessonById(@PathVariable Integer id){
        try {
            Course course =courseRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(course.lessons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Course().lessons);
        }


    }




}
