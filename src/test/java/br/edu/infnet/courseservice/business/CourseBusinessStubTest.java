package br.edu.infnet.courseservice.business;


import br.edu.infnet.courseservice.service.CourseService;
import br.edu.infnet.courseservice.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CourseBusinessStubTest {

    @Test
    void testCoursesRelatedToDevOps_When_UsingAStub() {

        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        // When / Act
        var filteredCourses =
                business.retriveCoursesRelatedToDevOps("Carolina");

        // Then / Assert
        assertEquals(1, filteredCourses.size());
    }

    @Test
    void testCoursesRelatedToDevOps_When_UsingAFooBarStudent() {

        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        // When / Act
        var filteredCourses =
                business.retriveCoursesRelatedToDevOps("Foo Bar");

        // Then / Assert
        assertEquals(0, filteredCourses.size());
    }

}