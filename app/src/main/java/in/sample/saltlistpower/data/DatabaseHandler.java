package in.sample.saltlistpower.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import in.sample.saltlistpower.model.ItemModel;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "items";
    private static final String TABLE = "item_table";

    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + DESCRIPTION + " TEXT,"
                + IMAGE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public void addItemModelList(List<ItemModel> itemModelList) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < itemModelList.size(); i++) {
            ItemModel itemModel = itemModelList.get(i);
            ContentValues values = new ContentValues();
            values.put(TITLE, itemModel.getTitle());
            values.put(DESCRIPTION, itemModel.getDescription());
            values.put(IMAGE, itemModel.getImage());

            // Inserting Row
            db.insert(TABLE, null, values);
        }

        db.close(); // Closing database connection
    }

    public ItemModel getItemModel(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[]{ID,
                        TITLE, DESCRIPTION, IMAGE}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ItemModel itemModel = new ItemModel(cursor.getString(3),
                cursor.getString(2), cursor.getString(1));
        return itemModel;
    }

    public List<ItemModel> getItemModelList() {
        List<ItemModel> itemModelList = new ArrayList<ItemModel>();
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemModel itemModel = new ItemModel();
                itemModel.setID(Integer.parseInt(cursor.getString(0)));
                itemModel.setTitle(cursor.getString(1));
                itemModel.setDescription(cursor.getString(2));
                itemModel.setImage(cursor.getString(3));
                itemModelList.add(itemModel);
            } while (cursor.moveToNext());
        }

        return itemModelList;
    }


}
