package com.example.loginsystem;
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
    }

    public void GetTextFromSQL(View v){
        TextView tx1=(TextView) findViewById((R.id.textView6));
        TextView tx2=(TextView) findViewById((R.id.textView4));

        try{
            ConnectionHelper connectionHelper= new ConnectionHelper();
            connect= connectionHelper.connectionclass();
            if(connect!=null){
                String query="Select * from Useraccountinfo";
                Statement st=connect.createStatement();
                ResultSet rs=st.executeQuery(query);

                while(rs.next()){
                    tx1.setText(rs.getString( 1));
                    tx2.setText(rs.getString( 2));
                }
            }
            else{
                ConnectionResult="Check Connection";
            }
        }
        catch (Exception ex){

        }
    }

   // private void checkavailablility(String Uname, String Upassword, String Cpassword){

    //}
}