

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;


public class AbstractTestCase {
    public void execute(Operation operation)
    {
        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
                "jdbc:postgresql://localhost:5432/bank_test_db",
                "postgres",
                "Benja"
        ), operation);

        dbSetup.launch();
    }
}
