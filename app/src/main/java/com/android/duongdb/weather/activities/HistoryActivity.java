package com.android.duongdb.weather.activities;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.duongdb.weather.R;
import com.android.duongdb.weather.adapters.HistoryRecyclerAdapter;
import com.android.duongdb.weather.databases.DeleteCityThread;
import com.android.duongdb.weather.databases.ListCityThread;
import com.android.duongdb.weather.databases.UpdateCityThread;
import com.android.duongdb.weather.models.HistoryModel;
import com.android.duongdb.weather.utils.DatabaseDeleteListener;
import com.android.duongdb.weather.utils.DatabaseLoadListener;
import com.android.duongdb.weather.utils.HistoryTouchHelper;
import com.android.duongdb.weather.utils.ItemRemoveListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements DatabaseLoadListener, ItemRemoveListener, DatabaseDeleteListener {

    private RecyclerView historyRecyclerView;
    private HistoryRecyclerAdapter historyRecyclerAdapter;
    private ListCityThread listCityThread;
    private ArrayList<HistoryModel> historyModels;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyRecyclerView = (RecyclerView) findViewById(R.id.history_recycler);
        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.message);

        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listCityThread = new ListCityThread(this);
        listCityThread.execute();
        listCityThread.setDatabaseLoadListener(this);
    }

    @Override
    public void onFinish(ArrayList<HistoryModel> result) {
        if(result.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("History unavailable");
            return;
        }
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        historyModels = result;
        historyRecyclerAdapter = new HistoryRecyclerAdapter(this, historyModels, HistoryActivity.this);
        historyRecyclerView.setAdapter(historyRecyclerAdapter);
        ItemTouchHelper.Callback callback = new HistoryTouchHelper(historyRecyclerAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(historyRecyclerView);
    }

    @Override
    public void onRemove(final int position, final HistoryModel historyModel) {
        DeleteCityThread deleteCityThread = new DeleteCityThread(HistoryActivity.this);
        deleteCityThread.setDatabaseDeleteListener(HistoryActivity.this);
        deleteCityThread.execute(historyModels.get(position).getCity(), position + "");
        Snackbar snackbar = Snackbar.make(historyRecyclerView, "Deleted location : " + historyModels.get(position).getCity() , Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        historyModels.add(position, historyModel);
                        historyRecyclerAdapter.notifyItemInserted(position);
                        imageView.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        UpdateCityThread updateCityThread = new UpdateCityThread(HistoryActivity.this);
                        updateCityThread.execute(historyModel);
                    }
                });
        snackbar.show();
    }

    @Override
    public void onFinishDelete(int position) {
        historyModels.remove(position);
        historyRecyclerAdapter.notifyItemRemoved(position);
        if(historyModels.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("History unavailable");
        }
    }
}
