package com.example.loginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
    public class Validate extends AsyncTask<String, Void, String> {
        Context ctx;
        Connection connect;
        @Override
        protected String doInBackground(String... strings) {
            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connect = connectionHelper.connectionclass();
                if (connect != null) {
                    String query = "SELECT * FROM Useraccountinfo WHERE Uname='" + name.getText().toString() + "'";
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        int avail = rs.getInt(3);
                        String actual_password = rs.getString(2);
                        if (avail == 0) {
                            return "Access Denied.";
                        } else if (!password.getText().toString().equals(actual_password)) {
                            return "Wrong Pssword";
                        } else {
                            return "GO";
                        }
                    } else {
                       return "The account does not exist, create an account.";
                    }
                }
            } catch (Exception ex) {

            }
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            message.setText(s);
            }

        }
    public void onclickvalidate(View v){
        Validate validate=new Validate();
        validate.execute("");
    }

    public void ToCreateAccount(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}