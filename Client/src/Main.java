import gui.GUIHandler;
import i18n.LocaleManager;
import net.RemoteDatabaseWithAuth;
import net.RemoteDatabaseWithAuthAndInfoAboutGroupsOwners;

import java.io.IOException;

public class Main {
    public static void main(String [] args) {
        new LocaleManager();
        RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db=null;
        try {
            db = new RemoteDatabaseWithAuthAndInfoAboutGroupsOwners("127.0.0.1",33737);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GUIHandler gui = new GUIHandler(db);
        gui.start();
//        RemoteDatabaseWithAuth db = null;
//        try {
//            db = new RemoteDatabaseWithAuth("127.0.0.1", 33737);
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//            return;
//        }
//        ConsoleIO console = new ConsoleIO();
//        CommandHandler ch = new CommandHandler(db, console);
//        ch.register("show",new ShowCommand(db, console));
//        ch.register("login", new LoginCommand(db));
//        ch.register("register",new RegisterCommand(db,console));
//        ch.register("execute_script", new ExecuteScriptCommand(console,ch));
//        while(db.isConnected()) {
//            console.write(">");
//            String command = null;
//            try{
//                command = console.readLine();
//            } catch (NoSuchElementException e){
//                break;
//            }
//            if(command == null)
//                break;
//            if(command.equals("exit")){
//                break;
//            }
//            if(command.equals("")){
//                console.write(">");
//                continue;
//            }
//            LinkedList<String> commandArgs = new LinkedList<>(Arrays.asList(command.split(" ")));
//            command = commandArgs.get(0);
//            commandArgs.remove(0);
//            try{
//                ch.execute(command,commandArgs.size()==0?null:commandArgs.toArray(new String[commandArgs.size()]));
//            } catch(ThereIsNotCommand | InvalidCommandArgumentException e){
//                console.writeError(e.getMessage());
//            }
//        }
//        db.disconnect();
    }
}
