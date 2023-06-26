package command;

import Client.Client;
import SQL.SQLDatabaseWithInfoAboutGroupsOwners;

public class GetOwnersCommand implements Command{

    SQLDatabaseWithInfoAboutGroupsOwners db;
    Client client;

    public GetOwnersCommand(SQLDatabaseWithInfoAboutGroupsOwners db, Client client) {
        this.db = db;
        this.client = client;
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        client.writeObject(db.getOwnersOfGroups());
    }

    @Override
    public String description() {
        return "Возвращает номера владельцев групп";
    }
}
