package br.edu.infnet.courseservice.business;

import br.edu.infnet.courseservice.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CourseBusinessMockTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {

        // Given / Arrange
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);

        courses = Arrays.asList(
                "Administração de BDs noSQL com MongoDB",
                "Arquitetura de Redes de Computadores",
                "Desenvolvimento com Serviços WCF e Microsoft Azure",
                "Melhores Práticas em DevOps",
                "Segurança no Processo e Desenvolvimento de Software",
                "Projeto de Bloco: Desenvolvimento Android",
                "Projeto de Bloco: Desenvolvimento Java",
                "Projeto de Bloco: Engenharia de Dados: Big Data",
                "Projeto de Bloco: Engenharia de Softwares Escaláveis",
                "Projeto de Bloco: Engenharia Disciplinada de Softwares"
        );
    }

    @Test
    void testCoursesRelatedToDevOps_When_UsingAMock() {

        // Given / Arrange
        when(mockService.retrieveCourses("Carolina"))
                .thenReturn(courses);

        // When / Act
        var filteredCourses =
                business.retriveCoursesRelatedToDevOps("Carolina");

        // Then / Assert
        assertEquals(1, filteredCourses.size());
    }
}