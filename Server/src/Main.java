import Client.*;
import SQL.SQLDatabase;
import SQL.SQLDatabaseWithInfoAboutGroupsOwners;
import SQL.SQLUserDatabase;
import command.*;
import io.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Main {
    static org.slf4j.Logger log;

    public static void main(String [] args) throws IOException {
        SQLDatabaseWithInfoAboutGroupsOwners db;
        try {
            db = new SQLDatabaseWithInfoAboutGroupsOwners();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(33737));
        ServerSocket serverSocket = serverSocketChannel.socket();
        ConsoleIO console = new ConsoleIO();
        SQLUserDatabase userDatabase = db.getUserDatabaseById(0);
        CommandHandler ch = new CommandHandler(userDatabase,console);
        ch.register("show",new ShowCommandForAdmin(userDatabase, console));
        ch.register("execute_script", new ExecuteScriptCommand(console,db));
        ClientHandler clients = new ClientHandler(db);
        console.write(">");
        log = org.slf4j.LoggerFactory.getLogger("main");
        log.info("Начало работы");
        while(db.isConnected()){
            if(System.in.available()>0)
            {
                String command = null;
                try {
                    command = console.readLine();
                } catch (NoSuchElementException e) {
                    break;
                }
                if (command == null)
                    break;
                if (command.equals("exit")) {
                    break;
                }
                if (command.equals("")) {
                    break;
                }
                LinkedList<String> commandArgs = new LinkedList<>(Arrays.asList(command.split(" ")));
                command = commandArgs.get(0);
                commandArgs.remove(0);
                try {
                    ch.execute(command, commandArgs.size() == 0 ? null : commandArgs.toArray(new String[commandArgs.size()]));
                } catch (ThereIsNotCommand | InvalidCommandArgumentException e) {
                    console.writeError(e.getMessage());
                }
                console.write(">");
            }
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                SocketChannel errChannel = null;
                while(errChannel == null)
                    errChannel = serverSocketChannel.accept();
                Client client = new ClientWithMultiThreading(socketChannel.socket(),errChannel.socket());
                clients.addClient(client);
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        clients.close();
        serverSocket.close();
        log.info("Конец работы");
    }
}
