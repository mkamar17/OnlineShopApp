package com.treehouse.onlineshopapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends Activity {

    // declaring layout button, edit texts
    public Button run;
    public TextView message;
    public ProgressBar progressBar;
    // declaring connection variables
    public Connection con;
    //end declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting values from button, texts and progress bar
        run = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //setting up the function when button login is clicked
        run.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
        //end setting up function when button login clicked
    }

    public class CheckLogin extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;
        String name1 = "";

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String r){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,r, Toast.LENGTH_LONG).show();
            if(isSuccess){
                message = (TextView) findViewById(R.id.textView2);
                message.setText(name1);
            }
        }

        @Override
        protected String doInBackground(String... params){
            try{
                con = connectionclass(); //connect to database
                if (con == null){
                    z = "check internet access!";
                }
                else{
                    //change below query according to your own database
                    String query = "select * from UserAccounts";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        name1 = rs.getString("UserName"); //name is the string of a column in the database, read through
                        z = "query successful";
                        isSuccess=true;
                        con.close();
                    }
                    else{
                        z = "invalid query";
                        isSuccess=false;
                    }
                }
            }
            catch (Exception ex){
                isSuccess = false;
                z = ex.getMessage();

                Log.d("sql error", z);
            }

            return z;
        }

    }

    @SuppressLint("NewApi")
    public Connection connectionclass() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //your database connection string goes below
            ConnectionURL="";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch(SQLException se){
            Log.e("error here 1: ", se.getMessage());
        }
        catch(ClassNotFoundException e){
            Log.e("error here 2: ", e.getMessage());
        }
        catch(Exception e){
            Log.e("error here 3: ",e.getMessage());
        }
        return connection;
    }

}