package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Labtop on 20/02/18.
 */

public class InventoryContract {

    public static final String CONTENT_AUTHORITY =  "com.example.anrdoid.inventoryappstage2";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY = "product";

    //constructor
    public InventoryContract() {
    }

    public final static class InventoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +  PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        //Name of table DB
        public final static String TABLE_NAME = "product";

        //Column of table
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "product_name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        // SUPPLIER_NAME LIST VALUES
        public final static int SUPPLIER_UNKNOWN = 0;
        public final static int SUPPLIER_AMAZON = 1;
        public final static int SUPPLIER_JARIRR = 2;
        public final static int SUPPLIER_OBEIKAN = 3;

        public static boolean isValidSupplierName(int suppliername) {
            if (suppliername == SUPPLIER_UNKNOWN || suppliername == SUPPLIER_AMAZON || suppliername == SUPPLIER_JARIRR || suppliername == SUPPLIER_OBEIKAN) {
                return true;
            }
            return false;
        }
    }
}
