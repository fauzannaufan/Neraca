package com.example.user.neraca_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Neraca.db";
    //public static final String TRANSAKSI_TABLE_NAME = "transaksi";
    //public static final String TRANSAKSI_COLUMN_ID = "id_transaksi";
    //public static final String TRANSAKSI_COLUMN_WAKTU = "waktu_transaksi";
    //public static final String TRANSAKSI_COLUMN_JENIS = "jenis_transaksi";
    //public static final String TRANSAKSI_COLUMN_KETERANGAN = "keterangan_transaksi";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table transaksi " +
                        "(id_transaksi integer primary key, waktu_transaksi text, jenis_transaksi text,keterangan_transaksi text)"
        );
        db.execSQL("create table objek_ref" +
                "(id_objek_ref integer primary key, kategori text, nama text, satuan text)"
        );
        db.execSQL("create table objek_transaksi" +
                        "(id_objek integer primary key, id_transaksi integer NOT NULL, id_objek_ref integer NOT NULL, status integer NOT NULL, nominal integer, frekuensi_pembayaran integer, cicilan integer, bunga integer)"
        );
    }

    /*
    public Cursor getTransaksiPada (String waktu){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from transaksi where waktu_transaksi="+waktu+"", null );
        return res;
    }

    public Cursor getObjekMasuk (int id_transaksi){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from objek_transaksi where id_transaksi="+id_transaksi+" and status=1", null );
        return res;
    }

    public Cursor getObjekKeluar (int id_transaksi){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from objek_transaksi where id_transaksi=" + id_transaksi + " and status=2", null);
        return res;
    }
    */

    public Cursor getListBarang() {

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select nama, satuan from objek_ref where kategori='barang'", null );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transaksi");
        db.execSQL("DROP TABLE IF EXISTS objek_ref");
        db.execSQL("DROP TABLE IF EXISTS objek_transaksi");
        onCreate(db);
    }

    public boolean insertTransaksi (ArrayList<String> data)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id_transaksi", 100);
        contentValues.put("jenis_transaksi", data.get(0));
        contentValues.put("waktu_transaksi", data.get(1));
        contentValues.put("keterangan_transaksi", data.get(2));
        db1.insert("transaksi", null, contentValues);

        /*
        SQLiteDatabase db2 = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("id_objek_ref", 100);
        contentValues.put("kategori", "uang");
        contentValues.put("nama", "Uang");
        contentValues.put("satuan", "rupiah");
        //db2.insert("objek_ref", null, contentValues);

        SQLiteDatabase db3 = this.getWritableDatabase();
        contentValues = new ContentValues();
        //contentValues.put("id_objek",100);
        contentValues.put("id_transaksi", 1);
        contentValues.put("id_objek_ref", 1);
        contentValues.put("status", 1);
        contentValues.put("nominal", 1000);
        //db3.insert("objek_transaksi", null, contentValues);

        */
        return true;
    }

    /*
    public ArrayList<String> getAllTransaksi() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from transaksi", null );
        res.moveToFirst();
        int row_num = res.getCount();

        array_list.add(row_num + "");
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("id_transaksi")));
            array_list.add(res.getString(res.getColumnIndex("jenis_transaksi")));
            array_list.add(res.getString(res.getColumnIndex("waktu_transaksi")));
            array_list.add(res.getString(res.getColumnIndex("keterangan_transaksi")));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllObjekRef() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from objek_ref", null );
        res.moveToFirst();
        int row_num = res.getCount();

        array_list.add(row_num + "");
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("id_objek_ref")));
            array_list.add(res.getString(res.getColumnIndex("kategori")));
            array_list.add(res.getString(res.getColumnIndex("nama")));
            array_list.add(res.getString(res.getColumnIndex("satuan")));
            res.moveToNext();
        }
        return array_list;
    }
    */

}
