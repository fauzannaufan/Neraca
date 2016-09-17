package com.example.user.neraca_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.TextView;

import com.example.user.test.R;

import java.util.ArrayList;

/**
 * Created by User on 8/2/2016.
 */
public class JurnalTransaksiActivity extends ActionBarActivity {

    DBHelper mydb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jurnal_transaksi);

        TextView test = (TextView)findViewById(R.id.textView11);
        mydb = new DBHelper(this);
        ArrayList<String> AL = mydb.getAllTransaksi();

        int row_num = Integer.parseInt(AL.get(0));

        String result = "";
        for (int i = 0; i < row_num ; i++){
            result += AL.get(i*4+1) + "|" + AL.get(i*4+2) + "|" + AL.get(i*4+3) + "|" + AL.get(i*4+4) + "\n";
        }

        result += "\n\n";

        ArrayList<String> ALOR = mydb.getAllObjekRef();

        int row_num_or = Integer.parseInt(ALOR.get(0));

        for (int j = 0; j < row_num_or ; j++){
            result += ALOR.get(j*4+1) + "|" + ALOR.get(j*4+2) + "|" + ALOR.get(j*4+3) + "|" + ALOR.get(j*4+4) + "\n";
        }

        test.setText(result);
    }

}
