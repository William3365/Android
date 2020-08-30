package cn.edu.jsit.smartfactory.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//DatabaseHelper继承自SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    private  SQLiteDatabase db;
    private static final String DB_NAME="smartfactory";//数据库名称
    private static final int DB_VERSION = 1;//数据库版本
//重写
    public DatabaseHelper(Context context){
        //上下文，名称，游标，版本
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //sql语句
        db.execSQL("create table data(id integer primary key,temperature text,humidity text,light text)");
        db.beginTransaction();//开启事务
//创建一个触发器，用于限制数据库，只保存20条，如果有新数据，删除旧的
        try{
            db.execSQL("create trigger trigger_delete_top after insert on data " +//触发器名称
                    "begin delete from data where(select count(id) from data)>20" +
                    " and id in (select id from data order by id asc limit(select count(id)-20 from data));"+
                    "end;");
            db.setTransactionSuccessful();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
 public void read(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getReadableDatabase();
 }
 public void write(Context context)
 {
     DatabaseHelper databaseHelper = new DatabaseHelper(context);
     db = databaseHelper.getReadableDatabase();
 }
 public void insert(Context context,String temp,String hum,String light){
        write(context);
     ContentValues cv = new ContentValues();
     cv.put("temperature",temp);
     cv.put("humidity",hum);
     cv.put("light",light);
     db.insert("data",null,cv);
     close();
 }

 public List<Float> search(Context context,String type){
        read(context);
        //创建数组
        List<Float> data = new ArrayList<Float>();
        Cursor c = db.rawQuery("select * from data order by id desc",null);
        while(c.moveToNext()){
            float s=0;
            switch(type){
                case "温度":
                    s = Float.parseFloat(c.getString(1));//插入到第一列
                    break;
                case "湿度":
                    s = Float.parseFloat(c.getString(2));
                    break;
                case "光照":
                    s = Float.parseFloat(c.getString(3));
                    break;
            }
            data.add(s);
        }
        close();
        return data;
 }
 public void Close(){
        if(db!=null)
            db.close();
 }



    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVerion,int newVersion){

    }
}
