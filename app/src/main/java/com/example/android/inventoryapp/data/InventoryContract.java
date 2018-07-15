package com.example.android.inventoryapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */

    public static final String PATH_INVENTORY = "inventory";

    /**
     * Inner class that defines constant values for the inventory database table.
     * Each entry in the table represents a single product.
     */
    public static final class InventoryEntry implements BaseColumns {

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);


        /**
         * Name of database table for products
         */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID number for the product.
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME = "product_name";

        /**
         * price of the product.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "price";

        /**
         * Available quantity of the product.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";

        /**
         * Product's supplier name.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";

        /**
         * Product's supplier phone number.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE = "supplier_phone";

    }
}
