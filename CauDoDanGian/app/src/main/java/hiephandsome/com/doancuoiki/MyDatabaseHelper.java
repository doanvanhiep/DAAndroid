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
    private static final String DATABASE_NAME = "DBCauHoiHoanChinh";


    // Tên bảng: Note.
    private static final String TABLE_BoCauHoi = "BoCauHoiHoanChinh";

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
    public ArrayList<BoCauHoi> getAllBoCauHoi(String loai) {
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
            //Động vật
            BoCauHoi boCauHoi1 = new BoCauHoi("Con gì chân ngắn. Mà lại có màng. Mỏ bẹt màu vàng. Hay kêu cạp cạp?","Con chó","Con vịt","Con gà","Con ngỗng","B","DV");
             BoCauHoi boCauHoi2 = new BoCauHoi("Thường nằm đầu hè. Giữ nhà cho chủ. Người lạ nó sủa. Người quen nó mừng. Đây là con gì?","Con chó","Con khỉ","Con chuột","Con chim","A","DV");
            BoCauHoi boCauHoi3 = new BoCauHoi("Vừa bằng quả ổi, khi nổi khi chìm? Là con gì?","Con lương","Con rắn","Con chó","Con ốc","D","DV");
            BoCauHoi boCauHoi4 = new BoCauHoi("Vừa bằng lục lạc, trong nạc ngoài xương?","Trứng vịt","Trứng ngang","Trứng gà","Trứng chim","C","DV");
            BoCauHoi boCauHoi5 = new BoCauHoi("Vừa bằng que diêm, khi chìm khi nổi?","Con rắn","Con đĩa","Con lương","Con cóc","B","DV");
            BoCauHoi boCauHoi6 = new BoCauHoi("Vừa bằng hột quít, dưới đất ngậm châu? Là con gì?","Con đom đóm","Con cá","Con lương","Con khỉ","A","DV");
            BoCauHoi boCauHoi7 = new BoCauHoi("Con gì? Một lòng khuya sớm chuyên cần. Trách người vô nghĩa, sao chê ngu đần?","Con đom voi","Con bò","Con gà","Con khỉ","B","DV");
            BoCauHoi boCauHoi8 = new BoCauHoi("Con gì: Bốn người giẫm đất, một người phất cờ. Hai người lẳng lơ, hai người quạt mát. Trách người vô nghĩa, sao chê ngu đần?","Con đom voi","Con bò","Con gà","Con khỉ","A","DV");
            BoCauHoi boCauHoi9 = new BoCauHoi("Con gì? Cái mỏ xinh xinh. Hai chân tí xíu. Lông vàng mát dịu \"\"Chiếp! Chiếp!\" suốt ngày?","Con gà con","Con gà trống","Con gà mẹ","Con vịt con","A","DV");
            BoCauHoi boCauHoi10 = new BoCauHoi("Con gì mào đỏ, gá ò ó o ..., từ sáng tinh mơ, gọi người thức dậy?","Con gà con","Con gà trống","Con gà mẹ","Con vịt con","B","DV");
            BoCauHoi boCauHoi11 = new BoCauHoi("Con gì? Thân em nửa chuột, nửa chim. Ngày treo chân ngủ, tối tìm mồi bay. Trời cho tai mắt giỏi thay. Tối đen tối mịt cứ bay vù vù?","Con gà rừng","Con dơi","Con chim","Con đại bàng","B","DV");
            BoCauHoi boCauHoi12 = new BoCauHoi("Con gì đôi cánh mỏng tanh, bay cao bay thấp báo rằng nắng mưa?","Con gà rừng","Con dơi","Con chim","Con chuồn chuồn","D","DV");
            BoCauHoi boCauHoi13 = new BoCauHoi("Con gì ăn cỏ, đầu có 2 sừng, lỗ mũi buộc thừng, kéo cày rất giỏi?","Con bò","Con dê","Con chồn","Con trâu","D","DV");
            BoCauHoi boCauHoi14 = new BoCauHoi("Con gì? Bốn cột tứ trụ. Người ngự lên trên. Gươm bac hai bên. Chầu vua thượng đế?","Con bò","Con voi","Con chồn","Con trâu","B","DV");
            BoCauHoi boCauHoi15 = new BoCauHoi("Con gì ăn no, bụng to mắt híp, mồm kêu ụt it, nằm thở phì phò?","Con heo","Con khỉ","Con mèo","Con chó","B","DV");

//Hoa quả
            BoCauHoi boCauHoi16 = new BoCauHoi("Da cóc mà bọc bột lọc, Bột lọc mà bọc hòn than?","Qủa na","Quả nhãn","Quả dưa hấu","Quả chôm chô","B","HQ");
            BoCauHoi boCauHoi17 = new BoCauHoi("Hoa trắng, lòng hoa vàng ong. Vào chùa thường gặp bát ngát trong sân chùa?","Hoa đại","Hoa tiểu","Hoa vạn thọ","Hoa hồng","A","HQ");
            BoCauHoi boCauHoi18 = new BoCauHoi("Hoa gì khi nở trên cành. Già chui xuống đất để dành nuôi ta?","Hoa dại","Hoa lạc","Hoa vạn thọ","Hoa hồng","B","HQ");
            BoCauHoi boCauHoi19 = new BoCauHoi("Hoa gì chỉ nở mùa hè. Dang tay đón bạn ve về đỏ cây. Ở trên mái phố suốt ngày. Không ai cắm lọ hoa này để chơi?","Hoa dại","Hoa lạc","Hoa vạn thọ","Hoa phượng","D","HQ");
            BoCauHoi boCauHoi20 = new BoCauHoi("Hoa gì e thẹn bên đường?","Hoa dại","Hoa lạc","Hoa trinh nữ","Hoa phượng","C","HQ");
            BoCauHoi boCauHoi21 = new BoCauHoi("Tắm dưới hồ rất dịu dàng. Mà sao mang tiếng đùng đoàng lạ thay","Hoa súng","Hoa sen","Hoa trinh nữ","Hoa phượng","B","HQ");
            BoCauHoi boCauHoi22 = new BoCauHoi("Đỏ bừng khắp cả mình cây. Khi quả chín vỡ, bông bay khắp vùng?","Hoa súng","Hoa sen","Hoa trinh nữ","Hoa gạo","D","HQ");
            BoCauHoi boCauHoi23 = new BoCauHoi("Giữa lưng trời có vũng nước trong. Cá lòng tong lội mãi không tới?","Qủa na","Quả nhãn","Quả dừa","Quả chôm chô","C","HQ");
            BoCauHoi boCauHoi24 = new BoCauHoi("Hoa gì quả quyện với trầu nên duyên?","Hoa súng","Hoa sen","Hoa cau","Hoa gạo","C","HQ");
            BoCauHoi boCauHoi25 = new BoCauHoi("Da cóc mà bọc bột lọc. Bột lọc mà bọc hòn son?","Qủa vải","Quả nhãn","Quả dừa","Quả chôm chô","A","HQ");
            BoCauHoi boCauHoi26 = new BoCauHoi("Ruột chấm vừng đen. Ăn vào mà xem. Vừa bổ vừa mát?","Qủa thanh long","Quả nhãn","Quả dừa","Quả chôm chô","A","HQ");
            BoCauHoi boCauHoi27 = new BoCauHoi("Hoa gì vạn tuổi lừng danh","Hoa súng","Hoa sen","Hoa vạn thọ","Hoa gạo","C","HQ");
            BoCauHoi boCauHoi28 = new BoCauHoi("Hoa gì báo hiệu đến giờ thu sang?","Hoa cúc","Hoa sen","Hoa vạn thọ","Hoa gạo","A","HQ");
            BoCauHoi boCauHoi29 = new BoCauHoi("Hoa gì lắm sắc không hương Tên như nhân tạo trồng ngoài cổng ai?","Hoa cúc","Hoa giấy","Hoa vạn thọ","Hoa gạo","B","HQ");
            BoCauHoi boCauHoi30 = new BoCauHoi("Lá dài như đòn gánh vươn. Mà hoa rủ tím như chùm pháo bông?","Hoa cúc","Hoa giấy","Hoa vạn thọ","Hoa nắng","D","HQ");

            //Ngày tết

            BoCauHoi boCauHoi31 = new BoCauHoi("Vị khách đầu tiên đến nhà chúc tết được gọi là?","Người xông đất","Người mở hàng","Người đẹp trai","Người đẹp gái","A","NT");
            BoCauHoi boCauHoi32 = new BoCauHoi("Khoảng khắc chuyển tiếp từ năm này sang năm khác?","Trung thu","Tết tây","Giao thừa","Rằm tháng giêng","C","NT");
            BoCauHoi boCauHoi33 = new BoCauHoi("Đây là 1 hoạt động truyền thống mang lại sự may mắn của 2 con vật truyền thuyết biểu tượng của mùa xuân do các vũ công điều khiển","Múa lửa","Múa lân","Múa rìu","Múa võ","B","NT");
            BoCauHoi boCauHoi34 = new BoCauHoi("Ngày tết các thầy đồ thường làm gì?","Coi bói","Viết câu đối","Lì xì","Nhận lì xì","B","NT");
            BoCauHoi boCauHoi35 = new BoCauHoi("Một phong tục tập quán từ lâu đời của Việt Nam vào ngày tết?","Chúc tết","Đi du lịch","Đi phượt","Đi làm","A","NT");
            BoCauHoi boCauHoi36 = new BoCauHoi("Tết Nguyên Đán còn có tên gọi khác?","Tết ta","Tết tây","Tết trung thu","Tết nguyên tiêu","A","NT");
            BoCauHoi boCauHoi37 = new BoCauHoi("Nghi lễ diễn ra vào ngày 23 tháng chạp âm lịch gọi là gì?","Giao thừa","Tất niên","trung thu","Cúng đưa ông táo","B","NT");
            BoCauHoi boCauHoi38 = new BoCauHoi("Một loại thức ăn ngọt không thể thiếu vào ngày tết có rất nhiều hương vị?","Bánh","Nước ngọt","Mức","Kẹo","C","NT");
            BoCauHoi boCauHoi39 = new BoCauHoi("Tết có mâm ... quả","Nhất","Tứ","Nhị","Ngũ","D","NT");
            BoCauHoi boCauHoi40 = new BoCauHoi("Hoa tượng trưng cho mùa xuân ở miền Trung & Nam?","Mai","Đào","Hồng","Cúc","A","NT");
            BoCauHoi boCauHoi41 = new BoCauHoi("Hoa tượng trưng cho mùa xuân ở miền Bắc?","Mai","Đào","Hồng","Cúc","B","NT");
            BoCauHoi boCauHoi42 = new BoCauHoi("Hoa tượng trưng cho mùa xuân ở miền Bắc?","Mai","Đào","Hồng","Cúc","B","NT");
            BoCauHoi boCauHoi43 = new BoCauHoi("Bánh trời là gì ?","Bánh trưng","Bánh giày","Bánh kẹo","Bánh gạo","A","NT");
            BoCauHoi boCauHoi44 = new BoCauHoi("Bánh đất là gì ?","Bánh trưng","Bánh giày","Bánh kẹo","Bánh gạo","B","NT");
            BoCauHoi boCauHoi45 = new BoCauHoi("Tên ba vị thần tượng trưng cho sự giàu sang , hạnh phúc & sức khỏe?","Thọ","Lộc","Phúc","Phúc Lộc Thọ","D","NT");





            this.addBoCauHoi(boCauHoi1);
            this.addBoCauHoi(boCauHoi2);
            this.addBoCauHoi(boCauHoi3);
            this.addBoCauHoi(boCauHoi4);
            this.addBoCauHoi(boCauHoi5);
            this.addBoCauHoi(boCauHoi6);
            this.addBoCauHoi(boCauHoi7);
            this.addBoCauHoi(boCauHoi8);
            this.addBoCauHoi(boCauHoi9);
            this.addBoCauHoi(boCauHoi11);
            this.addBoCauHoi(boCauHoi12);
            this.addBoCauHoi(boCauHoi13);
            this.addBoCauHoi(boCauHoi14);
            this.addBoCauHoi(boCauHoi15);
            this.addBoCauHoi(boCauHoi16);
            this.addBoCauHoi(boCauHoi17);
            this.addBoCauHoi(boCauHoi18);
            this.addBoCauHoi(boCauHoi19);
            this.addBoCauHoi(boCauHoi20);
            this.addBoCauHoi(boCauHoi21);
            this.addBoCauHoi(boCauHoi22);
            this.addBoCauHoi(boCauHoi23);
            this.addBoCauHoi(boCauHoi24);
            this.addBoCauHoi(boCauHoi25);
            this.addBoCauHoi(boCauHoi26);
            this.addBoCauHoi(boCauHoi27);
            this.addBoCauHoi(boCauHoi28);
            this.addBoCauHoi(boCauHoi29);
            this.addBoCauHoi(boCauHoi30);
            this.addBoCauHoi(boCauHoi31);
            this.addBoCauHoi(boCauHoi32);
            this.addBoCauHoi(boCauHoi33);
            this.addBoCauHoi(boCauHoi34);
            this.addBoCauHoi(boCauHoi35);
            this.addBoCauHoi(boCauHoi36);
            this.addBoCauHoi(boCauHoi37);
            this.addBoCauHoi(boCauHoi38);
            this.addBoCauHoi(boCauHoi39);
            this.addBoCauHoi(boCauHoi40);
            this.addBoCauHoi(boCauHoi41);
            this.addBoCauHoi(boCauHoi42);
            this.addBoCauHoi(boCauHoi43);
            this.addBoCauHoi(boCauHoi44);
            this.addBoCauHoi(boCauHoi45);
        }
    }

}
