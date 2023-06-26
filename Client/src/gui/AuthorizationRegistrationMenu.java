package gui;

import command.NotCorrectLoginOrPassword;
import command.ThereIsUserWithThisLogin;
import i18n.LocaleManager;
import net.RemoteDatabaseWithAuth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthorizationRegistrationMenu extends JFrameWithLocalization{
    private RemoteDatabaseWithAuth db;
    JButton auth;
    JButton reg;
    GUIHandler parentGUIHandler;
    AuthorizationFrame authorizationFrame;
    RegistrationFrame registrationFrame;
    public AuthorizationRegistrationMenu(GUIHandler parentGUIHandler, RemoteDatabaseWithAuth db){
        super();
        this.db=db;
        this.parentGUIHandler=parentGUIHandler;
        this.authorizationFrame = new AuthorizationFrame(this);
        this.registrationFrame = new RegistrationFrame(this);
        LocaleManager.registerNewLocalizable(authorizationFrame);
        LocaleManager.registerNewLocalizable(registrationFrame);
        //setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        auth = new JButton();
        auth.addActionListener(e -> {
            authorizationFrame.setVisible(true);
            registrationFrame.setVisible(false);
        });
        add(auth,gridBagConstraints);
        reg = new JButton();
        reg.addActionListener(e -> {
            registrationFrame.setVisible(true);
            authorizationFrame.setVisible(false);
        });
        gridBagConstraints.gridy=2;
        add(reg,gridBagConstraints);
    }

    public void tryLogin(String login,String password) throws NotCorrectLoginOrPassword {
        db.login(login,password);
        parentGUIHandler.main();
    }

    public void tryRegister(String login,String password) throws ThereIsUserWithThisLogin {
        db.register(login,password);
        parentGUIHandler.main();
    }

    @Override
    public void changeLocalization(Locale locale) {
        super.changeLocalization(locale);
        rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        auth.setText(rb.getString("auth"));
        reg.setText(rb.getString("reg"));
        pack();
    }

    public void close() {
        LocaleManager.dropLocalizable(authorizationFrame);
        LocaleManager.dropLocalizable(registrationFrame);
        authorizationFrame.dispatchEvent(new WindowEvent(authorizationFrame,WindowEvent.WINDOW_CLOSING));
        registrationFrame.dispatchEvent(new WindowEvent(registrationFrame,WindowEvent.WINDOW_CLOSING));
        dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }
}
