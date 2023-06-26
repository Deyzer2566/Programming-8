package SQL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class SQLDatabaseWithInfoAboutGroupsOwners extends SQLDatabase{

    public SQLDatabaseWithInfoAboutGroupsOwners() throws SQLException {
    }

    public HashMap<Integer, LinkedList<Long>> getOwnersOfGroups(){
        return clientsGroups;
    }
}
