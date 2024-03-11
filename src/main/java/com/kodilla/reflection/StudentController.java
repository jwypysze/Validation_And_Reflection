package com.kodilla.reflection;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Map<Integer, String> createStudents(@RequestBody Numbers numbers) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

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
}
