package tn.formalab.elearning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Lesson;
import tn.formalab.elearning.repositories.LessonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("lessons")
public class LessonController {
    private LessonRepository lessonRepository;

    @Autowired
    public LessonController(LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson){

        System.out.println(lesson.name);

        Lesson savedLesson = this.lessonRepository.save(lesson);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }


    @GetMapping(path = "all")
    public ResponseEntity<List<Lesson>> getAllLesson(){
        List<Lesson> lessons=this.lessonRepository.findAll();

        return  ResponseEntity.status(HttpStatus.OK).body(lessons);

    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer id){
        try {
            Lesson lesson =lessonRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(lesson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Lesson());
        }


    }



    @PatchMapping(path = "update")
    public ResponseEntity<Lesson> updateLesson(@RequestBody Lesson lesson){

        Lesson updatedLesson =this.lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedLesson);

    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String,String>> deleteById(@PathVariable Integer id) {
        this.lessonRepository.deleteById(id);
        HashMap<String,String> obj =new HashMap<>();
        obj.put("message","lesson deleted Succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(obj) ;
    }


}
