package com.huang.superbracelet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.huang.dao.DaoMaster;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by 黄家远 on 16/5/27.
 */
public class MyDevOpenHelper extends DaoMaster.OpenHelper {

    public static final String DB_NAME = "exam.db";

    private static final SortedMap<Integer, Migration> ALL_MIGRATIONS = new TreeMap<>();

    static {
        ALL_MIGRATIONS.put(1, new V1Migration());
        ALL_MIGRATIONS.put(2, new V2Migration());
        ALL_MIGRATIONS.put(3, new V3Migration());
    }

    public MyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SortedMap<Integer, Migration> migrations = ALL_MIGRATIONS.subMap(oldVersion, newVersion);
        for (Integer version : migrations.keySet()) {
            migrations.get(version).migrate(db);
        }
    }

    public interface Migration {
        void migrate(SQLiteDatabase db);
    }

    public static class V1Migration implements Migration {
        @Override
        public void migrate(SQLiteDatabase db) {
//            db.execSQL("ALTER TABLE" + StudentDao.TABLENAME + "ADD COLUMN width");
        }
    }

    public static class V2Migration implements Migration {
        @Override
        public void migrate(SQLiteDatabase db) {
//            db.execSQL("ALTER TABLE" + StudentDao.TABLENAME + "ADD COLUMN height");
        }
    }

    public static class V3Migration implements Migration {
        @Override
        public void migrate(SQLiteDatabase db) {
//            db.execSQL("ALTER TABLE" + StudentDao.TABLENAME + "ADD COLUMN boothom");
        }
    }
}
