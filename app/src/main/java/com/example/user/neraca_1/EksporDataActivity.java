package com.example.user.neraca_1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.test.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class EksporDataActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ekspor_data);
    }

    public void EksporData(View view) {
        EditText nama = (EditText) findViewById(R.id.nama_file);
        String nama_file = nama.getText().toString();
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        String root = Environment.getExternalStorageDirectory().toString();
        File export_dir = new File(root + "/neraca");

        File file = new File(export_dir, nama_file+".csv");
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM transaksi", null);
            csvWriter.writeNext(cursor.getColumnNames());
            while (cursor.moveToNext()) {
                String arr[] = {cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)};
                csvWriter.writeNext(arr);
            }
            csvWriter.close();
            cursor.close();
            Log.i("File", "File "+nama_file+".csv berhasil");
        } catch (Exception e) {
            Log.i("Gagal", "gagal karena "+e.toString());
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(), "Ekspor data berhasil!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
