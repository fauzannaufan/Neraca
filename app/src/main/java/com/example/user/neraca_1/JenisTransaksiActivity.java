package com.example.user.neraca_1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.test.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JenisTransaksiActivity extends ActionBarActivity {
    Button date;
    DatePickerDialog datePickerDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jenis_transaksi);

        Spinner jenisSpinner = (Spinner) findViewById(R.id.jenisTransaksiSpinner);
        ArrayAdapter<CharSequence> jenisAdapter = ArrayAdapter.createFromResource(this, R.array.list_transaksi, android.R.layout.simple_spinner_item);
        jenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisSpinner.setAdapter(jenisAdapter);

        ArrayAdapter<CharSequence> objekAdapter = ArrayAdapter.createFromResource(this, R.array.list_objek, android.R.layout.simple_spinner_item);
        objekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        date = (Button) findViewById(R.id.waktuTransaksiButton);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy", Locale.US);
        String formattedDate = df.format(c.getTime());
        date.setText(formattedDate);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(JenisTransaksiActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String the_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                date.setText(the_date);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    public void goToObjekTransaksi(View view){
        Spinner jenisSpinner = (Spinner) findViewById(R.id.jenisTransaksiSpinner);
        Button waktuButton = (Button) findViewById(R.id.waktuTransaksiButton);
        TextView keteranganTextView = (TextView) findViewById(R.id.keteranganTransaksiInput);

        String jenisTransaksi = jenisSpinner.getSelectedItem().toString();
        String waktuTransaksi = waktuButton.getText().toString();
        String keterangan = keteranganTextView.getText().toString();

        Intent intent;
        if (jenisTransaksi.equals("Penjualan")) {
            intent = new Intent(this, ObjekTransaksiActivity.class);
        } else {
            intent = new Intent(this, PembelianActivity.class);
        }

        intent.putExtra("jenis", jenisTransaksi);
        intent.putExtra("waktu", waktuTransaksi);
        intent.putExtra("keterangan", keterangan);

        startActivity(intent);

    }
}
