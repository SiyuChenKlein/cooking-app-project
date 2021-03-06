package com.example.loginsystem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText confirm_password;
    private Button confirm;
    private Button back;
    private TextView message;
    Connection connect;
    String ConnectionResult="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.Account_Name);
        password=(EditText)findViewById(R.id.Password);
        confirm_password=(EditText)findViewById(R.id.Confirm_Password);
        confirm=(Button)findViewById(R.id.Confirm_Button);
        back=(Button)findViewById(R.id.Back_Button);
        message=(TextView) findViewById(R.id.message);
    }

    public void SetAccount(View v){
        TextView tx2=(TextView) findViewById((R.id.textView4));

        try{
            ConnectionHelper connectionHelper= new ConnectionHelper();
            connect= connectionHelper.connectionclass();
            if(connect!=null){
                String query="INSERT INTO Useraccountinfo (Uname,Upassword,Uavailability) VALUES ('"+this.name.getText().toString()+"','"+this.password.getText().toString()+"',1)";
                Statement st=connect.createStatement();
                st.executeUpdate(query);
                st.close();
            }
            else{
                ConnectionResult="Check Connection";
            }
        }
        catch (Exception ex){

        }
    }

    public void Check(View v){

        try {
            if (!confirm_password.getText().toString().equals(password.getText().toString())) {
                message.setText("Password doesn't match, enter again.");
                password.getText().clear();
                confirm_password.getText().clear();
            }
            else {
                ConnectionHelper connectionHelper= new ConnectionHelper();
                connect= connectionHelper.connectionclass();
                String query="SELECT * FROM Useraccountinfo WHERE Uname='"+this.name.getText().toString()+"'";
                Statement st=connect.createStatement();
                ResultSet rs=st.executeQuery(query);
                if(rs.next()){

                    message.setText("Account exists, change your accountname.");
                    name.getText().clear();
                    password.getText().clear();
                    confirm_password.getText().clear();
                }
                else {
                    SetAccount(v);
                    message.setText("Create Account Success!");
                }
            }
        }
        catch(Exception ex){

        }
    }

    public void BackToLogin(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(i);
    }
}