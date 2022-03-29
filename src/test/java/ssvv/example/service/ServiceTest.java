package ssvv.example.service;

import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Nota;
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

public class ServiceTest {
    private Service service;

    @Before
    public void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti-test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme-test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note-test.xml");

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudentSuccess() {
        service.deleteStudent("1");
        int result = service.saveStudent("1", "student1", 123);
        assertEquals(0, result);
    }

    @Test
    public void testAddStudentFail() {
        service.deleteStudent("2");
        int result = service.saveStudent("2", "student2", 1);
        assertEquals(1, result);
    }

    @Test
    public void testAddStudentFail_() {
        service.deleteStudent("2");
        int result = service.saveStudent("2", "student2", 1);
        assertEquals(1, result);
    }

}
