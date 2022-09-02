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

public class BidActivity extends AppCompatActivity {
    private int jobId;
    private int userID;
    private Bid[] bids = null;
    private int selected_handle = -1;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            jobId = savedInstanceState.getInt(LoginActivity.JOB_ID);
            userID = Integer.parseInt(savedInstanceState.getString(LoginActivity.USER_ID));
        } else {
            Intent intent = getIntent();
            jobId = intent.getIntExtra(LoginActivity.JOB_ID, 0);
            userID = Integer.parseInt(intent.getStringExtra(LoginActivity.USER_ID));
        }
        setContentView(R.layout.activity_bid);
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
        arg0.putInt(LoginActivity.JOB_ID, jobId);
        arg0.putString(LoginActivity.USER_ID, String.valueOf(userID));
    }
    public void viewavailableJob(View view) {
        if (selected_handle!= -1) {
            Bid h = bids[selected_handle];
            Intent intent = new Intent(this, WorkerViewActivity.class);
            intent.putExtra(LoginActivity.WORKER, String.valueOf(h.getWorker()));
            intent.putExtra(LoginActivity.JOB_ID, String.valueOf(h.getJob()));
            intent.putExtra(LoginActivity.USER_ID, String.valueOf(userID));
            startActivity(intent);
        }
    }
    public void closeMessage(View view) {
        finish();
    }

    private class HandlesTask extends AsyncTask<Void, Void, String> {
        private String uri;

        HandlesTask() {
            uri = "http://" + URIHandler.hostName + "/bids?job=" + jobId;
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
        bids = null;
        String[] handleStrs = null;

        ListView handlesList = (ListView) findViewById(R.id.bidsList);

        bids = gson.fromJson(json, Bid[].class);
        handleStrs = new String[bids.length];
        for (int n = 0; n < handleStrs.length; n++) {
            Bid bid = bids[n];
            handleStrs[n] = "(Workerid):"+bid.getWorker();
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