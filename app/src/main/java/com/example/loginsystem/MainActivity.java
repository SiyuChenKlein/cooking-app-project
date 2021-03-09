package com.example.loginsystem;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private String TConfirm_password;
    private String Tpassword;
    Connection connect;
    String  ConnectionResult="";



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
    public class OnclickCheck extends AsyncTask<String, Void, String> {
        Context ctx;
        Connection connect;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            password=(EditText)findViewById(R.id.Password);
            confirm_password=(EditText)findViewById(R.id.Confirm_Password);
            TConfirm_password=confirm_password.getText().toString();
            Tpassword= password.getText().toString();

        }
        @Override
        protected String doInBackground(String... strings) {
            if (!TConfirm_password.equals(Tpassword)) {

                return "Password doesn't match, enter again.";
            } else {
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    String query = "SELECT * FROM Useraccountinfo WHERE Uname='" + MainActivity.this.name.getText().toString() + "'";
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        return "Account exists, change your accountname.";
                    } else {
                        try {
                            connectionHelper = new ConnectionHelper();
                            connect = connectionHelper.connectionclass();
                            if (connect != null) {
                                query = "INSERT INTO Useraccountinfo (Uname,Upassword,Uavailability) VALUES ('" + name.getText().toString() + "','" + password.getText().toString() + "',1)";
                                st = connect.createStatement();
                                st.executeUpdate(query);
                                return "Create Account Success!";
                            }
                        } catch (Exception ex) {

                        }

                    }
                } catch (Exception ex) {

                }
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            message.setText(s);
            if(s== "Password doesn't match, enter again."){
                password.getText().clear();
                confirm_password.getText().clear();
            }
            else if(s=="Account exists, change your accountname."){
                name.getText().clear();
                password.getText().clear();
                confirm_password.getText().clear();
            }

        }

    }
    public void onclick_check(View v){
        OnclickCheck onclickCheck=new OnclickCheck();
        onclickCheck.execute("");
    }







    public void BackToLogin(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(i);
    }
}