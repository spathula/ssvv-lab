package ssvv.example.service;

import org.junit.After;
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

    @After
    public void teardown() {
        service.deleteStudent("1");
    }

    @Test
    public void test_addStudent_success() {
        int result = service.saveStudent("1", "student1", 123);
        assertEquals(1, result);
    }

    @Test
    public void test_addStudent_fail_invalidGroup() {
        int result = service.saveStudent("1", "student1", 1);
        assertEquals(0, result);
    }

    @Test
    public void test_addStudent_fail_emptyId() {
        int result = service.saveStudent("", "student2", 123);
        assertEquals(0, result);
    }

    @Test
    public void test_addStudent_fail_emptyName() {
        int result = service.saveStudent("1", "", 123);
        assertEquals(0, result);
    }

    @Test
    public void test_addStudent_fail_nullId() {
        int result = service.saveStudent(null, "student2", 123);
        assertEquals(0, result);
    }

    @Test
    public void test_addStudent_fail_nullName() {
        int result = service.saveStudent("1", null, 123);
        assertEquals(0, result);
    }

}
