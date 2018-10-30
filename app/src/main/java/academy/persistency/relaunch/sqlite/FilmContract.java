package academy.persistency.relaunch.sqlite;

import android.provider.BaseColumns;

public class FilmContract {

    private FilmContract() { }

    public static class FilmEntry implements BaseColumns {
        public static final String TABLE_NAME = "FILM_ENTRY";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PIC_URL = "url";
    }
}


