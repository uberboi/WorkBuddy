package cmps121.workbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Steven Huang on 12/5/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "event_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "description";
    private static final String COL4 = "date";
    private static final String COL5 = "time";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String description, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, description);
        contentValues.put(COL4, date);
        contentValues.put(COL5, time);

        Log.e(TAG, "addData: Adding " + name + description + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL4 + " ASC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "," + COL2 + "," + COL3 + "," + COL4 + "," + COL5 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemFromDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "," + COL2 + "," + COL3 + "," + COL4 + "," + COL5 + " FROM " + TABLE_NAME +
                " WHERE " + COL4 + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates event
     */
    public void updateData(String newName, String newDescription, String newDate, String newTime,
                           String oldName, String oldDescription, String oldDate, String oldTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COL2 + " = '" + newName + "'" +
                ", " + COL3 + " = '" + newDescription + "'" +
                ", " + COL4 + " = '" + newDate  + "'" +
                ", " + COL5 + " = '" + newTime + "'" +
                " WHERE " + COL2 + " = '" + oldName + "'" +
                " AND " + COL3 + " = '" + oldDescription + "'" +
                " AND " + COL4 + " = '" + oldDate + "'" +
                " AND " + COL5 + " = '" + oldTime + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param name
     */
    public void deleteName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_NAME, COL1 + " = " + id, null);
        //db.close();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                 + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}