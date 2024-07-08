package com.anertapi.springboot_rest_api.controller;

import com.anertapi.springboot_rest_api.controller.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    //http://localhost:8080/students for return student object as json object to the client
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Swapnil","More");

       // return new ResponseEntity<>(student,HttpStatus.OK);
       // return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","Swapnil").body(student); //we can get this key-value in postman response header
    }

    //http://localhost:8080/students for return list of students as json object to the client
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Swapnil","More"));
        students.add(new Student(2,"Gopal","Bhoi"));
        students.add(new Student(3,"Krishi","Sonawane"));
        students.add(new Student(4,"Jidnyanshu","Wadile"));
        return ResponseEntity.ok(students);
    }

    //Spring boot api with path variable
    //{id} :- URI Template Variable   it will handle path variables in a request url
    //it will bind the value of uri template variable into method argument
    //http://localhost:8080/students/1/swapnil/more
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                      @PathVariable("first-name") String firstName,
                                      @PathVariable("last-name") String lastName){
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot rest api with query param
    //http://localhost:8080/students/query?id=1&firstName=Swapnil&lastName=More
    // it will  extract or handle query parameter in the request url
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot rest api that handles http post request for creating new resource
    //@PostMapping & @RequestBody
    //http://localhost:8080/students/create
    @PostMapping("create")
   // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> studentCreate(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //Spring boot rest api that handles http put request for updating existing resource
   // http://localhost:8080/students/2/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> studentUpdate(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }
    //Spring boot rest api that handles http delete request for deleting existing resource
    //http://localhost:8080/students/2/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> studentDelete(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted Successfully");
    }
}
