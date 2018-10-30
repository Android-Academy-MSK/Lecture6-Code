package academy.persistency.relaunch.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static academy.persistency.relaunch.sqlite.FilmContract.FilmEntry;

public class FilmDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FilmDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FilmEntry.TABLE_NAME + " (" +
                    FilmEntry._ID + " INTEGER PRIMARY KEY," +
                    FilmEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FilmEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FilmEntry.COLUMN_NAME_PIC_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FilmEntry.TABLE_NAME;


    private FilmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteOpenHelper getInstance(Context context) {
        return new FilmDbHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
