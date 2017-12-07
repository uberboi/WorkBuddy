package cmps121.workbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tegster007 on 12/6/17.
 */

public class todoDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "todoDatabaseHelper";

    private static final String TABLE_NAME = "todo_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "description";
    private static final String COL4 = "date";
    //private static final String COL5 = "time";

    public todoDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
              //  COL4 + " TEXT," +
                COL4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);
        //contentValues.put(COL5, item4);

        Log.e(TAG, "addData: Adding " + item + item2 + " to " + TABLE_NAME);

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
        String query = "SELECT * FROM " + TABLE_NAME;
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
        String query = "SELECT " + COL2 + "," + COL3 + "," + COL4 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemFromDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "," + COL2 + "," + COL3 + "," + COL4 + " FROM " + TABLE_NAME +
                " WHERE " + COL4 + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param oldName
     */
    public void updateData(String newName, String newDescription, String newDate,
                           String oldName, String oldDescription, String oldDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COL2 + " = '" + newName + "'" +
                ", " + COL3 + " = '" + newDescription + "'" +
                ", " + COL4 + " = '" + newDate  + "'" +
                " WHERE " + COL2 + " = '" + oldName + "'" +
                " AND " + COL3 + " = '" + oldDescription + "'" +
                " AND " + COL4 + " = '" + oldDate + "'";
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
