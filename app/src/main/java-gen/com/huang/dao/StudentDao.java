package com.huang.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.huang.bean.Student;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Student".
*/
public class StudentDao extends AbstractDao<Student, Void> {

    public static final String TABLENAME = "Student";

    /**
     * Properties of entity Student.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "Id", false, "ID");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Xingming = new Property(2, String.class, "xingming", false, "XINGMING");
        public final static Property Xuehao = new Property(3, String.class, "xuehao", false, "XUEHAO");
        public final static Property Nianji = new Property(4, String.class, "nianji", false, "NIANJI");
        public final static Property Banji = new Property(5, String.class, "banji", false, "BANJI");
        public final static Property Xingbie = new Property(6, String.class, "xingbie", false, "XINGBIE");
    };

    private DaoSession daoSession;


    public StudentDao(DaoConfig config) {
        super(config);
    }
    
    public StudentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Student\" (" + //
                "\"ID\" TEXT NOT NULL ," + // 0: Id
                "\"USERNAME\" TEXT," + // 1: username
                "\"XINGMING\" TEXT," + // 2: xingming
                "\"XUEHAO\" TEXT," + // 3: xuehao
                "\"NIANJI\" TEXT," + // 4: nianji
                "\"BANJI\" TEXT," + // 5: banji
                "\"XINGBIE\" TEXT);"); // 6: xingbie
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Student\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Student entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getId());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String xingming = entity.getXingming();
        if (xingming != null) {
            stmt.bindString(3, xingming);
        }
 
        String xuehao = entity.getXuehao();
        if (xuehao != null) {
            stmt.bindString(4, xuehao);
        }
 
        String nianji = entity.getNianji();
        if (nianji != null) {
            stmt.bindString(5, nianji);
        }
 
        String banji = entity.getBanji();
        if (banji != null) {
            stmt.bindString(6, banji);
        }
 
        String xingbie = entity.getXingbie();
        if (xingbie != null) {
            stmt.bindString(7, xingbie);
        }
    }

    @Override
    protected void attachEntity(Student entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Student readEntity(Cursor cursor, int offset) {
        Student entity = new Student( //
            cursor.getString(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // xingming
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // xuehao
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nianji
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // banji
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // xingbie
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Student entity, int offset) {
        entity.setId(cursor.getString(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setXingming(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setXuehao(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNianji(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBanji(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setXingbie(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Student entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Student entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
