package com.example.p_code.diagnostictest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.p_code.diagnostictest.Interface.VolleyInterface;
import com.example.p_code.diagnostictest.Template.EndPointAPI;
import com.example.p_code.diagnostictest.Template.Template;
import com.example.p_code.diagnostictest.Utils.Data;
import com.example.p_code.diagnostictest.Utils.JSONParser;
import com.example.p_code.diagnostictest.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity implements VolleyInterface {

    public VolleyRequest mRequest;
    public JSONParser mJSONParser;
    TextView tvTime1, tvTime2, tvTime3, tvMark1, tvMark2, tvMark3,
            tvDate1, tvDate2, tvDate3;
    View noHis1, noHis2, noHis3, lin1, lin2, lin3;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        linkToLayout();

        mRequest = new VolleyRequest(this);
        mJSONParser = new JSONParser(this);

        getHistory();
    }

    private void getHistory() {
        Map<String,String> map = new HashMap<>();
        map.put(Template.Query.TAG, Template.Query.HISTORY);
        map.put(Template.Query.NISN, Data.nisn);
        mRequest.sendPostRequest(EndPointAPI.DIAGTEST_HISTORY, map);

    }

    @Override
    public void onPrepare() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mempersiapkan History...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        Log.v("LALALALALALLA HEHE",jsonObject.toString());
        //Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show();
        mJSONParser.isSuccess(jsonObject,this);

        if (null != Data.timeHistory[2]){
            tvDate1.setText(Data.timeHistory[0].substring(8,10) + "-" +
                            Data.timeHistory[0].substring(5,7) +
                            "-" + Data.timeHistory[0].substring(0,4));
            tvTime1.setText(Data.timeHistory[0].substring(11,16));
            tvMark1.setText(Data.markHistory[0]);
            lin1.setVisibility(View.VISIBLE);
            noHis1.setVisibility(View.GONE);

            tvDate2.setText(Data.timeHistory[1].substring(8,10) + "-" +
                            Data.timeHistory[1].substring(5,7) +
                            "-" + Data.timeHistory[1].substring(0,4));
            tvTime2.setText(Data.timeHistory[1].substring(11,16));
            tvMark2.setText(Data.markHistory[1]);
            lin2.setVisibility(View.VISIBLE);
            noHis2.setVisibility(View.GONE);

            tvDate3.setText(Data.timeHistory[2].substring(8,10) + "-" +
                            Data.timeHistory[2].substring(5,7) +
                            "-" + Data.timeHistory[2].substring(0,4));
            tvTime3.setText(Data.timeHistory[2].substring(11,16));
            tvMark3.setText(Data.markHistory[2]);
            lin3.setVisibility(View.VISIBLE);
            noHis3.setVisibility(View.GONE);
        }
        else if(null != Data.timeHistory[1]){
            tvDate1.setText(Data.timeHistory[0].substring(8,10) + "-" +
                            Data.timeHistory[0].substring(5,7) +
                            "-" + Data.timeHistory[0].substring(0,4));
            tvTime1.setText(Data.timeHistory[0].substring(11,16));
            tvMark1.setText(Data.markHistory[0]);
            lin1.setVisibility(View.VISIBLE);
            noHis1.setVisibility(View.GONE);

            tvDate2.setText(Data.timeHistory[1].substring(8,10) + "-" +
                            Data.timeHistory[1].substring(5,7) +
                            "-" + Data.timeHistory[1].substring(0,4));
            tvTime2.setText(Data.timeHistory[1].substring(11,16));
            tvMark2.setText(Data.markHistory[1]);
            lin2.setVisibility(View.VISIBLE);
            noHis2.setVisibility(View.GONE);


            /*tvDate3.setText("-");
            tvTime3.setText("-");
            tvMark3.setText("-");*/
        }
        else if(null != Data.timeHistory[0]){
            tvDate1.setText(Data.timeHistory[0].substring(8,10) + "-" +
                            Data.timeHistory[0].substring(5,7) +
                            "-" + Data.timeHistory[0].substring(0,4));
            tvTime1.setText(Data.timeHistory[0].substring(11,16));
            tvMark1.setText(Data.markHistory[0]);
            noHis1.setVisibility(View.GONE);
            lin1.setVisibility(View.VISIBLE);

            /*tvDate2.setText("-");
            tvTime2.setText("-");
            tvMark2.setText("-");

            tvDate3.setText("-");
            tvTime3.setText("-");
            tvMark3.setText("-");*/
        }
    }

    @Override
    public void onFailed(VolleyError errorListener) {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        Toast.makeText(this, "Gagal mendapatkan History!! Harap periksa koneksi internet anda!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void linkToLayout(){
        tvTime1 = (TextView)findViewById(R.id.time1);
        tvTime2 = (TextView)findViewById(R.id.time2);
        tvTime3 = (TextView)findViewById(R.id.time3);
        tvMark1 = (TextView)findViewById(R.id.mark1);
        tvMark2 = (TextView)findViewById(R.id.mark2);
        tvMark3 = (TextView)findViewById(R.id.mark3);
        tvDate1 = (TextView)findViewById(R.id.date1);
        tvDate2 = (TextView)findViewById(R.id.date2);
        tvDate3 = (TextView)findViewById(R.id.date3);
        noHis1 = findViewById(R.id.noHis1);
        noHis2 = findViewById(R.id.noHis2);
        noHis3 = findViewById(R.id.noHis3);
        lin1 = findViewById(R.id.linLayC1);
        lin2 = findViewById(R.id.linLayC2);
        lin3 = findViewById(R.id.linLayC3);
    }

    public String changeNumToMonth(int num){
        switch (num){
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Desember";
            default:
                return "";
        }
    }
}
