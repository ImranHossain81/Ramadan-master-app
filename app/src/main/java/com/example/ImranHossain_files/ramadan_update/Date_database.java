package com.example.ImranHossain_files.ramadan_update;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Date_database extends SQLiteOpenHelper {


    private static final String DATABASENAME = "datedatiles.db";
    private static final String TABLE_NAME = "user_datiles";
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String IFTER = "ifter";
    private static final String SEHERI = "seheri";
    private static final int VERTION_NUMBER = 7;
    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT NOT NULL," + IFTER + " TEXT NOT NULL," + SEHERI + " TEXT NOT NULL)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;


    public Date_database(Context context) {


        super(context, DATABASENAME, null, VERTION_NUMBER);
        this.context = context;


    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        try {

            db.execSQL(CREATE_TABLE);
            //Toast.makeText(context,"Database active",Toast.LENGTH_LONG).show();


        } catch (Exception e) {

            Toast.makeText(context, "App not support. " + e, Toast.LENGTH_LONG).show();


        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try {

            db.execSQL(DROP_TABLE);
            onCreate(db);


        } catch (Exception e) {

            Toast.makeText(context, "Sorry this apps is not support in your device." + e, Toast.LENGTH_LONG).show();


        }


    }


    public long insertData(Date_detiles date_detiles) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date_detiles.getDate());
        contentValues.put(IFTER, date_detiles.getIftari());
        contentValues.put(SEHERI, date_detiles.getSeheri());


        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowID;

    }

    public Boolean search(String uname) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_NAME, null);
        Boolean result = false;

        if (cursor.getCount() == 0) {
            //Toast.makeText(context," data not found",Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                String username = cursor.getString(
                        1);
                if (username.equals(uname)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public String searchinfo(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {Date_database.ID, Date_database.DATE, Date_database.IFTER, Date_database.SEHERI};
        Cursor cursor = sqLiteDatabase.query(Date_database.TABLE_NAME, columns, Date_database.DATE + " = '" + id + "'", null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int index0 = cursor.getColumnIndex(Date_database.ID);
            int index1 = cursor.getColumnIndex(Date_database.DATE);
            int index2 = cursor.getColumnIndex(Date_database.IFTER);
            int index3 = cursor.getColumnIndex(Date_database.SEHERI);

            String iid = cursor.getString(index0);
            String date = cursor.getString(index1);
            String ifter = cursor.getString(index2);
            String seheri = cursor.getString(index3);

            stringBuffer.append("\nতারিখ: " + date + "\n\n" + "সেহেরি: " + ifter + "\n\n" + "ইফতার: " + seheri);

        }

        return stringBuffer.toString();
    }


    public Cursor showAllData() {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }


    public Boolean updatedata(String date, String ifter, String seheri) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(IFTER, ifter);
        contentValues.put(SEHERI, seheri);

        sqLiteDatabase.update(TABLE_NAME, contentValues, DATE + " = ?", new String[]{date});

        return true;
    }


    public int deletedata(String id)

    {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME, DATE + " = ?", new String[]{id});
        return value;
    }


}
