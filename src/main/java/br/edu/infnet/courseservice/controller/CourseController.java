package br.edu.infnet.courseservice.controller;


import java.util.HashMap;

import br.edu.infnet.courseservice.model.Course;
import br.edu.infnet.courseservice.proxy.CambioProxy;
import br.edu.infnet.courseservice.repository.CourseRepository;
import br.edu.infnet.courseservice.response.Cambio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Course endpoint")
@RestController
@RequestMapping("course-service")
public class CourseController {

    @Autowired
    private Environment environment;

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CambioProxy proxy;

    @Operation(summary = "Find a specific course by your ID")
    @GetMapping(value = "/{id}/{currency}")
    public Course findCourse(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {

        var course = repository.getById(id);
        if (course == null) throw new RuntimeException("Course not Found");

        var cambio = proxy.getCambio(course.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        course.setEnvironment("Course port: " + port + "Cambio Port" + cambio.getEnvironment());
        course.setPrice(cambio.getConvertedValue());
        return course;
    }

    @GetMapping(value = "/v1/{id}/{currency}")
    public Course findCourseV1(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {

        var course = repository.getById(id);
        if (course == null) throw new RuntimeException("Course not Found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", course.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity("http://localhost:8000/cambio-service/"
                                + "{amount}/{from}/{to}",
                        Cambio.class,
                        params);

        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");
        course.setEnvironment(port);
        course.setPrice(cambio.getConvertedValue());
        return course;
    }
}