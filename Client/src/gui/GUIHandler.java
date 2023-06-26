package gui;

import i18n.LocaleManager;
import net.RemoteDatabaseWithAuthAndInfoAboutGroupsOwners;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;

public class GUIHandler {
    protected RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db;
    Locale locale;
    protected AuthorizationRegistrationMenu authorizationRegistrationMenu;
    enum State{
        CREATED,START,AUTHORIZED;
    }
    protected State state;
    public GUIHandler(RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db){
        this.db=db;
        state = State.CREATED;
    }

    public void start(){
        authorizationRegistrationMenu = new AuthorizationRegistrationMenu(this,db);
        authorizationRegistrationMenu.setLocationRelativeTo(null);
        authorizationRegistrationMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authorizationRegistrationMenu.setJMenuBar(new JMenuBar());
        authorizationRegistrationMenu.setVisible(true);
        LocaleManager.registerNewLocalizable(authorizationRegistrationMenu);
        state = State.START;
    }

    public void main(){
        authorizationRegistrationMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        state = State.AUTHORIZED;
        LocaleManager.dropLocalizable(authorizationRegistrationMenu);
        authorizationRegistrationMenu.close();
        try {
            MainAppFrame mainAppFrame = new MainAppFrame(db);
            mainAppFrame.setLocationRelativeTo(null);
            mainAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainAppFrame.setVisible(true);
            LocaleManager.registerNewLocalizable(mainAppFrame);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
