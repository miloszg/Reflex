package com.milosz.re_flex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

        private static final String DB_NAME="Users.db";
        private static final String DB_TABLE="User_Table";

        private static final String ID="ID";
        private static final String NAME="NAME";
        private static final String POINTS="POINTS";

        private static final String CREATE_TABLE="CREATE TABLE "+DB_TABLE+" ("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME+" TEXT, "+ POINTS +" INTEGER )";

        public DataBase(Context context){
            super(context,DB_NAME,null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);

            onCreate(db);
        }
        public boolean insertDataName(String name){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME,name);
            //long result=db.insert(DB_TABLE,null,contentValues);
            db.insert(DB_TABLE,null,contentValues);
            return true;
//            db.execSQL("INSERT INTO " + DB_TABLE+"(NAME, POINTS) VALUES (name, points);");
//            return true;
        }
        public boolean insertDataPoints(int punkty){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(POINTS,punkty);
            //long result= db.insert(DB_TABLE,null,contentValues);
            db.insert(DB_TABLE,null,contentValues);
            return true;
        }
        public Cursor viewData(){
            SQLiteDatabase db=this.getReadableDatabase();
            String query="SELECT*FROM "+DB_TABLE;
            Cursor c=db.rawQuery(query,null);

            return c;
        }
}
