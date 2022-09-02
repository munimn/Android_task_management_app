package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ViewAllJobsActivity extends AppCompatActivity {

    private int userID;
    private Job[] completedjobs = null;
    private Job[] availablejobs = null;
    private int selected_handle1 = -1;
    private int selected_handle2 = -1;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            userID = Integer.parseInt(savedInstanceState.getString(LoginActivity.USER_ID));
        } else {
            Intent intent = getIntent();
            userID = Integer.parseInt(intent.getStringExtra(LoginActivity.USER_ID));
        }
        setContentView(R.layout.activity_view_all_jobs);
        gson = new Gson();
        new HandlesTask().execute();
        new HandlesTask2().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new HandlesTask().execute();
        new HandlesTask2().execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle arg0) {
        super.onSaveInstanceState(arg0);
        arg0.putString(LoginActivity.USER_ID, String.valueOf(userID));
    }

    public void viewcompletedJob(View view) {
        if (selected_handle2 != -1) {
            Job h = completedjobs[selected_handle2];
            Intent intent = new Intent(this, ViewJobActivity.class);
            intent.putExtra(LoginActivity.DESCRIPTION, h.getDescription());
            intent.putExtra(LoginActivity.DATE, String.valueOf(h.getDate()));
            intent.putExtra(LoginActivity.LOCATION, h.getLocation());
            intent.putExtra(LoginActivity.JOB_ID, h.getIdjob());
            intent.putExtra(LoginActivity.USER_ID, String.valueOf(userID));
            startActivity(intent);
        }
    }
    public void viewavailableJob(View view) {
        if (selected_handle1!= -1) {
            Job h = availablejobs[selected_handle1];
            Intent intent = new Intent(this, BidActivity.class);
            intent.putExtra(LoginActivity.USER_ID, String.valueOf(userID));
            intent.putExtra(LoginActivity.JOB_ID, h.getIdjob());
            startActivity(intent);
        }
    }



    private class HandlesTask extends AsyncTask<Void, Void, String> {
        private String uri;

        HandlesTask() {
            uri = "http://" + URIHandler.hostName + "/available?customer=" + userID;
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            loadHandles(result);
        }
    }

    private class HandlesTask2 extends AsyncTask<Void, Void, String> {
        private String uri;

        HandlesTask2() {
            uri = "http://" + URIHandler.hostName + "/completed?customer=" + userID;
        }

        @Override
        protected String doInBackground(Void... ignored) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            loadHandles2(result);
        }
    }




    private void loadHandles(String json) {
        availablejobs = null;
        String[] handleStrs = null;

        ListView handlesList = (ListView) findViewById(R.id.availablejobs);

        availablejobs = gson.fromJson(json, Job[].class);
        handleStrs = new String[availablejobs.length];
        for (int n = 0; n < handleStrs.length; n++) {
            Job job = availablejobs[n];
            handleStrs[n] = "(Jobid):"+job.getIdjob() + "  (Job):" + job.getDescription()   ;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, handleStrs);
        handlesList.setAdapter(adapter);

        handlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int j, long l) {
                // remember the selection
                selected_handle1 = j;
            }
        });
    }
    private void loadHandles2(String json) {
        completedjobs = null;
        String[] handleStrs = null;

        ListView handlesList = (ListView) findViewById(R.id.completedjobs);

        completedjobs= gson.fromJson(json, Job[].class);
        handleStrs = new String[completedjobs.length];
        for (int n = 0; n < handleStrs.length; n++) {
            Job job = completedjobs[n];
            handleStrs[n] = "(Jobid):"+job.getIdjob() + "  (Job):" + job.getDescription()   ;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, handleStrs);
        handlesList.setAdapter(adapter);

        handlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                // remember the selection
                selected_handle2= i;
            }
        });
    }

}