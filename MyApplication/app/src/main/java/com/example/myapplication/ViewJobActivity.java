package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ViewJobActivity extends AppCompatActivity {

    private int workerId;
    private int jobId;
    private String descriptions;
    private String locations;
    private String dates;

    private Gson gson;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

            jobId = savedInstanceState.getInt(LoginActivity.JOB_ID);
            descriptions = savedInstanceState.getString(LoginActivity.DESCRIPTION);
            locations = savedInstanceState.getString(LoginActivity.LOCATION);
            dates = savedInstanceState.getString(LoginActivity.DATE);
        } else {
            Intent intent = getIntent();

            jobId = intent.getIntExtra(LoginActivity.JOB_ID,0);
            descriptions = intent.getStringExtra(LoginActivity.DESCRIPTION);
            locations = intent.getStringExtra(LoginActivity.LOCATION);
            dates = intent.getStringExtra(LoginActivity.DATE);

        }
        setContentView(R.layout.activity_view_job);
        gson = new Gson();
        new findworker().execute();
        loadMessage();

    }

    @Override
    protected void onSaveInstanceState(Bundle arg0) {
        super.onSaveInstanceState(arg0);
        arg0.putInt(LoginActivity.JOB_ID, jobId);
    }
    public void doBid(View view) {
        EditText hoursText = (EditText) findViewById(R.id.feedbackHours);
        int hours = Integer.parseInt(hoursText.getText().toString());
        EditText ratingsText = (EditText) findViewById(R.id.feedbackRatings);
        int ratings = Integer.parseInt(ratingsText.getText().toString());

        Worker newworker= new Worker();
        newworker.setWorker(workerId);
        newworker.setJob(jobId);
        newworker.sethours(hours);
        newworker.setRating(ratings);

        new NewWorkerTask(newworker).execute();
    }
    // Task to post a new Bid
    private class NewWorkerTask extends AsyncTask<Void, Void, String> {
        private String uri;
        private String toSend;

        NewWorkerTask(Worker b) {
            uri = "http://" + URIHandler.hostName + "/feedback";
            this.toSend = gson.toJson(b);
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doPost(uri, toSend);
        }

        @Override
        protected void onPostExecute(String messageId) {
            if("".equals(messageId)){
                userMessage("Feedback already Exists");}
            else
            {finish();}

        }
        private void userMessage(String message) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        }
    }




    private void loadMessage() {

        TextView description = (TextView) this.findViewById(R.id.name);
        description.setText(descriptions );
        TextView location = (TextView) this.findViewById(R.id.email);
        location.setText( locations);
        TextView date = (TextView) this.findViewById(R.id.phone);
        date.setText(dates);

    }

    public void closeMessage(View view) {
        finish();
    }


    private class findworker extends AsyncTask<Void, Void, String> {
        private String uri;

        findworker() {
            uri = "http://" + URIHandler.hostName + "/assignments?job=" + jobId;
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
           workerId = Integer.parseInt(result) ;
        }
    }




}