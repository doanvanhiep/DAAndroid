package hiephandsome.com.doancuoiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hiephandsome.com.doancuoiki.CauHoi.BoCauHoi;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "DBCauHoi";


    // Tên bảng: Note.
    private static final String TABLE_BoCauHoi = "BoCauHoi";

    private static final String COLUMN_ID ="Id";
    private static final String COLUMN_CauHoi ="CauHoi";
    private static final String COLUMN_DAA = "DAA";
    private static final String COLUMN_DAB = "DAB";
    private static final String COLUMN_DAC = "DAC";
    private static final String COLUMN_DAD = "DAD";
    private static final String COLUMN_CAUTL = "CAUTL";
    private static final String COLUMN_Loai = "Loai";





    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_BoCauHoi + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + COLUMN_CauHoi + " TEXT,"
                + COLUMN_DAA + " TEXT,"
                + COLUMN_DAB + " TEXT,"
                + COLUMN_DAC + " TEXT,"
                + COLUMN_DAD + " TEXT,"
                + COLUMN_CAUTL + " TEXT,"
                + COLUMN_Loai + " TEXT" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BoCauHoi);


        // Và tạo lại.
        onCreate(db);
    }

    public void addBoCauHoi(BoCauHoi boCauHoi) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + boCauHoi.getCauhoiId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CauHoi,boCauHoi.getCauHoi());
        values.put(COLUMN_DAA,boCauHoi.getDapAnA());
        values.put(COLUMN_DAB,boCauHoi.getDapAnB());
        values.put(COLUMN_DAC,boCauHoi.getDapAnC());
        values.put(COLUMN_DAD,boCauHoi.getDapAnD());
        values.put(COLUMN_CAUTL,boCauHoi.getCauTraLoi());
        values.put(COLUMN_Loai,boCauHoi.getLoai());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_BoCauHoi, null, values);


        // Đóng kết nối database.
        db.close();
    }
    //Lấy tất cả câu hỏi theo loại
    public ArrayList<BoCauHoi> getAllNotesBoCauHoi(String loai) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        ArrayList<BoCauHoi> boCauHoiList = new ArrayList<BoCauHoi>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BoCauHoi +" where "+COLUMN_Loai +"='" +loai+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                BoCauHoi boCauHoi = new BoCauHoi();
                boCauHoi.setCauhoiId(Integer.parseInt(cursor.getString(0)));
                boCauHoi.setCauHoi(cursor.getString(1));
                boCauHoi.setDapAnA(cursor.getString(2));
                boCauHoi.setDapAnB(cursor.getString(3));
                boCauHoi.setDapAnC(cursor.getString(4));
                boCauHoi.setDapAnD(cursor.getString(5));
                boCauHoi.setCauTraLoi(cursor.getString(6));
                boCauHoi.setLoai(cursor.getString(7));

                // Thêm vào danh sách.
                boCauHoiList.add(boCauHoi);
            } while (cursor.moveToNext());
        }

        // return note list
        return boCauHoiList;
    }


    public int getBoCauHoiCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_BoCauHoi;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }




    //default dữ liệu

    public void createDefaultBoCauHoi()  {

        int count = this.getBoCauHoiCount();
        if(count ==0 ) {
            BoCauHoi boCauHoi1 = new BoCauHoi("Con gì chân ngắn. Mà lại có màng. Mỏ bẹt màu vàng. Hay kêu cạp cạp?","Con chó","Con chó","Con chó","Con chó","C","DV");
             BoCauHoi boCauHoi2 = new BoCauHoi("Thường nằm đầu hè. Giữ nhà cho chủ. Người lạ nó sủa. Người quen nó mừng. Đây là con gì?","Con chó","Con chó","Con chó","Con chó","C","DV");
            BoCauHoi boCauHoi3 = new BoCauHoi("Vừa bằng quả ổi, khi nổi khi chìm? Là con gì?","Con chó","Con chó","Con chó","Con chó","C","DV");
            this.addBoCauHoi(boCauHoi1);
            this.addBoCauHoi(boCauHoi2);
            this.addBoCauHoi(boCauHoi3);
        }
    }
}
