package signup;


import com.google.gson.Gson;
import interfaces.Messages;
import interfaces.Player;
import interfaces.XOInterface;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import  tictactoegui.*;
import static tictactoegui.TicTacToeGui.SI;

public class signUpController implements Initializable {
    PrintStream controllerPS;

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField uname;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField conpass;
    @FXML
    private Text checkfname;
    @FXML
    private Text checklname;
    @FXML
    private Text checkuname;
    @FXML
    private Text checkpass;
    @FXML
    private Text confirmpass;
    @FXML
    private Label errorMsg;
 
    public boolean check()
    {
        boolean flag = true;
        checkuname.setVisible(false);
        checkpass.setVisible(false);
        checklname.setVisible(false);
        checkfname.setVisible(false);
        confirmpass.setVisible(false);
        if(uname.getText().equals(""))
        {
            checkuname.setVisible(true);
            flag = false;
        }
        if(password.getText().equals(""))
        {
            checkpass.setVisible(true); 
            flag = false;
        }
        if(lname.getText().equals(""))
        {
            checklname.setVisible(true);
            flag = false;
        }
        if(fname.getText().equals(""))
        {
            checkfname.setVisible(true); 
            flag = false;
        }
        if(((conpass.getText()).equals(password.getText())) == false)
        {
            confirmpass.setVisible(true); 
            flag = false;
        }
        return flag;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerPS = TicTacToeGui.ps;
        errorMsg.setVisible(false);
    }    

    @FXML
    private void register(ActionEvent event) throws IOException {
        if (check())
        {
            Player player=new Player(uname.getText(),password.getText(),fname.getText(),lname.getText());
            XOInterface xointerface =new XOInterface (Messages.REGISTER,player);
            Gson g = new Gson();
            String s = g.toJson(xointerface);
            controllerPS.println(s);            
        }
    }  

    @FXML
    private void back(ActionEvent event) {
        try
        {
            Stage signinstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader signinpage = new FXMLLoader();
            signinpage.setLocation(getClass().getResource("/signin/signIn.fxml"));
            Parent  signinpageroot = signinpage.load();
            TicTacToeGui.SI = signinpage.getController();
            Scene scenesignin = new Scene( signinpageroot);
            signinstage.hide();
            signinstage.setScene(scenesignin);
            signinstage.show();                     
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }    
    }
    public void dispErrMsg(){
        errorMsg.setVisible(true);
    }

}