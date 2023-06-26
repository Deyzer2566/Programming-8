package gui;

import command.ThereIsUserWithThisLogin;
import net.RemoteDatabaseWithAuth;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationFrame extends JFrameWithLocalization {
    JButton reg;
    JLabel errorMessage;

    JLabel login_head;
    JLabel password_head;
    JLabel repeatPassword;
    enum ErrorType{
        NONE,PASSWORDS_NOT_EQUAL,THERE_IS_USER_WITH_THIS_LOGIN;
        public String getErrorMessage(ResourceBundle rb){
            if(this.equals(PASSWORDS_NOT_EQUAL)){
                return rb.getString("PasswordsNotEqual");
            }
            else if(this.equals(THERE_IS_USER_WITH_THIS_LOGIN)){
                return rb.getString("ThereIsUserWithThisLogin");
            }
            return "";
        }
    }
    ErrorType errorType;
    public RegistrationFrame(AuthorizationRegistrationMenu authorizationRegistrationMenu) {
        super();
        this.errorType=ErrorType.NONE;
        setLocationRelativeTo(null); // окно в центре экрана
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        login_head = new JLabel();
        add(login_head,gridBagConstraints);

        JTextField login = new JTextField(5);
        gridBagConstraints.gridy=2;
        add(login,gridBagConstraints);

        gridBagConstraints.gridy=3;
        password_head = new JLabel();
        add(password_head,gridBagConstraints);

        JTextField password = new JPasswordField(5);
        gridBagConstraints.gridy=4;
        add(password,gridBagConstraints);

        repeatPassword = new JLabel();
        gridBagConstraints.gridy=5;
        add(repeatPassword,gridBagConstraints);

        JTextField confirmPassword = new JPasswordField(5);
        gridBagConstraints.gridy=6;
        add(confirmPassword,gridBagConstraints);

        reg = new JButton();
        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        reg.addActionListener(e -> {
            if(!password.getText().equals(confirmPassword.getText())) {
                errorMessage.setText(rb.getString("PasswordsNotEqual"));
                this.errorType = ErrorType.PASSWORDS_NOT_EQUAL;
                errorMessage.setVisible(true);
            }
            else
                try {
                    authorizationRegistrationMenu.tryRegister(login.getText(),password.getText());
                } catch (ThereIsUserWithThisLogin ex) {
                    errorMessage.setText(rb.getString("ThereIsUserWithThisLogin"));
                    errorType = ErrorType.THERE_IS_USER_WITH_THIS_LOGIN;
                    errorMessage.setVisible(true);
                }
        });
        gridBagConstraints.gridy=7;
        add(reg,gridBagConstraints);
        gridBagConstraints.gridy=8;
        add(errorMessage,gridBagConstraints);
        setSize(400,300);
        setJMenuBar(new JMenuBar());
    }

    @Override
    public void changeLocalization(Locale locale) {
        super.changeLocalization(locale);
        this.rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        reg.setText(rb.getString("reg"));
        errorMessage.setText(errorType.getErrorMessage(rb));
        repeatPassword.setText(rb.getString("RepeatPassword"));
        login_head.setText(rb.getString("login"));
        password_head.setText(rb.getString("password"));
        setTitle(rb.getString("reg"));
    }
}
