

import dao.DBUtil;
import dao.GenericDao;
import main.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class TestDao extends AbstractTestCase {
    GenericDao<Faculty> facultyDao = new GenericDao<>();
    GenericDao<Department> departmentDao = new GenericDao<>();
    GenericDao<Student> studentDao = new GenericDao<>();
    GenericDao<Course> courseDao = new GenericDao<>();
    GenericDao<Registration> registrationDao = new GenericDao<>();


    @BeforeTest
    public void init() {
        DBUtil.getSessionFactory();
        System.out.println("Tables created");
    }

    @BeforeMethod
    public void initializeTest() {
        execute(TestData.INSERT_FACULTY);
        execute(TestData.INSERT_DEPARTMENTS);
        execute(TestData.INSERT_STUDENTS);
        execute(TestData.INSERT_COURSES);
        execute(TestData.INSERT_REGISTRATIONS);
        System.out.println("test initialized");
    }

    @AfterMethod
    public void cleanTestData() {
        execute(TestData.DELETE_REGISTRATION);
        execute(TestData.DELETE_STUDENTS);
        execute(TestData.DELETE_COURSES);
        execute(TestData.DELETE_DEPARTMENT);
        execute(TestData.DELETE_FACULTY);
        System.out.println("test data cleaned");
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty(" FAC123", "EDUCATION");
        Faculty fac = facultyDao.create(faculty);
        Assert.assertEquals(fac.getId(), faculty.getId());
        System.out.println("faculty created");
    }

    @Test
    public void testUpdateFaculty() {
        Faculty fac = facultyDao.findOne(Faculty.class, "FIN102");
        fac.setName("FINA");
        Faculty rtrfac = facultyDao.update(fac);
        Assert.assertEquals(rtrfac.getName(), "FINA");
        System.out.println("faculty updated");
    }

    @Test
    public void testDeleteFaculty() {
        Faculty faculty = new Faculty(" FIN102", "FINANCE");
        Faculty fac = facultyDao.delete(faculty);
        Assert.assertEquals(fac.getName(), "FINANCE");
        System.out.println("faculty deleted");
    }

    @Test
    public void testCreateDepartment() {
        Faculty faculty = facultyDao.findOne(Department.class, "FIN102");
        Department department = departmentDao.create(new Department("342", "THEOL", faculty));
        Assert.assertEquals(department.getName(), "THEOL");
        System.out.println("department created");
    }

    @Test
    public void testUpdateDepartment() {
        Department department = departmentDao.findOne(Department.class, "DEP111");
        department.setName("SOFTWARE ENG");
        department = departmentDao.update(department);
        Assert.assertEquals(department.getName(), "SOFTWARE ENG");
        System.out.println("department updated");
    }

    @Test
    public void testDeleteDepartment() {
        Department department = departmentDao.findOne(Department.class, "DEP111");

        List<Course> courses = courseDao.findCourses("DEP111");
        List<Student> students = studentDao.findStudents("DEP111");

        courses.forEach((crs) -> {
            List<Registration> registrations = registrationDao.findbyCourse(Registration.class, crs.getCode());
            registrations.forEach((reg) -> {
                registrationDao.delete(reg);
            });
            courseDao.delete(crs);
        });

        students.forEach((stu) -> {
            List<Registration> registrations = registrationDao.findbyStudent(Registration.class, stu.getStudentId());
            registrations.forEach((reg) -> {
                registrationDao.delete(reg);
            });
            studentDao.delete(stu);
        });

        Department dep = departmentDao.delete(department);
        Assert.assertEquals(dep.getName(), "SOFTWARE ENGINEERING");
        System.out.println("faculty deleted");
    }

    @Test
    public void testCreateCourse() {
        Department department = departmentDao.findOne(Department.class, "DEP111");
        Course course = courseDao.create(new Course("MATH123", "applied mathematics",
                4, department));
        Assert.assertEquals(course.getVersion(), 1);
        System.out.println("course created");
    }

    @Test
    public void testUpdateCourse() {
        Course course = courseDao.findOne(Course.class, "INSY312");
        course.setName("REQUIREMENT ENGINEERING");
        Course rtrcourse = courseDao.update(course);
        Assert.assertEquals(rtrcourse.getVersion(), 2);
        System.out.println("course updated");
    }

    @Test
    public void testDeleteCourse() {
        Course course = courseDao.findOne(Course.class, "INSY312");
        List<Registration> registrations = registrationDao.findbyCourse(Registration.class, "INSY312");
        registrations.forEach((reg) -> {
            registrationDao.delete(reg);
        });
        Course rtrcourse = courseDao.delete(course);
        Assert.assertEquals(rtrcourse.getVersion(), 1);
        System.out.println("course deleted");
    }

    @Test
    public void testCreateStudent() {
        Department department = departmentDao.findOne(Student.class, "DEP111");
        Student student = studentDao.create(new Student("23532", "vloom", Gender.FEMALE,
                new Date(1998, 4, 4), "0746783",
                "vloom@gmail.com", department));
        Assert.assertEquals(student.getVersion(), 1);
        System.out.println("student created");
    }

    @Test
    public void testUpdateStudent() {
        Student student = studentDao.findOne(Student.class, "20925");
        student.setName("benja");
        Student rtrstudent = studentDao.update(student);
        Assert.assertEquals(rtrstudent.getVersion(), 2);
        System.out.println("student updated");
    }

    @Test
    public void testDeleteStudent() {
        Student student = studentDao.findOne(Student.class, "21208");
        System.out.println("student id: " + student.getName());
        List<Registration> registrations = registrationDao.findbyStudent(Registration.class, "21208");
        registrations.forEach((reg) -> {
            registrationDao.delete(reg);
        });
        Student rtrstudent = studentDao.delete(student);
        Assert.assertEquals(rtrstudent.getVersion(), 1);
        System.out.println("student deleted");
    }

    @Test
    public void testCreateRegistration() {
        Student student = studentDao.findOne(Student.class, "21208");
        Course course = courseDao.findOne(Course.class, "INSY312");
        Registration registration = registrationDao.create(new Registration("REG43", student, course, new Date(2021, 1, 6)));
        Assert.assertEquals(registration.getVersion(), 1);
        System.out.println("registered successfully");
    }

    @Test
    public void testUpdateRegistration() {
        Registration registration = registrationDao.findOne(Registration.class, "reg43");
        registration.setIsPaid(true);
        Registration rtrreg = registrationDao.update(registration);
        Assert.assertEquals(rtrreg.getVersion(), 2);
        System.out.println("registration updated");
    }

    @Test
    public void testDeleteRegistration() {
        Registration registration = registrationDao.findOne(Registration.class, "reg43");
        Registration rtrreg = registrationDao.delete(registration);
        Assert.assertEquals(rtrreg.getVersion(), 1);
        System.out.println("registration deleted");
    }

}
