

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import java.time.LocalDate;
import java.util.Date;

public class TestData {
    public static Operation INSERT_COURSES =
            Operations.insertInto("course")
            .withDefaultValue("version", 1)
            .columns("code", "name", "credits", "department_id")
            .values("INSY312", "SOFTWARE ENGINEERING", 3, "DEP111" )
            .values("INSY345", "WEB TECHNOLOGY", 4, "DEP111")
            .values("INSY372", "ADVANCED SOFTWARE TESTING", 4, "DEP111")
            .build();

    public static Operation INSERT_STUDENTS =
            Operations.insertInto("student")
                    .withDefaultValue("version", 1)
                    .columns("studentid", "name", "gender", "dateofbirth", "phonenumber", "emailaddress", "department_id")
                    .values("21793", "Benja", "MALE", new Date(1997, 10, 5), "078423242", "benja@gmail.com", "DEP111")
                    .values("21793", "ndushi", "MALE", new Date(1998, 11, 15), "078553678", "ndushi@gmail.com", "DEP111")
                    .build();

    public static Operation INSERT_FACULTY =
            Operations.insertInto("faculty")
            .columns("id", "name")
            .values("IT101", "INFORMATION TECHNOLOGY")
            .values("FIN102", "FINANCE")
            .values("THEO103", "THEOLOGY")
            .build();

    public static Operation INSERT_DEPARTMENTS =
            Operations.insertInto("department")
            .columns("id", "name", "faculty_id")
            .values("DEP111", "SOFTWARE ENGINEERING", "IT101")
            .values("DEP222", "NETWORKING AND TELECOMMUNICATIONO", "IT101")
            .values("DEP333", "BUSINESS AND FINANCE", "FIN102")
            .build();

    public static Operation INSERT_REGISTRATIONS =
            Operations.insertInto("registration")
            .withDefaultValue("version", 1)
            .columns("id", "dateregistered", "ispaid", "course_code", "student_id" )
            .values("reg43", new Date(2021, 1, 6), false, "INSY372", "21793")
            .values("reg55", new Date(2021, 1, 6), false, "INSY312", "21796")
            .values("reg70", new Date(2021, 1, 6), false, "INSY345", "21890")
            .build();

    public static Operation DELETE_STUDENTS =
            Operations.deleteAllFrom("student");

    public static Operation DELETE_COURSES =
            Operations.deleteAllFrom("course");

    public static Operation DELETE_FACULTY =
            Operations.deleteAllFrom("faculty");

    public static Operation DELETE_DEPARTMENT =
            Operations.deleteAllFrom("department");

    public static Operation DELETE_REGISTRATION =
            Operations.deleteAllFrom("registration");

}
