package ssvv.example.service;

import org.junit.*;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Pair;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.Validator;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private static Service service;

    @BeforeClass
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti-test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme-test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note-test.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.saveStudent("3", "student1", 123, "mail@domain.com");
        service.saveTema("3", "tema", 3, 2);
    }

    @AfterClass
    public static void teardown() {
        service.deleteStudent("1");
        service.deleteTema("1");
        service.deleteStudent("2");
        service.deleteTema("2");
        service.deleteNota(new Pair("2", "2"));
        service.deleteStudent("3");
        service.deleteTema("3");
        service.deleteNota(new Pair("3", "3"));
    }

    @Test
    public void test_addStudent() {
        int result = service.saveStudent("1", "student1", 123, "mail@domain.com");
        assertEquals(1, result);
    }

    @Test
    public void test_addAssignment() {
        int result = service.saveTema("1", "tema", 3, 2);
        assertEquals(1, result);
    }

    @Test
    public void test_addGrade() {
        int result = service.saveNota("3", "3", 10, 3, "very good very nice");
        assertEquals(1, result);
    }

    @Test
    public void test_addStudent_Assignment_Grade() {
        service.saveStudent("2", "student2", 123, "mail@domain.com");
        service.saveTema("2", "tema2", 5, 3);
        int result = service.saveNota("2", "2", 8, 5, "very good very nice");
        assertEquals(1, result);
    }
}
