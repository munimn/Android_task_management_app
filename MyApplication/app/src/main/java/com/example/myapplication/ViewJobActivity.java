package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
            workerId = Integer.parseInt(savedInstanceState.getString(LoginActivity.USER_ID));
            jobId = savedInstanceState.getInt(LoginActivity.JOB_ID);
            descriptions = savedInstanceState.getString(LoginActivity.DESCRIPTION);
            locations = savedInstanceState.getString(LoginActivity.LOCATION);
            dates = savedInstanceState.getString(LoginActivity.DATE);
        } else {
            Intent intent = getIntent();
            workerId = Integer.parseInt(intent.getStringExtra(LoginActivity.USER_ID));
            jobId = intent.getIntExtra(LoginActivity.JOB_ID,0);
            descriptions = intent.getStringExtra(LoginActivity.DESCRIPTION);
            locations = intent.getStringExtra(LoginActivity.LOCATION);
            dates = intent.getStringExtra(LoginActivity.DATE);
        }
        setContentView(R.layout.activity_view_job);
        gson = new Gson();
        loadMessage();

    }

    @Override
    protected void onSaveInstanceState(Bundle arg0) {
        super.onSaveInstanceState(arg0);
        arg0.putInt(LoginActivity.USER_ID, workerId);
        arg0.putInt(LoginActivity.JOB_ID, jobId);

    }
    public void doBid(View view) {

        Bid newBid = new Bid();
        newBid.setWorker(workerId);
        newBid.setJob(jobId);

        new NewBidTask(newBid).execute();
    }
    // Task to post a new Bid
    private class NewBidTask extends AsyncTask<Void, Void, String> {
        private String uri;
        private String toSend;

        NewBidTask(Bid b) {
            uri = "http://" + URIHandler.hostName + "/bids";
            this.toSend = gson.toJson(b);
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doPost(uri, toSend);
        }

        @Override
        protected void onPostExecute(String messageId) {
            finish();
        }
    }




    private void loadMessage() {

        TextView description = (TextView) this.findViewById(R.id.description);
        description.setText(descriptions );
        TextView location = (TextView) this.findViewById(R.id.location);
        location.setText( locations);
        TextView date = (TextView) this.findViewById(R.id.date);
        date.setText(dates);

    }

    public void closeMessage(View view) {
        finish();
    }




}