package com.huang.superbracelet.activity.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.huang.dao.DaoMaster;
import com.huang.dao.DaoSession;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseActivity;

public class MainActivity extends BaseActivity{

    private SQLiteDatabase db;
    private EditText editText;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    public static final String TAG = "DaoExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();

        searchBy();

        editText = (EditText) findViewById(R.id.editTextNote);
    }

    @Override
    protected void initWedgit() {

    }

    private void searchBy(){
//        String textColumn = NoteDao.Properties.Text.columnName;
//        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
//        cursor = db.query(getNoteDao().getTablename(), getNoteDao().getAllColumns(), null, null, null, null, orderBy);
//        String[] from = {textColumn, NoteDao.Properties.Comment.columnName};
        int[] to = {android.R.id.text1, android.R.id.text2};

//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
//                to);
//        setListAdapter(adapter);
    }

    private void openDB(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"notes-db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

//    private NoteDao getNoteDao(){
//        return daoSession.getNoteDao();
//    }

//    private void addNote(){
//        String noteText = editText.getText().toString();
//        editText.setText("");
//
//        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
//        String comment = "Added on " + df.format(new Date());
//
//        // 插入操作，简单到只要你创建一个 Java 对象
//        Note note = new Note(null, noteText, comment, new Date());
//        getNoteDao().insert(note);
//    }

//    private void search(){
//        Query query = getNoteDao().queryBuilder()
//                .where(NoteDao.Properties.Text.eq("Test1"))
//                .orderAsc(NoteDao.Properties.CreationDate)
//                .build();
//        List<Note> notes = query.list();
//        QueryBuilder.LOG_SQL = true;
//        QueryBuilder.LOG_VALUES = true;
//        searchBy();
//    }

    public void onMyButtonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
//                addNote();
                break;
            case R.id.buttonSearch:
//                search();
                break;
            default:
                Log.d(TAG, "what has gone wrong ?");
                break;
        }
    }

}
