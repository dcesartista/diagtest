package com.example.p_code.diagnostictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class UjianActivity extends AppCompatActivity {
    public static final String TAG = UjianActivity.class.getSimpleName();
    //private RecyclerView mRecyclerView;
    //private SoalAdapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    private ListView mListView;
    private SoalAdapterNew mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);

        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new SoalAdapterNew(this,getDataSet());
        mListView.setAdapter(mAdapter);

       /* mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewUjian);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SoalAdapter(getDataSet(),this);
        mRecyclerView.setAdapter(mAdapter);*/

    }

    private ArrayList<Soal> getDataSet() {
        ArrayList results = new ArrayList<Soal>();
        for (int index = 0; index < 5; index++) {
            results.add(new Soal("Soal " + (index+1), "Opsi AD", "Opsi B", "Opsi C", "Opsi D", "Alasan",
                                 "Alasan 3", "Alasan 2", "Alasan 3", "Alasan 4"));
        }
        return results;
    }
}
