package academy.persistency.relaunch.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static academy.persistency.relaunch.sqlite.FilmContract.FilmEntry;

public class FilmRepository {

    private final Context mContext;

    public FilmRepository(Context mContext) {
        this.mContext = mContext;
    }

    private void saveData(List<Film> filmList) {
        SQLiteDatabase db = FilmDbHelper.getInstance(mContext).getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Film file : filmList) {
                values.put(FilmEntry.COLUMN_NAME_TITLE, file.getTitle());
                values.put(FilmEntry.COLUMN_NAME_DESCRIPTION, file.getDescription());
                values.put(FilmEntry.COLUMN_NAME_PIC_URL, file.getUrl());

                db.insert(FilmEntry.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        db.close();
    }

    private List<Film> getData() {
        SQLiteDatabase db = FilmDbHelper.getInstance(mContext).getReadableDatabase();
        String[] projection = {
                FilmEntry.COLUMN_NAME_TITLE,
                FilmEntry.COLUMN_NAME_DESCRIPTION,
                FilmEntry.COLUMN_NAME_PIC_URL
        };
        Cursor cursor = db.query(
                FilmEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        List<Film> filmList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(
                        cursor.getColumnIndexOrThrow(FilmEntry.COLUMN_NAME_TITLE));
                String description = cursor.getString(
                        cursor.getColumnIndexOrThrow(FilmEntry.COLUMN_NAME_DESCRIPTION));
                String url = cursor.getString(
                        cursor.getColumnIndexOrThrow(FilmEntry.COLUMN_NAME_PIC_URL));

                filmList.add(new Film(title, description, url));
            }
        }
        cursor.close();
        db.close();

        return filmList;
    }


}
