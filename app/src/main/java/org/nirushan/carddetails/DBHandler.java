package org.nirushan.carddetails;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.nirushan.carddetails.model.Card;
import org.nirushan.carddetails.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "pizza_db";

    private static final String TEXT = " TEXT ";
    private static final String INTEGER = " INTEGER ";

    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_CARD = "card";


    private static final String CARD_ID = "id";
    private static final String CARD_NUMBER = "card_number";
    private static final String EXPIRY_DATE = "expiry_date";
    private static final String CV_CODE = "cv_code";
    private static final String CARD_OWNER = "card_owner";
    private static final String CARD_CUSTOMER_ID = "customer_id";
    private static final String CARD_CREATED = "created";
    private static final String CARD_PAYMENT_METHOD = "payment_method";
    private static final String CARD_SERVICE_CHARGE = "service_charge";
    private static final String CARD_TOTAL = "total";
    private static final String CARD_QTY = "qty";

    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "name";
    private static final String CUSTOMER_PHONE = "phone";
    private static final String CUSTOMER_DESC = "description";
    private static final String CUSTOMER_ADDRESS = "address";
    private static final String CUSTOMER_CREATED = "created";
    private static final String CUSTOMER_TOTAL = "total";

    private static final String CREATE_TABLE_CARD = "CREATE TABLE " + TABLE_CARD + " ( " +
            CARD_ID + INTEGER + "PRIMARY KEY AUTOINCREMENT ," +
            CARD_NUMBER + TEXT + "," +
            EXPIRY_DATE + TEXT + "," +
            CV_CODE + TEXT + "," +
            CARD_OWNER + TEXT + "," +
            CARD_CREATED + TEXT + "," +
            CARD_PAYMENT_METHOD + TEXT + "," +
            CARD_SERVICE_CHARGE + TEXT + "," +
            CARD_TOTAL + TEXT + "," +
            CARD_QTY + TEXT + "," +
            CARD_CUSTOMER_ID + INTEGER  + ")";

    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER + " ( " +
            CUSTOMER_ID + INTEGER + " PRIMARY KEY AUTOINCREMENT ," +
            CUSTOMER_NAME + TEXT + "," +
            CUSTOMER_PHONE + TEXT + "," +
            CUSTOMER_DESC + TEXT + "," +
            CUSTOMER_ADDRESS + TEXT + "," +
            CUSTOMER_TOTAL + TEXT + "," +
            CUSTOMER_CREATED + TEXT  + ")";


    private static final String DELETE_CUSTOMER = "DROP TABLE IF EXISTS " + TABLE_CUSTOMER;
    private static final String DELETE_CARD = "DROP TABLE IF EXISTS " + TABLE_CARD;


    private Context mContext;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_CARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //reinitialize db
        db.execSQL(DELETE_CARD);
        db.execSQL(DELETE_CUSTOMER);

        onCreate(db);
    }

    public void deleteCard(int keyValue) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = CARD_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(keyValue)};
        db.delete(TABLE_CARD, whereClause, whereArgs);
    }

    public boolean addCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXPIRY_DATE, card.getEXPIRY_DATE());
        values.put(CARD_NUMBER, card.getCARD_NUMBER());
        values.put(CV_CODE, card.getCV_CODE());
        values.put(CARD_OWNER, card.getCARD_OWNER());
        values.put(CARD_CUSTOMER_ID, card.getCARD_CUSTOMER_ID());
        values.put(CARD_CREATED, card.getCreated());
        values.put(CARD_PAYMENT_METHOD, card.getPaymentStatus());
        values.put(CARD_SERVICE_CHARGE, card.getServiceCharge());
        values.put(CARD_TOTAL, card.getTotal());
        values.put(CARD_QTY, card.getQty());
        long resultVatCode= db.insert(TABLE_CARD, null, values);
        return resultVatCode > -1;
    }

    public boolean updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXPIRY_DATE, card.getEXPIRY_DATE());
        values.put(CARD_NUMBER, card.getCARD_NUMBER());
        values.put(CV_CODE, card.getCV_CODE());
        values.put(CARD_OWNER, card.getCARD_OWNER());
        values.put(CARD_CUSTOMER_ID, card.getCARD_CUSTOMER_ID());
        values.put(CARD_CREATED, card.getCreated());
        values.put(CARD_PAYMENT_METHOD, card.getPaymentStatus());
        values.put(CARD_SERVICE_CHARGE, card.getServiceCharge());
        values.put(CARD_TOTAL, card.getTotal());
        values.put(CARD_QTY, card.getQty());

        long resultVatCode = db.update(TABLE_CARD, values, CARD_ID + " = " + card.getCardId() , new String[]{});
        return resultVatCode > -1;
    }

    public List<Card> getAllCard() {
        List<Card> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CARD + " order by "+ CARD_ID +" desc";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                Card card = new Card();
                card.setCardId(c.getInt(c.getColumnIndex(CARD_ID)));
                card.setCARD_NUMBER(c.getString(c.getColumnIndex(CARD_NUMBER)));
                card.setEXPIRY_DATE(c.getString(c.getColumnIndex(EXPIRY_DATE)));
                card.setCV_CODE(c.getString(c.getColumnIndex(CV_CODE)));
                card.setCARD_OWNER(c.getString(c.getColumnIndex(CARD_OWNER)));
                card.setCARD_CUSTOMER_ID(c.getString(c.getColumnIndex(CARD_CUSTOMER_ID)));
                card.setCreated(c.getString(c.getColumnIndex(CARD_CREATED)));
                card.setQty(c.getDouble(c.getColumnIndex(CARD_QTY)));
                card.setServiceCharge(c.getDouble(c.getColumnIndex(CARD_SERVICE_CHARGE)));
                card.setTotal(c.getDouble(c.getColumnIndex(CARD_TOTAL)));
                card.setPaymentStatus(c.getString(c.getColumnIndex(CARD_PAYMENT_METHOD)));

                arrayList.add(card);
            }
        }
        assert c != null;
        c.close();
        return arrayList;
    }


    public List<Card> getCardDetailsById(int id) {
        List<Card> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CARD + " where "+ CARD_ID +" = "+id+" ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                Card card = new Card();
                card.setCardId(c.getInt(c.getColumnIndex(CARD_ID)));
                card.setCARD_NUMBER(c.getString(c.getColumnIndex(CARD_NUMBER)));
                card.setEXPIRY_DATE(c.getString(c.getColumnIndex(EXPIRY_DATE)));
                card.setCV_CODE(c.getString(c.getColumnIndex(CV_CODE)));
                card.setCARD_OWNER(c.getString(c.getColumnIndex(CARD_OWNER)));
                card.setCARD_CUSTOMER_ID(c.getString(c.getColumnIndex(CARD_CUSTOMER_ID)));
                card.setCreated(c.getString(c.getColumnIndex(CARD_CREATED)));
                card.setQty(c.getDouble(c.getColumnIndex(CARD_QTY)));
                card.setServiceCharge(c.getDouble(c.getColumnIndex(CARD_SERVICE_CHARGE)));
                card.setTotal(c.getDouble(c.getColumnIndex(CARD_TOTAL)));
                card.setPaymentStatus(c.getString(c.getColumnIndex(CARD_PAYMENT_METHOD)));

                arrayList.add(card);
            }
        }
        assert c != null;
        c.close();
        return arrayList;
    }

    public List<Customer> getAllCustomerById(int customerId) {
        List<Customer> students = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER + " where "+CUSTOMER_ID+" = "+customerId+" ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                Customer customer = new Customer();
                customer.setCustomerId(c.getInt(c.getColumnIndex(CUSTOMER_ID)));
                customer.setCustomerName(c.getString(c.getColumnIndex(CUSTOMER_NAME)));
                customer.setCustomerPhone(c.getString(c.getColumnIndex(CUSTOMER_PHONE)));
                customer.setCustomerAddress(c.getString(c.getColumnIndex(CUSTOMER_ADDRESS)));
                customer.setCustomerCreated(c.getString(c.getColumnIndex(CUSTOMER_CREATED)));
                customer.setCustomerDesc(c.getString(c.getColumnIndex(CUSTOMER_DESC)));
                customer.setCustomerTotal(c.getString(c.getColumnIndex(CUSTOMER_TOTAL)));
                students.add(customer);
            }
        }
        assert c != null;
        c.close();
        return students;
    }


    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> students = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                Customer customer = new Customer();
                customer.setCustomerId(c.getInt(c.getColumnIndex(CUSTOMER_ID)));
                customer.setCustomerName(c.getString(c.getColumnIndex(CUSTOMER_NAME)));
                customer.setCustomerPhone(c.getString(c.getColumnIndex(CUSTOMER_PHONE)));
                customer.setCustomerAddress(c.getString(c.getColumnIndex(CUSTOMER_ADDRESS)));
                customer.setCustomerCreated(c.getString(c.getColumnIndex(CUSTOMER_CREATED)));
                customer.setCustomerDesc(c.getString(c.getColumnIndex(CUSTOMER_DESC)));
                customer.setCustomerTotal(c.getString(c.getColumnIndex(CUSTOMER_TOTAL)));
                students.add(customer);
            }
        }
        assert c != null;
        c.close();
        return students;
    }



    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public void closeDB() {

        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null && db.isOpen())
            db.close();
    }



}
