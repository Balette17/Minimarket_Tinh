package Main;
import Model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;


    public Login(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(400, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private void cancel() {
        int exit = JOptionPane.showConfirmDialog(this,"Do you want exit?","Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if(exit == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }


    private void login() {
        String name = txtUsername.getText();
        String pass = String.valueOf(txtPassword.getPassword());
        ItemApp i = null;
        User admin = new User("tinh", "tinh1234");
        User checkUser = new User(name, pass);

        boolean login = false;

        if (admin.equals(checkUser)) {
            i = new ItemApp(name, this);
            login = true;
        }
        if (login) {
            i.setVisible(true);
            this.setVisible(false);
        } else {
            showMess("Login Failed");
        }

    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this, mess);
    }
    public static void main(String[] args) {
        JFrame f = new Login("Login");
        f.setVisible(true);
    }
}
