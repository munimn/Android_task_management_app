package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public final static String USER_ID = "com.example.myapplication.USER_ID";
    public final static String JOB_ID = "com.example.myapplication.JOB_ID";
    public final static String DESCRIPTION= "com.example.myapplication.DESCRIPTION";
    public final static String  DATE = "com.example.myapplication.DATE";
    public final static String LOCATION= "com.example.myapplication.LOCATION";
    public final static String WORKER= "com.example.myapplication.WORKER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void logIn(View view) {
        // Fetch the user name and password from the GUI
        EditText userText = (EditText) findViewById(R.id.user_name);
        String userName = userText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.password);
        String password = passwordText.getText().toString();

        // Create and launch the AsyncTask to check the user name and password.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new LogInTask(userName,password).execute();
        } else {
            userMessage(getResources().getString(R.string.message_no_network));
        }

    }

    private class LogInTask extends AsyncTask<Void, Void, String> {
        private String uri;

        LogInTask(String userName,String password) {
            uri="http://"+URIHandler.hostName+"/people?user="+userName+"&password="+password;
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doGet(uri,"0");
        }

        @Override
        protected void onPostExecute(String result) {
            if("0".equals(result)){
                userMessage(getResources().getString(R.string.message_login_failed));}
            else
            {goToMain(result);}
        }
    }

    private void userMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void goToMain(String id) {
        Intent intent = new Intent(this, ViewAllJobsActivity.class);
        intent.putExtra(USER_ID, id);
        startActivity(intent);
    }

}
