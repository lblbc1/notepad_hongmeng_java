package cn.lblbc.notepad.db;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class DbHelper {
    private static DbHelper dbHelper = new DbHelper();
    private OrmContext ormContext;

    private DbHelper() {
    }

    public static DbHelper getInstance() {
        return dbHelper;
    }

    public void initDb(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ormContext = databaseHelper.getOrmContext("lblDb", "lblDb.db", NoteDatabase.class);
    }

    public void add(String content) {
        Note note = new Note();
        note.setId(System.currentTimeMillis());
        note.setContent(content);
        ormContext.insert(note);
        ormContext.flush();
    }

    public List<Note> query() {
        OrmPredicates ormPredicates = ormContext.where(Note.class);
        return ormContext.query(ormPredicates);
    }

    public Note query(long noteId) {
        OrmPredicates ormPredicates = ormContext.where(Note.class).equalTo("id", noteId + "");
        List<Note> noteList = ormContext.query(ormPredicates);
        Note note = null;
        if (!noteList.isEmpty()) {
            note = noteList.get(0);
        }
        return note;
    }

    public void update(Note note) {
        ormContext.update(note);
        ormContext.flush();
    }

    public void delete(long noteId) {
        OrmPredicates ormPredicates = ormContext.where(Note.class).equalTo("id", noteId + "");
        ormContext.delete(ormPredicates);
        ormContext.flush();
    }
}
