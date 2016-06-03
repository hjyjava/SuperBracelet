package com.huang.superbracelet.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 黄家远 on 16/6/3.
 */
public abstract class DbMigration {
    protected String currentVersion;

    abstract void migration(SQLiteDatabase db);
}
