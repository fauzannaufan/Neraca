package com.example.user.neraca_1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.user.test.R;

public class JurnalTransaksiActivity extends ActionBarActivity {

    String[] barang = {"Uang", "Mie Kuning", "Daging Sapi", "Kerupuk", "Bakso"};
    String[] jumlah = {"Rp100.000", "1 kg", "1 kg", "15 buah", "Habis"};
    TextView nama;
    TextView nominal;
    int i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jurnal_transaksi);

        nama = (TextView) findViewById(R.id.nama_barang);
        nominal = (TextView) findViewById(R.id.nominal_barang);

        i = 0;
        nama.setText(barang[i]);
        nominal.setText(jumlah[i]);

        /*TextView test = (TextView)findViewById(R.id.textView11);
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

        test.setText(result);*/
    }

    public void GeserKanan(View view) {
        i++;
        Geser();
    }

    public void GeserKiri(View view) {
        i--;
        Geser();
    }

    public void Geser() {
        if (i < 0)
            i += 5;

        nama.setText(barang[i % 5]);
        nominal.setText(jumlah[i % 5]);

        if (jumlah[i % 5].equals("Habis")) {
            nominal.setTextColor(Color.BLACK);
        } else {
            nominal.setTextColor(Color.argb(255,11,159,38));
        }
    }

}
