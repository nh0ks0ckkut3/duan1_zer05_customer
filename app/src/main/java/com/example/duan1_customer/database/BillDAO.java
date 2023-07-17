package com.example.duan1_customer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1_customer.model.Bill;

import java.util.ArrayList;

public class BillDAO {
    private Helper helper;

    public BillDAO(Context context){
        helper = new Helper(context);
    }

    public ArrayList<Bill> getListHistory(){
        ArrayList<Bill> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase =helper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,userNameEmployee,idService,idProduct,time,totalPrice,phoneNumberCustomer  FROM bill WHERE phoneNumberCustomer like ? and status like ?",new String[]{"0337295209","da thanh toan"});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                int id_bill = cursor.getInt(0);
                Cursor cursorEmployee = sqLiteDatabase.rawQuery("select name from employee where userName like ?", new String[]{cursor.getString(1)});
                cursorEmployee.moveToFirst();
                Cursor cursorService = sqLiteDatabase.rawQuery("select name from service where id = ?",new String[]{String.valueOf(cursor.getInt(2))});
                Cursor cursorProduct = sqLiteDatabase.rawQuery("select name from product where id = ?", new String[]{String.valueOf(cursor.getInt(3))});
                cursorService.moveToFirst();
                cursorProduct.moveToFirst();
                list.add(new Bill(id_bill,
                                cursor.getString(6),
                        cursorEmployee.getString(0),
                        cursorService.getString(0),
                        cursorProduct.getString(0),
                                cursor.getString(4),
                                cursor.getInt(5)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean addBill(Bill bill){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("phoneNumberCustomer",bill.getPhoneNumberCustomer());
        contentValues.put("userNameEmployee",bill.getUserNameEmployee());
        contentValues.put("idService",bill.getIdService());
        contentValues.put("idProduct",bill.getIdProduct());
        contentValues.put("time",bill.getTime());
        contentValues.put("status",bill.getStatus());
        contentValues.put("totalPrice",bill.getTotalPrice());

        long check =sqLiteDatabase.insert("bill", null, contentValues);
        return (!(check == -1));
    }

    public boolean editBill(int idBillEdit, Bill bill){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("phoneNumberCustomer",bill.getPhoneNumberCustomer());
        contentValues.put("userNameEmployee",bill.getUserNameEmployee());
        contentValues.put("idService",bill.getIdService());
        contentValues.put("idProduct",bill.getIdProduct());
        contentValues.put("time",bill.getTime());
        contentValues.put("status",bill.getStatus());
        contentValues.put("totalPrice",bill.getTotalPrice());
        long check = sqLiteDatabase.update("bill", contentValues, "id= ?",new String[]{String.valueOf(idBillEdit)});
        return (!(check == -1));
    }

    public boolean deleteBill(int idBill){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        long check = sqLiteDatabase.delete("bill", "id= ?",new String[]{String.valueOf(idBill)});
        return (!(check == -1));
    }
}
