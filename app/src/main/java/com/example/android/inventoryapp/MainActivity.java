package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;
import com.example.android.inventoryapp.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper inventoryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        inventoryDbHelper = new InventoryDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_insert) {
            insertDummyData();
            displayDatabaseInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertDummyData() {

        // Create and/or open a database to write onto it
        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Headset");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, 61);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 10);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Razer");
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE, "+302593485716");
        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = inventoryDbHelper.getReadableDatabase();

        String[] projection = null;
        String selection = null;
        String[] selectionArgs = new String[0] ;

        Cursor cursor = db.query(InventoryEntry.TABLE_NAME, projection,
                selection, selectionArgs,
                null, null, null);

        try {

            TextView displayView =  findViewById(R.id.content);
            displayView.setText("Number of rows in inventory database table: " + cursor.getCount() + " products. \n\n");
            displayView.append("\n" + InventoryEntry._ID + "-"
                    + InventoryEntry.COLUMN_PRODUCT_NAME + "-"
                    + InventoryEntry.COLUMN_PRODUCT_PRICE + "-"
                    + InventoryEntry.COLUMN_PRODUCT_QUANTITY + "-"
                    + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME + "-"
                    + InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE + "\n\n");

            while (cursor.moveToNext()){
                int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
                int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
                int productPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
                int productQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
                int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
                int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentProductPrice = cursor.getInt(productPriceColumnIndex);
                int currentProductQuantity = cursor.getInt(productQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                displayView.append("\n" + currentID + "-" + currentProductName + "-" + String.valueOf(currentProductPrice) + "-" + String.valueOf(currentProductQuantity) + "-" + currentSupplierName + "-" + currentSupplierPhone);
            }



        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
