package chimpokomon.com.example.pep.chimpokomon.database;

import android.provider.BaseColumns;

public final class DBtest {

    private DBtest() {
    }

    public static class Table_test implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DONE = "done";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_STARRED = "starred";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_NOTIFY = "notify";
    }
}
