package com.example.loginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity2 extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView message;
    Connection connect;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name=(EditText)findViewById(R.id.account_name);
        password=(EditText)findViewById(R.id.password);
        message=(TextView) findViewById(R.id.message1);
    }

    public void Validate(View v){
        try{
            ConnectionHelper connectionHelper= new ConnectionHelper();
            connect= connectionHelper.connectionclass();
            if(connect!=null){
               // message.setText("Test1");
                String query="SELECT * FROM Useraccountinfo WHERE Uname='"+this.name.getText().toString()+"'";
              //  message.setText("Test5");
                Statement st=connect.createStatement();
              //  message.setText("Test6");
                ResultSet rs=st.executeQuery(query);
             //   message.setText("Test2");
                if(rs.next()) {
                    //  message.setText("Test3");
                    int avail = rs.getInt(4);
                    String actual_password = rs.getString(3);
                    // message.setText("Test");
                    if (avail == 0) {
                        message.setText("Access Denied.");
                    } else if (!password.getText().toString().equals(actual_password)) {
                        message.setText("Wrong Password.");
                    } else {
                        message.setText("Go.");
                    }
                }
                else{
                    message.setText("The account does not exist, create an account.");
                }
            }
        }
        catch (Exception ex){

        }
    }

    public void ToCreateAccount(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}