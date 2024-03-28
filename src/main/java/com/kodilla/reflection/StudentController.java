package com.kodilla.reflection;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Map<Integer, String> createStudents(@Valid @RequestBody Numbers numbers) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Student[] students = new Student[numbers.getN()];

        for(int i = 0; i < numbers.getN(); i++) {
            Student student = new Student();
            student.initializeIndexNumber(numbers.getZ());
            students[i] = student;
        }

        Map<Integer, String> map = new HashMap<>();

        for (Student student : students) {
            Field indexNumberF = Student.class.getDeclaredField("indexNumber");
            indexNumberF.setAccessible(true);
            String indexNumber = (String) indexNumberF.get(student);

            Integer key = student.hashCode();
            map.put(key, indexNumber);
        }
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exc) {
        Map<String, String> resultMap = new HashMap<>();
        List<ObjectError> errorList = exc.getBindingResult().getAllErrors();
        errorList.forEach(errorObject -> {
            FieldError fieldError = (FieldError) errorObject;
            String name = fieldError.getField();
            String message = errorObject.getDefaultMessage();
            resultMap.put(name, message);
        });
        return resultMap;
    }
}
