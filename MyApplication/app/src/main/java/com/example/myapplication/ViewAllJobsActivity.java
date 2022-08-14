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

    private String userID;
    private Job[] jobs = null;
    private int selected_handle = -1;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            userID = savedInstanceState.getString(LoginActivity.USER_ID);
        } else {
            Intent intent = getIntent();
            userID = intent.getStringExtra(LoginActivity.USER_ID);
        }
        setContentView(R.layout.activity_view_all_jobs);
        gson = new Gson();
        new HandlesTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new HandlesTask().execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle arg0) {
        super.onSaveInstanceState(arg0);
        arg0.putString(LoginActivity.USER_ID, userID);
    }

    public void viewJob(View view) {
        if (selected_handle != -1) {
            Job h = jobs[selected_handle];
            Intent intent = new Intent(this, ViewJobActivity.class);
            intent.putExtra(LoginActivity.DESCRIPTION, h.getDescription());
            intent.putExtra(LoginActivity.DATE, String.valueOf(h.getDate()));
            intent.putExtra(LoginActivity.LOCATION, h.getLocation());
            intent.putExtra(LoginActivity.JOB_ID, h.getIdjob());
            intent.putExtra(LoginActivity.USER_ID, userID);
            startActivity(intent);
        }
    }



    private class HandlesTask extends AsyncTask<Void, Void, String> {
        private String uri;

        HandlesTask() {
            uri = "http://" + URIHandler.hostName + "/available";
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




    private void loadHandles(String json) {
        jobs = null;
        String[] handleStrs = null;

        ListView handlesList = (ListView) findViewById(R.id.JobsList);

        jobs = gson.fromJson(json, Job[].class);
        handleStrs = new String[jobs.length];
        for (int n = 0; n < handleStrs.length; n++) {
            Job job = jobs[n];
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
                selected_handle = i;
            }
        });
    }

}