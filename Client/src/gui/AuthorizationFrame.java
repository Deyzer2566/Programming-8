package gui;

import command.NotCorrectLoginOrPassword;
import net.RemoteDatabaseWithAuth;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthorizationFrame extends JFrameWithLocalization {
    ResourceBundle rb;
    JButton auth;
    JLabel errorMessage;
    JLabel login_head;
    JLabel password_head;
    public AuthorizationFrame(AuthorizationRegistrationMenu authorizationRegistrationMenu) {
        super();
        setLocationRelativeTo(null); // окно в центре экрана
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        login_head = new JLabel();
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        add(login_head,gridBagConstraints);

        JTextField login = new JTextField(5);
        gridBagConstraints.gridy=2;
        add(login,gridBagConstraints);

        password_head = new JLabel();
        gridBagConstraints.gridy=3;
        add(password_head,gridBagConstraints);

        JTextField password = new JPasswordField(5);
        gridBagConstraints.gridy=4;
        add(password,gridBagConstraints);

        auth = new JButton();
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        auth.addActionListener(e -> {
            try {
                authorizationRegistrationMenu.tryLogin(login.getText(),password.getText());
            } catch (NotCorrectLoginOrPassword ex) {
                errorMessage.setVisible(true);
                pack();
            }
        });
        gridBagConstraints.gridy=5;
        add(auth,gridBagConstraints);
        gridBagConstraints.gridy=6;
        add(errorMessage,gridBagConstraints);
        setJMenuBar(new JMenuBar());
    }

    @Override
    public void changeLocalization(Locale locale) {
        super.changeLocalization(locale);
        this.rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        auth.setText(rb.getString("auth"));
        errorMessage.setText(rb.getString("NotCorrectLoginOrPassword"));
        login_head.setText(rb.getString("login"));
        password_head.setText(rb.getString("password"));
        setTitle(rb.getString("auth"));
        pack();
    }
}
