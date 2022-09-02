package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class WorkerViewActivity extends AppCompatActivity {

    private int workerId;
    private int jobId;
    private PersonNew personnew;
    private int userID;

    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            workerId = Integer.parseInt(savedInstanceState.getString(LoginActivity.WORKER));
            jobId = Integer.parseInt(savedInstanceState.getString(LoginActivity.JOB_ID));
            userID = Integer.parseInt(savedInstanceState.getString(LoginActivity.USER_ID));
        } else {
            Intent intent = getIntent();
            workerId = Integer.parseInt(intent.getStringExtra(LoginActivity.WORKER));
            jobId = Integer.parseInt(intent.getStringExtra(LoginActivity.JOB_ID));
            userID = Integer.parseInt(intent.getStringExtra(LoginActivity.USER_ID));
        }
        setContentView(R.layout.activity_worker_view);
        gson = new Gson();
        new LogInTask().execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle arg0) {
        super.onSaveInstanceState(arg0);
        arg0.putInt(LoginActivity.WORKER, workerId);
        arg0.putString(LoginActivity.USER_ID, String.valueOf(userID));
    }



    private class NewAssignmentTask extends AsyncTask<Void, Void, String> {
        private String uri;
        private String toSend;

        NewAssignmentTask(Assignment a) {
            uri = "http://" + URIHandler.hostName + "/assignments";
            this.toSend = gson.toJson(a);
        }
        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doPost(uri, toSend);
        }
        @Override
        protected void onPostExecute(String messageId) {
            goback();
        }
    }


    private class LogInTask extends AsyncTask<Void, Void, String> {
        private String uri;

        LogInTask() {
            uri="http://"+URIHandler.hostName+"/peoplenew?id="+workerId;
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doGet(uri,"0");
        }

        @Override
        protected void onPostExecute(String result) {
            loadMessage(result);
        }
    }




    private void loadMessage(String json) {

        personnew = gson.fromJson(json,PersonNew.class);

        TextView name = (TextView) this.findViewById(R.id.name);
        name.setText(personnew.getName());
        TextView email = (TextView) this.findViewById(R.id.email);
        email.setText( personnew.getEmail());
        TextView phone = (TextView) this.findViewById(R.id.phone);
        phone.setText(personnew.getPhone());
        TextView ratings = (TextView) this.findViewById(R.id.ratings);
        ratings.setText(String.valueOf(personnew.getRating()));
    }

    public void closeMessage(View view) {
        finish();
    }

    public void doAssign(View view) {
        Assignment a = new Assignment();
        a.setWorker(workerId);
        a.setJob(jobId);
        new NewAssignmentTask(a).execute();
    }
    public void goback(){
        Intent intent = new Intent(this, ViewAllJobsActivity.class);
        intent.putExtra(LoginActivity.USER_ID, String.valueOf(userID));
        startActivity(intent);
    }

}
