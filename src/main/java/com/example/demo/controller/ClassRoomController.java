package com.example.demo.controller;

import com.example.demo.model.ClassRoom;
import com.example.demo.model.MyDTOResponse;
import com.example.demo.model.Student;
import com.example.demo.service.ClassRoomService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/classroom")
public class ClassRoomController {
    @Autowired
    ClassRoomService classRoomService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity get() {
        try {
            List<ClassRoom> result = classRoomService.getAllClassRooms();
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \" There are no class room at all",
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        try {
            ClassRoom result = classRoomService.getClassRoomById(id);
            if (result != null) {
                return new ResponseEntity<ClassRoom>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \"not found class room with Id " + id + "\" }",
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/EXTERNAL")
    public ResponseEntity getByExternalClass(){
        try {
            List<Student> result = classRoomService.getByExternalClass();
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \" There are no students in external class",
                    HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getMyDTO/{id}")
    public ResponseEntity getMyDTO(@PathVariable Integer id){
        try {
            MyDTOResponse myDTO = classRoomService.getMyDTO(id);
            if (myDTO != null) {
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                return new ResponseEntity<>(myDTO, HttpStatus.OK);

            }
            return new ResponseEntity<String>("{ \"Warning\": \" ",
                    HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
                return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }




    @PostMapping()
    public ResponseEntity<String> post(@RequestBody ClassRoom classRoom) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(classRoom);
        ClassRoom classRoom1 = objectMapper.readValue(json, ClassRoom.class);
        String result = classRoomService.createClassRoom(classRoom1);
        if (result == null) {
            return new ResponseEntity<String>("{ \"result\": \"created\" }", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity put(@RequestBody ClassRoom classRoom, @PathVariable Integer id) {
        try {
            ClassRoom result = classRoomService.getClassRoomById(id);
            classRoomService.updateClassRoom(classRoom, id);
            if (result != null) {
                return new ResponseEntity<ClassRoom>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"result\": \"created\" }", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        try {
            ClassRoom result = classRoomService.getClassRoomById(id);
            if (result != null) {
                classRoomService.deleteClassRoom(id);
                return new ResponseEntity<ClassRoom>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \"not found class room with Id " + id + "\" }",
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
