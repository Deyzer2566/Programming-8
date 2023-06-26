package command;

import io.FileAccessException;
import io.FileReader;
import io.IOHandler;
import io.Writer;
import storage.Database;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Команда для исполнения скрипта
 */
public class ExecuteScriptCommand implements Command{

    private Writer writer;
    private static int recursiveLevel=0;
    private Database database;

    private final int maxRecursiveLevel = 5;

    public ExecuteScriptCommand(Writer writer, Database database) {
        this.writer = writer;
        this.database = database;
    }

    @Override
    public void execute(String[] args) throws InvalidCommandArgumentException {
        if(args == null){
            throw new InvalidCommandArgumentException("Не удалось прочитать название файла!");
        }
        if(args.length < 1){
            throw new InvalidCommandArgumentException("Введите название файла!");
        }
        if(recursiveLevel + 1 > maxRecursiveLevel){
            writer.writeError("Достигнут максимум рекурсии!");
            return;
        }
        recursiveLevel++;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            throw new InvalidCommandArgumentException("Файл не найден!");
        } catch (FileAccessException e){
            throw new InvalidCommandArgumentException("Невозможно прочитать данные из файла!");
        }
        CommandHandler commandHandler1 = new CommandHandler(database,fileReader);
        commandHandler1.register("execute_script", new ExecuteScriptCommand(fileReader,database));
        while(fileReader.hasNextLine()){
            String command = fileReader.readLine();
            if(command == null)
                break;
            if(command.equals("exit")){
                break;
            }
            if(command.equals("")){
                continue;
            }
            LinkedList<String> commandArgs = new LinkedList<>(Arrays.asList(command.split(" ")));
            command = commandArgs.get(0);
            commandArgs.remove(0);
            try{
                commandHandler1.execute(command,commandArgs.size()==0?null:commandArgs.toArray(new String[commandArgs.size()]));
            } catch(ThereIsNotCommand | InvalidCommandArgumentException e){
                writer.writeError(e.getMessage());
            }
        }
        fileReader.close();
        recursiveLevel--;
    }

    @Override
    public String description() {
        return "прочитать и исполнить скрипт из указанного файла.";
    }
}
