package com.example.vyapaarone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VyapaarOne.db";
    private static final int DATABASE_VERSION = 24;
    // ==========================
// USER TABLE
// ==========================
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USER_LOGIN_ID = "user_login_id";
    private static final String COLUMN_USER_SHOP = "shop_name";
    private static final String COLUMN_USER_MOBILE = "mobile";
    private static final String COLUMN_USER_GST = "gstin";
    private static final String COLUMN_USER_PASSWORD = "password";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_SELLING_PRICE = "selling_price";
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_CUSTOMER_PHONE = "customer_phone";
    private static final String COLUMN_CUSTOMER_EMAIL = "customer_email";
    private static final String COLUMN_CUSTOMER_ADDRESS = "customer_address";
    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_ORDER_USER_ID = "user_id";
    private static final String COLUMN_ORDER_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_ORDER_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_ORDER_DATE = "order_date";
    private static final String COLUMN_ORDER_TOTAL = "total_amount";
    private static final String COLUMN_ORDER_STATUS = "order_status";
    public static final String TABLE_ORDER_ITEMS = "order_items";
    public static final String COLUMN_ORDERITEM_USER_ID = "user_id";
    // ==========================
// PAYMENT TABLE
// ==========================
    private static final String TABLE_PAYMENTS = "payments";
    private static final String COLUMN_PAYMENT_ID = "payment_id";
    private static final String COLUMN_PAYMENT_ORDER_ID = "order_id";
    private static final String COLUMN_PAYMENT_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_PAID_AMOUNT = "paid_amount";
    private static final String COLUMN_PENDING_AMOUNT = "pending_amount";
    private static final String COLUMN_PAYMENT_DATE = "payment_date";
    private static final String COLUMN_PAYMENT_METHOD = "payment_method";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "product_name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_PRICE_PER_UNIT = "price_per_unit";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_MIN_STOCK = "min_stock";
    public static final String COLUMN_MAX_STOCK = "max_stock";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String TABLE_BUSINESS = "business_profile";
    public static final String COLUMN_PROFILE_IMAGE = "profile_image";
    public static final String COLUMN_BUSINESS_ID = "id";
    public static final String COLUMN_BUSINESS_NAME = "business_name";
    public static final String COLUMN_OWNER_NAME = "owner_name";
    public static final String COLUMN_GST_NUMBER = "gst_number";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";
    // ==========================
// INVOICE TABLE
// ==========================
    private static final String TABLE_INVOICES = "invoices";
    private static final String COLUMN_INVOICE_ID = "invoice_id";
    private static final String COLUMN_INVOICE_NUMBER = "invoice_number";
    private static final String COLUMN_INVOICE_ORDER_ID = "order_id";
    private static final String COLUMN_INVOICE_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_INVOICE_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_INVOICE_DATE = "invoice_date";
    private static final String COLUMN_SUBTOTAL = "subtotal";
    private static final String COLUMN_GST_PERCENT = "gst_percent";
    private static final String COLUMN_GST_AMOUNT = "gst_amount";
    private static final String COLUMN_GRAND_TOTAL = "grand_total";
    private static final String COLUMN_INVOICE_STATUS = "invoice_status";
    private static final String TABLE_LEDGER_ENTRIES = "ledger_entries";

    private static final String COLUMN_LEDGER_ID = "ledger_id";
    private static final String COLUMN_LEDGER_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_LEDGER_TYPE = "type";
    private static final String COLUMN_LEDGER_TITLE = "title";
    private static final String COLUMN_LEDGER_AMOUNT = "amount";
    private static final String COLUMN_LEDGER_DATE = "entry_date";
    // ==========================
// SUPPLIER TABLE
// ==========================

    private static final String TABLE_SUPPLIERS = "suppliers";
    public static final String COLUMN_SUPPLIER_USER_ID = "user_id";
    private static final String COLUMN_SUPPLIER_ID = "supplier_id";
    private static final String COLUMN_SUPPLIER_NAME = "supplier_name";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
    private static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
    private static final String COLUMN_SUPPLIER_GST = "supplier_gst";
    private static final String COLUMN_SUPPLIER_ADDRESS = "supplier_address";
    private static final String COLUMN_SUPPLIER_CITY = "supplier_city";
    private static final String COLUMN_SUPPLIER_STATE = "supplier_state";
    private static final String COLUMN_SUPPLIER_PINCODE = "supplier_pincode";
    private static final String COLUMN_OPENING_BALANCE = "opening_balance";
    private static final String COLUMN_BALANCE_TYPE = "balance_type";
    private static final String COLUMN_SUPPLIER_NOTES = "notes";

    private static final String TABLE_SECURITY = "security_settings";

    private static final String COLUMN_SECURITY_ID = "id";

    private static final String COLUMN_APP_LOCK = "app_lock";

    private static final String COLUMN_FINGERPRINT = "fingerprint";

    private static final String COLUMN_AUTO_LOGOUT = "auto_logout";

    private static final String COLUMN_PIN = "pin";
    // ==============================
// PURCHASE TABLE
// ==============================

    public static final String TABLE_PURCHASE = "purchase";
    public static final String COLUMN_PURCHASE_USER_ID = "user_id";
    public static final String COLUMN_PURCHASE_ID = "purchase_id";
    public static final String COLUMN_PURCHASE_SUPPLIER_ID = "supplier_id";
    public static final String COLUMN_PURCHASE_PRODUCT_ID = "product_id";

    public static final String COLUMN_PURCHASE_QUANTITY = "quantity";
    public static final String COLUMN_PURCHASE_PRICE = "purchase_price";
    public static final String COLUMN_PURCHASE_TOTAL = "total_amount";

    public static final String COLUMN_PURCHASE_DATE = "purchase_date";
    public static final String COLUMN_PURCHASE_NOTES = "notes";
    // ==============================
// PURCHASE RETURN TABLE
// ==============================

    private static final String TABLE_PURCHASE_RETURN = "purchase_return";
    private static final String COLUMN_PURCHASE_RETURN_USER_ID = "user_id";
    private static final String COLUMN_RETURN_ID = "return_id";
    private static final String COLUMN_RETURN_PURCHASE_ID = "purchase_id";
    private static final String COLUMN_RETURN_SUPPLIER_ID = "supplier_id";
    private static final String COLUMN_RETURN_PRODUCT_ID = "product_id";

    private static final String COLUMN_RETURN_QUANTITY = "return_quantity";
    private static final String COLUMN_RETURN_AMOUNT = "return_amount";

    private static final String COLUMN_RETURN_DATE = "return_date";
    private static final String COLUMN_RETURN_REASON = "return_reason";
    private static final String TABLE_STOCK_MOVEMENT = "stock_movement";

    private static final String COLUMN_MOVEMENT_ID = "movement_id";

    private static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_USER_ID = "user_id";
    private static final String COLUMN_MOVEMENT_TYPE = "movement_type";
    private static final String COLUMN_MOVEMENT_QTY = "movement_qty";
    private static final String COLUMN_STOCK_AFTER = "stock_after";
    private static final String COLUMN_MOVEMENT_DATE = "movement_date";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE =
                "CREATE TABLE " + TABLE_USERS + "("
                        + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USERNAME + " TEXT UNIQUE,"
                        + COLUMN_USER_LOGIN_ID + " TEXT,"
                        + COLUMN_USER_SHOP + " TEXT,"
                        + COLUMN_USER_MOBILE + " TEXT,"
                        + COLUMN_USER_GST + " TEXT,"
                        + COLUMN_USER_PASSWORD + " TEXT"
                        + ")";

        String CREATE_PRODUCTS_TABLE =
                "CREATE TABLE " + TABLE_PRODUCTS + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PRODUCT_USER_ID + " INTEGER,"
                        + COLUMN_NAME + " TEXT,"
                        + COLUMN_QUANTITY + " REAL,"
                        + COLUMN_UNIT + " TEXT,"
                        + COLUMN_PRICE_PER_UNIT + " REAL,"
                        + COLUMN_SELLING_PRICE + " REAL,"
                        + COLUMN_TOTAL_PRICE + " REAL,"
                        + COLUMN_MIN_STOCK + " REAL DEFAULT 0,"
                        + COLUMN_MAX_STOCK + " REAL DEFAULT 0"
                        + ")";

        String CREATE_CUSTOMER_TABLE =
                "CREATE TABLE " + TABLE_CUSTOMERS + "("

                        + COLUMN_CUSTOMER_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                        + COLUMN_CUSTOMER_NAME
                        + " TEXT,"

                        + COLUMN_CUSTOMER_PHONE
                        + " TEXT,"

                        + COLUMN_CUSTOMER_EMAIL
                        + " TEXT,"

                        + COLUMN_CUSTOMER_ADDRESS
                        + " TEXT"
                        + ")";
        String CREATE_ORDER_TABLE =
                "CREATE TABLE " + TABLE_ORDERS + "("
                        + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ORDER_USER_ID + " INTEGER,"
                        + COLUMN_ORDER_CUSTOMER_ID + " INTEGER,"
                        + COLUMN_ORDER_CUSTOMER_NAME + " TEXT,"
                        + COLUMN_ORDER_DATE + " TEXT,"
                        + COLUMN_ORDER_TOTAL + " REAL,"
                        + COLUMN_ORDER_STATUS + " TEXT"
                        + ")";
        String CREATE_PAYMENT_TABLE =
                "CREATE TABLE " + TABLE_PAYMENTS + "("
                        + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PAYMENT_ORDER_ID + " INTEGER,"
                        + COLUMN_PAYMENT_CUSTOMER_ID + " INTEGER,"
                        + COLUMN_PAID_AMOUNT + " REAL,"
                        + COLUMN_PENDING_AMOUNT + " REAL,"
                        + COLUMN_PAYMENT_DATE + " TEXT,"
                        + COLUMN_PAYMENT_METHOD + " TEXT"
                        + ")";
        String CREATE_BUSINESS_TABLE =
                "CREATE TABLE " + TABLE_BUSINESS + " ("
                        + COLUMN_BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_BUSINESS_NAME + " TEXT,"
                        + COLUMN_OWNER_NAME + " TEXT,"
                        + COLUMN_GST_NUMBER + " TEXT,"
                        + COLUMN_PHONE + " TEXT,"
                        + COLUMN_EMAIL + " TEXT,"
                        + COLUMN_ADDRESS + " TEXT,"
                        + COLUMN_PROFILE_IMAGE + " TEXT"
                        + ")";
        String CREATE_INVOICE_TABLE =
                "CREATE TABLE " + TABLE_INVOICES + "("
                        + COLUMN_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_INVOICE_NUMBER + " TEXT,"
                        + COLUMN_INVOICE_ORDER_ID + " INTEGER,"
                        + COLUMN_INVOICE_CUSTOMER_ID + " INTEGER,"
                        + COLUMN_INVOICE_CUSTOMER_NAME + " TEXT,"
                        + COLUMN_INVOICE_DATE + " TEXT,"
                        + COLUMN_SUBTOTAL + " REAL,"
                        + COLUMN_GST_PERCENT + " REAL,"
                        + COLUMN_GST_AMOUNT + " REAL,"
                        + COLUMN_GRAND_TOTAL + " REAL"
                        + ", " + COLUMN_INVOICE_STATUS + " TEXT DEFAULT 'ACTIVE'"
                        + ")";
        String CREATE_LEDGER_TABLE =
                "CREATE TABLE " + TABLE_LEDGER_ENTRIES + "("
                        + COLUMN_LEDGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_LEDGER_CUSTOMER_ID + " INTEGER,"
                        + COLUMN_LEDGER_TYPE + " TEXT,"
                        + COLUMN_LEDGER_TITLE + " TEXT,"
                        + COLUMN_LEDGER_AMOUNT + " REAL,"
                        + COLUMN_LEDGER_DATE + " TEXT"
                        + ")";
        String CREATE_SUPPLIER_TABLE =
                "CREATE TABLE " + TABLE_SUPPLIERS + "("
                        + COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_SUPPLIER_USER_ID + " INTEGER,"
                        + COLUMN_SUPPLIER_NAME + " TEXT,"
                        + COLUMN_COMPANY_NAME + " TEXT,"
                        + COLUMN_SUPPLIER_PHONE + " TEXT,"
                        + COLUMN_SUPPLIER_EMAIL + " TEXT,"
                        + COLUMN_SUPPLIER_GST + " TEXT,"
                        + COLUMN_SUPPLIER_ADDRESS + " TEXT,"
                        + COLUMN_SUPPLIER_CITY + " TEXT,"
                        + COLUMN_SUPPLIER_STATE + " TEXT,"
                        + COLUMN_SUPPLIER_PINCODE + " TEXT,"
                        + COLUMN_OPENING_BALANCE + " REAL,"
                        + COLUMN_BALANCE_TYPE + " TEXT,"
                        + COLUMN_SUPPLIER_NOTES + " TEXT"
                        + ")";
        String CREATE_PURCHASE_TABLE =
                "CREATE TABLE " + TABLE_PURCHASE + "("

                        + COLUMN_PURCHASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PURCHASE_USER_ID + " INTEGER,"
                        + COLUMN_PURCHASE_SUPPLIER_ID + " INTEGER,"
                        + COLUMN_PURCHASE_PRODUCT_ID + " INTEGER,"
                        + COLUMN_PURCHASE_QUANTITY + " REAL,"
                        + COLUMN_PURCHASE_PRICE + " REAL,"
                        + COLUMN_PURCHASE_TOTAL + " REAL,"
                        + COLUMN_PURCHASE_DATE + " TEXT,"
                        + COLUMN_PURCHASE_NOTES + " TEXT"
                        + ")";
        String CREATE_PURCHASE_RETURN_TABLE =
                "CREATE TABLE " + TABLE_PURCHASE_RETURN + "("
                        + COLUMN_RETURN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PURCHASE_RETURN_USER_ID + " INTEGER,"
                        + COLUMN_RETURN_PURCHASE_ID + " INTEGER,"
                        + COLUMN_RETURN_SUPPLIER_ID + " INTEGER,"
                        + COLUMN_RETURN_PRODUCT_ID + " INTEGER,"
                        + COLUMN_RETURN_QUANTITY + " REAL,"
                        + COLUMN_RETURN_AMOUNT + " REAL,"
                        + COLUMN_RETURN_DATE + " TEXT,"
                        + COLUMN_RETURN_REASON + " TEXT"
                        + ")";
        String CREATE_ORDER_ITEMS_TABLE =
                "CREATE TABLE " + TABLE_ORDER_ITEMS + "("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ORDERITEM_USER_ID + " INTEGER,"
                        + COLUMN_ORDER_ID + " INTEGER,"
                        + COLUMN_PRODUCT_ID + " INTEGER,"
                        + COLUMN_NAME + " TEXT,"
                        + "quantity REAL,"
                        + "price REAL"
                        + ")";
        String CREATE_TABLE_STOCK_MOVEMENT =
                "CREATE TABLE " + TABLE_STOCK_MOVEMENT + " (" +

                        COLUMN_MOVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        COLUMN_PRODUCT_ID + " INTEGER, " +

                        COLUMN_PRODUCT_NAME + " TEXT, " +

                        COLUMN_MOVEMENT_TYPE + " TEXT, " +

                        COLUMN_MOVEMENT_QTY + " REAL, " +

                        COLUMN_STOCK_AFTER + " REAL, " +

                        COLUMN_MOVEMENT_DATE + " TEXT)";
        String CREATE_SECURITY_TABLE =
                "CREATE TABLE " + TABLE_SECURITY + "("
                        + COLUMN_SECURITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_APP_LOCK + " INTEGER,"
                        + COLUMN_FINGERPRINT + " INTEGER,"
                        + COLUMN_AUTO_LOGOUT + " INTEGER,"
                        + COLUMN_PIN + " TEXT"
                        + ")";
        db.execSQL(CREATE_SECURITY_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ORDER_ITEMS_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_PAYMENT_TABLE);
        db.execSQL(CREATE_BUSINESS_TABLE);
        db.execSQL(CREATE_INVOICE_TABLE);
        db.execSQL(CREATE_LEDGER_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_SUPPLIER_TABLE);
        db.execSQL(CREATE_PURCHASE_TABLE);
        db.execSQL(CREATE_PURCHASE_RETURN_TABLE);
        db.execSQL(CREATE_TABLE_STOCK_MOVEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 3) {

            try {
                db.execSQL("ALTER TABLE " + TABLE_PRODUCTS +
                        " ADD COLUMN " + COLUMN_MIN_STOCK +
                        " REAL DEFAULT 0");
            } catch (Exception ignored) {
            }

            try {
                db.execSQL("ALTER TABLE " + TABLE_PRODUCTS +
                        " ADD COLUMN " + COLUMN_MAX_STOCK +
                        " REAL DEFAULT 0");
            } catch (Exception ignored) {
            }
        }

        // ===========================
        // VERSION 4 - CUSTOMER TABLE
        // ===========================

        if (oldVersion < 4) {

            String CREATE_CUSTOMER_TABLE =
                    "CREATE TABLE " + TABLE_CUSTOMERS + "("
                            + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_CUSTOMER_NAME + " TEXT,"
                            + COLUMN_CUSTOMER_PHONE + " TEXT,"
                            + COLUMN_CUSTOMER_EMAIL + " TEXT,"
                            + COLUMN_CUSTOMER_ADDRESS + " TEXT"
                            + ")";

            db.execSQL(CREATE_CUSTOMER_TABLE);
        }
        // ===========================
// VERSION 5 - ORDER TABLE
// ===========================

        if (oldVersion < 5) {

            String CREATE_ORDER_TABLE =
                    "CREATE TABLE " + TABLE_ORDERS + "("
                            + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_ORDER_CUSTOMER_ID + " INTEGER,"
                            + COLUMN_ORDER_CUSTOMER_NAME + " TEXT,"
                            + COLUMN_ORDER_DATE + " TEXT,"
                            + COLUMN_ORDER_TOTAL + " REAL,"
                            + COLUMN_ORDER_STATUS + " TEXT"
                            + ")";

            db.execSQL(CREATE_ORDER_TABLE);
        }
        if (oldVersion < 6) {

            String CREATE_ORDER_ITEMS_TABLE =
                    "CREATE TABLE " + TABLE_ORDER_ITEMS + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_ORDER_ID + " INTEGER,"
                            + COLUMN_PRODUCT_ID + " INTEGER,"
                            + COLUMN_NAME + " TEXT,"
                            + "quantity REAL,"
                            + "price REAL"
                            + ")";

            db.execSQL(CREATE_ORDER_ITEMS_TABLE);
        }
        // ===========================
// VERSION 7 - PAYMENT TABLE
// ===========================

        if (oldVersion < 7) {

            String CREATE_PAYMENT_TABLE =
                    "CREATE TABLE " + TABLE_PAYMENTS + "("
                            + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_PAYMENT_ORDER_ID + " INTEGER,"
                            + COLUMN_PAYMENT_CUSTOMER_ID + " INTEGER,"
                            + COLUMN_PAID_AMOUNT + " REAL,"
                            + COLUMN_PENDING_AMOUNT + " REAL,"
                            + COLUMN_PAYMENT_DATE + " TEXT,"
                            + COLUMN_PAYMENT_METHOD + " TEXT"
                            + ")";

            db.execSQL(CREATE_PAYMENT_TABLE);
        }
        // ===========================
// VERSION 8 - INVOICE TABLE
// ===========================

        if (oldVersion < 8) {

            String CREATE_INVOICE_TABLE =
                    "CREATE TABLE " + TABLE_INVOICES + "("
                            + COLUMN_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_INVOICE_NUMBER + " TEXT,"
                            + COLUMN_INVOICE_ORDER_ID + " INTEGER,"
                            + COLUMN_INVOICE_CUSTOMER_ID + " INTEGER,"
                            + COLUMN_INVOICE_CUSTOMER_NAME + " TEXT,"
                            + COLUMN_INVOICE_DATE + " TEXT,"
                            + COLUMN_SUBTOTAL + " REAL,"
                            + COLUMN_GST_PERCENT + " REAL,"
                            + COLUMN_GST_AMOUNT + " REAL,"
                            + COLUMN_GRAND_TOTAL + " REAL"
                            + ")";

            db.execSQL(CREATE_INVOICE_TABLE);
        }
        // ===========================
// VERSION 9 - BUSINESS PROFILE TABLE
// ===========================

        if (oldVersion < 9) {

            String CREATE_BUSINESS_TABLE =
                    "CREATE TABLE " + TABLE_BUSINESS + "("
                            + COLUMN_BUSINESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_BUSINESS_NAME + " TEXT,"
                            + COLUMN_OWNER_NAME + " TEXT,"
                            + COLUMN_GST_NUMBER + " TEXT,"
                            + COLUMN_PHONE + " TEXT,"
                            + COLUMN_EMAIL + " TEXT,"
                            + COLUMN_ADDRESS + " TEXT"
                            + ")";

            db.execSQL(CREATE_BUSINESS_TABLE);
        }
        // ===========================
// VERSION 10 - INVOICE STATUS
// ===========================

        if (oldVersion < 10) {

            try {

                db.execSQL(
                        "ALTER TABLE " + TABLE_INVOICES +
                                " ADD COLUMN " + COLUMN_INVOICE_STATUS +
                                " TEXT DEFAULT 'ACTIVE'"
                );

            } catch (Exception ignored) {

            }
        }
        // ===========================
// VERSION 11 - LEDGER TABLE
// ===========================

        if (oldVersion < 11) {

            String CREATE_LEDGER_TABLE =
                    "CREATE TABLE " + TABLE_LEDGER_ENTRIES + "("
                            + COLUMN_LEDGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_LEDGER_CUSTOMER_ID + " INTEGER,"
                            + COLUMN_LEDGER_TYPE + " TEXT,"
                            + COLUMN_LEDGER_TITLE + " TEXT,"
                            + COLUMN_LEDGER_AMOUNT + " REAL,"
                            + COLUMN_LEDGER_DATE + " TEXT"
                            + ")";

            db.execSQL(CREATE_LEDGER_TABLE);
        }
        // ===========================
// VERSION 12 - SUPPLIER TABLE
// ===========================

        if (oldVersion < 12) {

            String CREATE_SUPPLIER_TABLE =
                    "CREATE TABLE " + TABLE_SUPPLIERS + "("
                            + COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_SUPPLIER_NAME + " TEXT,"
                            + COLUMN_COMPANY_NAME + " TEXT,"
                            + COLUMN_SUPPLIER_PHONE + " TEXT,"
                            + COLUMN_SUPPLIER_EMAIL + " TEXT,"
                            + COLUMN_SUPPLIER_GST + " TEXT,"
                            + COLUMN_SUPPLIER_ADDRESS + " TEXT,"
                            + COLUMN_SUPPLIER_CITY + " TEXT,"
                            + COLUMN_SUPPLIER_STATE + " TEXT,"
                            + COLUMN_SUPPLIER_PINCODE + " TEXT,"
                            + COLUMN_OPENING_BALANCE + " REAL,"
                            + COLUMN_BALANCE_TYPE + " TEXT,"
                            + COLUMN_SUPPLIER_NOTES + " TEXT"
                            + ")";

            db.execSQL(CREATE_SUPPLIER_TABLE);
        }
        // ===========================
// VERSION 13 - PURCHASE TABLE
// ===========================
        if (oldVersion < 13) {

            String CREATE_PURCHASE_TABLE =
                    "CREATE TABLE " + TABLE_PURCHASE + "("
                            + COLUMN_PURCHASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_PURCHASE_SUPPLIER_ID + " INTEGER,"
                            + COLUMN_PURCHASE_PRODUCT_ID + " INTEGER,"
                            + COLUMN_PURCHASE_QUANTITY + " REAL,"
                            + COLUMN_PURCHASE_PRICE + " REAL,"
                            + COLUMN_PURCHASE_TOTAL + " REAL,"
                            + COLUMN_PURCHASE_DATE + " TEXT,"
                            + COLUMN_PURCHASE_NOTES + " TEXT"
                            + ")";

            db.execSQL(CREATE_PURCHASE_TABLE);
        }
        // ===========================
// VERSION 14 - PURCHASE RETURN TABLE
// ===========================
        if (oldVersion < 14) {

            String CREATE_PURCHASE_RETURN_TABLE =
                    "CREATE TABLE " + TABLE_PURCHASE_RETURN + "("
                            + COLUMN_RETURN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_RETURN_PURCHASE_ID + " INTEGER,"
                            + COLUMN_RETURN_SUPPLIER_ID + " INTEGER,"
                            + COLUMN_RETURN_PRODUCT_ID + " INTEGER,"
                            + COLUMN_RETURN_QUANTITY + " REAL,"
                            + COLUMN_RETURN_AMOUNT + " REAL,"
                            + COLUMN_RETURN_DATE + " TEXT,"
                            + COLUMN_RETURN_REASON + " TEXT"
                            + ")";

            db.execSQL(CREATE_PURCHASE_RETURN_TABLE);
        }
        // ===========================
// VERSION 15 - PRODUCT SELLING TABLE
// ===========================
        if (oldVersion < 15) {

            db.execSQL(
                    "ALTER TABLE " + TABLE_PRODUCTS +
                            " ADD COLUMN " +
                            COLUMN_SELLING_PRICE +
                            " REAL DEFAULT 0");

        }
        // ===========================
// VERSION 16 - STOCK MOVEMENT TABLE
// ===========================
        if (oldVersion < 16) {

            String CREATE_STOCK_MOVEMENT_TABLE =
                    "CREATE TABLE " + TABLE_STOCK_MOVEMENT + " ("
                            + COLUMN_MOVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_PRODUCT_ID + " INTEGER,"
                            + COLUMN_PRODUCT_NAME + " TEXT,"
                            + COLUMN_MOVEMENT_TYPE + " TEXT,"
                            + COLUMN_MOVEMENT_QTY + " REAL,"
                            + COLUMN_STOCK_AFTER + " REAL,"
                            + COLUMN_MOVEMENT_DATE + " TEXT"
                            + ")";

            db.execSQL(CREATE_STOCK_MOVEMENT_TABLE);
        }
        if (oldVersion < 17) {

            try {

                db.execSQL(
                        "ALTER TABLE "
                                + TABLE_BUSINESS
                                + " ADD COLUMN "
                                + COLUMN_PROFILE_IMAGE
                                + " TEXT");

            } catch (Exception ignored) {

            }
        }
        // ===========================
// VERSION 18 - USERS TABLE
// ===========================

        if (oldVersion < 18) {

            String CREATE_USERS_TABLE =
                    "CREATE TABLE " + TABLE_USERS + "("
                            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_USERNAME + " TEXT UNIQUE,"
                            + COLUMN_USER_LOGIN_ID + " TEXT,"
                            + COLUMN_USER_SHOP + " TEXT,"
                            + COLUMN_USER_MOBILE + " TEXT,"
                            + COLUMN_USER_GST + " TEXT,"
                            + COLUMN_USER_PASSWORD + " TEXT"
                            + ")";

            db.execSQL(CREATE_USERS_TABLE);
        }
        if (oldVersion < 19) {

            String CREATE_SECURITY_TABLE =
                    "CREATE TABLE " + TABLE_SECURITY + "("
                            + COLUMN_SECURITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + COLUMN_APP_LOCK + " INTEGER,"
                            + COLUMN_FINGERPRINT + " INTEGER,"
                            + COLUMN_AUTO_LOGOUT + " INTEGER,"
                            + COLUMN_PIN + " TEXT"
                            + ")";

            db.execSQL(CREATE_SECURITY_TABLE);
        }
        // ===========================
// VERSION 19 - PRODUCT USER
// ===========================

        if (oldVersion < 20) {

            try {

                db.execSQL(
                        "ALTER TABLE "
                                + TABLE_PRODUCTS
                                + " ADD COLUMN "
                                + COLUMN_PRODUCT_USER_ID
                                + " INTEGER DEFAULT 0");

            } catch (Exception ignored) {

            }
        }
        // ===========================
// VERSION 20 - ORDER USER
// ===========================

        if (oldVersion < 21) {

            try {

                db.execSQL(
                        "ALTER TABLE "
                                + TABLE_ORDERS
                                + " ADD COLUMN "
                                + COLUMN_ORDER_USER_ID
                                + " INTEGER DEFAULT 0");

            } catch (Exception ignored) {

            }
        }
        // ===========================
// VERSION 21 - ORDER ITEM USER
// ===========================

        if (oldVersion < 22) {

            try {

                db.execSQL(
                        "ALTER TABLE "
                                + TABLE_ORDER_ITEMS
                                + " ADD COLUMN "
                                + COLUMN_ORDERITEM_USER_ID
                                + " INTEGER DEFAULT 0");

            } catch (Exception ignored) {

            }
        }
        if (oldVersion < 23) {

            try {

                db.execSQL(
                        "ALTER TABLE " + TABLE_PURCHASE +
                                " ADD COLUMN " + COLUMN_PURCHASE_USER_ID +
                                " INTEGER DEFAULT 0");

            } catch (Exception ignored) {
            }

            try {

                db.execSQL(
                        "ALTER TABLE " + TABLE_PURCHASE_RETURN +
                                " ADD COLUMN " + COLUMN_PURCHASE_RETURN_USER_ID +
                                " INTEGER DEFAULT 0");

            } catch (Exception ignored) {
            }
        }
        // ===========================
// VERSION 23 - SUPPLIER USER ID
// ===========================

        if (oldVersion < 24) {

            try {

                db.execSQL(
                        "ALTER TABLE " + TABLE_SUPPLIERS +
                                " ADD COLUMN " + COLUMN_SUPPLIER_USER_ID +
                                " INTEGER DEFAULT 0");

            } catch (Exception ignored) {

            }

        }
    }

    // =========================
    // INSERT PRODUCT
    // =========================
    public boolean insertProduct(Product product, int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCT_USER_ID, userId);

        values.put(COLUMN_NAME, product.getProductName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_UNIT, product.getUnit());
        values.put(COLUMN_PRICE_PER_UNIT, product.getPricePerUnit());
        values.put(COLUMN_SELLING_PRICE, product.getSellingPrice());
        values.put(COLUMN_TOTAL_PRICE, product.getTotalPrice());
        values.put(COLUMN_MIN_STOCK, product.getMinStock());
        values.put(COLUMN_MAX_STOCK, product.getMaxStock());

        long result = db.insert(TABLE_PRODUCTS, null, values);

        db.close();

        return result != -1;
    }

    // =========================
    // GET ALL PRODUCTS
    // =========================

    public ArrayList<Product> getAllProducts(int userId) {

        ArrayList<Product> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Product p = new Product();

                p.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                p.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                p.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                p.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                p.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                p.setSellingPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                p.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                p.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                p.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                list.add(p);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }

    public ArrayList<Product> getProductsByNameAsc(int userId) {
        return getSortedProducts(userId, COLUMN_NAME + " ASC");
    }

    public ArrayList<Product> getProductsByNameDesc(int userId) {
        return getSortedProducts(userId, COLUMN_NAME + " DESC");
    }

    public ArrayList<Product> getProductsByPriceAsc(int userId) {
        return getSortedProducts(userId, COLUMN_PRICE_PER_UNIT + " ASC");
    }

    public ArrayList<Product> getProductsByPriceDesc(int userId) {
        return getSortedProducts(userId, COLUMN_PRICE_PER_UNIT + " DESC");
    }

    public ArrayList<Product> getProductsByStockAsc(int userId) {
        return getSortedProducts(userId, COLUMN_QUANTITY + " ASC");
    }

    public ArrayList<Product> getProductsByStockDesc(int userId) {
        return getSortedProducts(userId, COLUMN_QUANTITY + " DESC");
    }
    private ArrayList<Product> getSortedProducts(int userId, String orderBy) {

        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? " +
                        " ORDER BY " + orderBy,
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Product product = new Product();

                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                product.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                product.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                product.setSellingPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                product.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                product.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                product.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                productList.add(product);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return productList;
    }

    public ArrayList<Product> getLowStockProducts(int userId) {

        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_MIN_STOCK + " > 0 AND " +
                        COLUMN_QUANTITY + " < " + COLUMN_MIN_STOCK,
                new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {

            do {

                Product product = new Product();

                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                product.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                product.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                product.setSellingPrice(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                product.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                product.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                product.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                productList.add(product);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return productList;
    }

    public ArrayList<Product> getStockFullProducts(int userId) {

        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_MIN_STOCK + " > 0 AND " +
                        COLUMN_QUANTITY + " < " + COLUMN_MIN_STOCK,
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Product product = new Product();

                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                product.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                product.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                product.setSellingPrice(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                product.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                product.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                product.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                productList.add(product);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return productList;
    }

    public ArrayList<Product> getOverStockProducts(int userId) {

        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_MIN_STOCK + " > 0 AND " +
                        COLUMN_QUANTITY + " < " + COLUMN_MIN_STOCK,
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Product product = new Product();

                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                product.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                product.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                product.setSellingPrice(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                product.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                product.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                product.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                productList.add(product);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return productList;
    }

    public ArrayList<Product> getNormalStockProducts(int userId) {

        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_MIN_STOCK + " > 0 AND " +
                        COLUMN_QUANTITY + " < " + COLUMN_MIN_STOCK,
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Product product = new Product();

                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                product.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                product.setPricePerUnit(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
                product.setSellingPrice(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));
                product.setTotalPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));
                product.setMinStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));
                product.setMaxStock(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));

                productList.add(product);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return productList;
    }
    // =========================
    // UPDATE PRODUCT
    // =========================

    public boolean updateProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, product.getProductName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        values.put(COLUMN_UNIT, product.getUnit());
        values.put(COLUMN_PRICE_PER_UNIT, product.getPricePerUnit());
        values.put(COLUMN_SELLING_PRICE, product.getSellingPrice());
        values.put(COLUMN_TOTAL_PRICE, product.getTotalPrice());

        values.put(COLUMN_MIN_STOCK, product.getMinStock());
        values.put(COLUMN_MAX_STOCK, product.getMaxStock());

        int result = db.update(
                TABLE_PRODUCTS,
                values,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(product.getId())});

        db.close();

        return result > 0;
    }



    // =========================
// UPDATE STOCK ONLY
// =========================

    public boolean updateStock(int productId, double newQuantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)});

        if (cursor.moveToFirst()) {

            double pricePerUnit = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT));

            double totalPrice = newQuantity * pricePerUnit;

            ContentValues values = new ContentValues();

            values.put(COLUMN_QUANTITY, newQuantity);
            values.put(COLUMN_TOTAL_PRICE, totalPrice);

            int result = db.update(
                    TABLE_PRODUCTS,
                    values,
                    COLUMN_ID + "=?",
                    new String[]{String.valueOf(productId)});

            cursor.close();
            db.close();

            return result > 0;
        }

        cursor.close();
        db.close();

        return false;
    }
    // =========================
    // DELETE PRODUCT
    // =========================
    public void deleteProduct(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_PRODUCTS,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});

        db.close();
    }
    public int getTotalProducts(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }
    public double getTotalInventoryValue(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(total_price) FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        db.close();

        return total;
    }


    public int getOverstockCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_QUANTITY + " > " + COLUMN_MAX_STOCK +
                        " AND " + COLUMN_MAX_STOCK + " > 0",
                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }
    // =========================
// GET PRODUCT BY ID
// =========================

    public Product getProductById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        Product product = null;

        if (cursor.moveToFirst()) {

            product = new Product();

            product.setId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));

            product.setProductName(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));

            product.setQuantity(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));

            product.setUnit(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));

            product.setPricePerUnit(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT)));
            product.setSellingPrice(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SELLING_PRICE)));

            product.setTotalPrice(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)));

            product.setMinStock(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MIN_STOCK)));

            product.setMaxStock(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MAX_STOCK)));
        }

        cursor.close();
        db.close();

        return product;
    }


    // ==============================
// INSERT CUSTOMER
// ==============================

    public boolean insertCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CUSTOMER_NAME, customer.getCustomerName());
        values.put(COLUMN_CUSTOMER_PHONE, customer.getCustomerPhone());
        values.put(COLUMN_CUSTOMER_EMAIL, customer.getCustomerEmail());
        values.put(COLUMN_CUSTOMER_ADDRESS, customer.getCustomerAddress());

        long result = db.insert(TABLE_CUSTOMERS, null, values);

        db.close();

        return result != -1;
    }

// ==============================
// GET ALL CUSTOMERS
// ==============================

    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customerList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CUSTOMERS +
                        " ORDER BY " + COLUMN_CUSTOMER_NAME + " ASC",
                null);

        if (cursor.moveToFirst()) {

            do {

                Customer customer = new Customer();

                customer.setCustomerId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID)));

                customer.setCustomerName(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME)));

                customer.setCustomerPhone(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_PHONE)));

                customer.setCustomerEmail(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_EMAIL)));

                customer.setCustomerAddress(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ADDRESS)));

                customerList.add(customer);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return customerList;
    }
    public ArrayList<CustomerLedger> getCustomerLedger() {

        ArrayList<CustomerLedger> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor customerCursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CUSTOMERS +
                        " ORDER BY " + COLUMN_CUSTOMER_NAME + " ASC",
                null);

        while (customerCursor.moveToNext()) {

            CustomerLedger ledger = new CustomerLedger();

            int customerId = customerCursor.getInt(
                    customerCursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID));

            ledger.setCustomerId(customerId);

            ledger.setCustomerName(
                    customerCursor.getString(
                            customerCursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME)));

            ledger.setCustomerPhone(
                    customerCursor.getString(
                            customerCursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_PHONE)));

            // ==========================
            // TOTAL SALES (Uses GST Invoice if ACTIVE)
            // ==========================

            double totalSales = 0;

            Cursor orderCursor = db.rawQuery(
                    "SELECT " + COLUMN_ORDER_ID + ", " + COLUMN_ORDER_TOTAL +
                            " FROM " + TABLE_ORDERS +
                            " WHERE " + COLUMN_ORDER_CUSTOMER_ID + "=?",
                    new String[]{String.valueOf(customerId)});

            while (orderCursor.moveToNext()) {

                int orderId = orderCursor.getInt(0);

                double orderTotal = orderCursor.getDouble(1);

                Invoice invoice = getInvoiceByOrderId(orderId);

                if (invoice != null &&
                        "ACTIVE".equalsIgnoreCase(invoice.getStatus())) {

                    totalSales += invoice.getGrandTotal();

                } else {

                    totalSales += orderTotal;
                }
            }

            orderCursor.close();

            // ==========================
            // TOTAL PAID
            // ==========================

            Cursor paidCursor = db.rawQuery(
                    "SELECT IFNULL(SUM(" + COLUMN_PAID_AMOUNT + "),0) FROM "
                            + TABLE_PAYMENTS +
                            " WHERE " + COLUMN_PAYMENT_CUSTOMER_ID + "=?",
                    new String[]{String.valueOf(customerId)});

            double totalPaid = 0;

            if (paidCursor.moveToFirst()) {
                totalPaid = paidCursor.getDouble(0);
            }

            paidCursor.close();

            ledger.setTotalSales(totalSales);
            ledger.setTotalPaid(totalPaid);
            ledger.setPendingAmount(totalSales - totalPaid);

            list.add(ledger);
        }

        customerCursor.close();
        db.close();

        return list;
    }
    public CustomerLedger getCustomerLedgerById(int customerId) {

        ArrayList<CustomerLedger> list = getCustomerLedger();

        for (CustomerLedger ledger : list) {

            if (ledger.getCustomerId() == customerId) {

                return ledger;

            }
        }

        return null;
    }

// ==============================
// GET CUSTOMER BY ID
// ==============================

    public Customer getCustomerById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_CUSTOMERS,
                null,
                COLUMN_CUSTOMER_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {

            Customer customer = new Customer();

            customer.setCustomerId(id);

            customer.setCustomerName(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME)));

            customer.setCustomerPhone(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_PHONE)));

            customer.setCustomerEmail(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_EMAIL)));

            customer.setCustomerAddress(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ADDRESS)));

            cursor.close();
            db.close();

            return customer;
        }

        if (cursor != null)
            cursor.close();

        db.close();

        return null;
    }

// ==============================
// UPDATE CUSTOMER
// ==============================

    public boolean updateCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CUSTOMER_NAME, customer.getCustomerName());
        values.put(COLUMN_CUSTOMER_PHONE, customer.getCustomerPhone());
        values.put(COLUMN_CUSTOMER_EMAIL, customer.getCustomerEmail());
        values.put(COLUMN_CUSTOMER_ADDRESS, customer.getCustomerAddress());

        int result = db.update(
                TABLE_CUSTOMERS,
                values,
                COLUMN_CUSTOMER_ID + "=?",
                new String[]{String.valueOf(customer.getCustomerId())});

        db.close();

        return result > 0;
    }

// ==============================
// DELETE CUSTOMER
// ==============================

    public boolean deleteCustomer(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_CUSTOMERS,
                COLUMN_CUSTOMER_ID + "=?",
                new String[]{String.valueOf(id)});

        db.close();

        return result > 0;
    }
    public long insertOrder(Order order, int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ORDER_USER_ID, userId);

        values.put(COLUMN_ORDER_CUSTOMER_ID, order.getCustomerId());
        values.put(COLUMN_ORDER_CUSTOMER_NAME, order.getCustomerName());
        values.put(COLUMN_ORDER_DATE, order.getOrderDate());
        values.put(COLUMN_ORDER_TOTAL, order.getTotalAmount());
        values.put(COLUMN_ORDER_STATUS, order.getOrderStatus());

        long result = db.insert(TABLE_ORDERS, null, values);

        db.close();

        return result;
    }
    public ArrayList<Order> getAllOrders(int userId) {

        ArrayList<Order> orderList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=? " +
                        "ORDER BY " + COLUMN_ORDER_ID + " DESC",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Order order = new Order();

                order.setOrderId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ID)));

                order.setCustomerId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_ID)));

                order.setCustomerName(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_NAME)));

                order.setOrderDate(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATE)));

                order.setTotalAmount(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_ORDER_TOTAL)));

                order.setOrderStatus(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_STATUS)));

                orderList.add(order);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orderList;
    }
    public ArrayList<Order> getPendingOrdersByCustomer(int customerId) {

        ArrayList<Order> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT * FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_CUSTOMER_ID + "=?",

                new String[]{String.valueOf(customerId)}
        );

        while (cursor.moveToNext()) {

            Order order = new Order();

            order.setOrderId(
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_ID)));

            order.setCustomerId(
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_ID)));

            order.setCustomerName(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_NAME)));

            order.setOrderDate(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATE)));

            order.setTotalAmount(
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_TOTAL)));

            order.setOrderStatus(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_ORDER_STATUS)));
            // Only show orders with remaining balance

            double paid = getTotalPaidAmount(order.getOrderId());

            if (paid < order.getTotalAmount()) {

                list.add(order);
            }
        }

        cursor.close();
        db.close();

        return list;
    }
    public boolean deleteOrder(int orderId) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();

        try {

            Cursor cursor = db.rawQuery(
                    "SELECT product_id, quantity FROM " + TABLE_ORDER_ITEMS +
                            " WHERE order_id=?",
                    new String[]{String.valueOf(orderId)}
            );

            while (cursor.moveToNext()) {

                int productId = cursor.getInt(
                        cursor.getColumnIndexOrThrow("product_id"));

                double orderedQty = cursor.getDouble(
                        cursor.getColumnIndexOrThrow("quantity"));

                Cursor productCursor = db.rawQuery(
                        "SELECT quantity, price_per_unit FROM " + TABLE_PRODUCTS +
                                " WHERE id=?",
                        new String[]{String.valueOf(productId)}
                );

                if (productCursor.moveToFirst()) {

                    double currentQty = productCursor.getDouble(
                            productCursor.getColumnIndexOrThrow("quantity"));

                    double price = productCursor.getDouble(
                            productCursor.getColumnIndexOrThrow("price_per_unit"));

                    ContentValues values = new ContentValues();
                    values.put("quantity", currentQty + orderedQty);
                    values.put("total_price", (currentQty + orderedQty) * price);

                    db.update(
                            TABLE_PRODUCTS,
                            values,
                            "id=?",
                            new String[]{String.valueOf(productId)}
                    );
                }

                productCursor.close();
            }

            cursor.close();

            db.delete(
                    TABLE_ORDER_ITEMS,
                    COLUMN_ORDER_ID + "=?",
                    new String[]{String.valueOf(orderId)}
            );

            db.delete(
                    TABLE_PAYMENTS,
                    COLUMN_ORDER_ID + "=?",
                    new String[]{String.valueOf(orderId)}
            );
            db.delete(
                    TABLE_INVOICES,
                    COLUMN_INVOICE_ORDER_ID + "=?",
                    new String[]{String.valueOf(orderId)}
            );

            int result = db.delete(
                    TABLE_ORDERS,
                    COLUMN_ORDER_ID + "=?",
                    new String[]{String.valueOf(orderId)}
            );
            db.setTransactionSuccessful();

            return result > 0;

        } finally {

            db.endTransaction();
            db.close();
        }

    }
    public boolean updateOrder(Order order) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ORDER_CUSTOMER_ID, order.getCustomerId());
        values.put(COLUMN_ORDER_CUSTOMER_NAME, order.getCustomerName());
        values.put(COLUMN_ORDER_DATE, order.getOrderDate());
        values.put(COLUMN_ORDER_TOTAL, order.getTotalAmount());
        values.put(COLUMN_ORDER_STATUS, order.getOrderStatus());

        int result = db.update(
                TABLE_ORDERS,
                values,
                COLUMN_ORDER_ID + "=?",
                new String[]{String.valueOf(order.getOrderId())});

        db.close();

        return result > 0;
    }
    public boolean insertOrderItem(OrderItem item, int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ORDERITEM_USER_ID, userId);

        values.put(COLUMN_ORDER_ID, item.getOrderId());
        values.put(COLUMN_PRODUCT_ID, item.getProductId());
        values.put(COLUMN_NAME, item.getProductName());
        values.put("quantity", item.getQuantity());
        values.put("price", item.getPrice());

        long result = db.insert(TABLE_ORDER_ITEMS, null, values);

        db.close();

        return result != -1;
    }
    public ArrayList<OrderItem> getOrderItemsByOrderId(int orderId) {

        ArrayList<OrderItem> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDER_ITEMS +
                        " WHERE order_id=?",
                new String[]{String.valueOf(orderId)}
        );

        if (cursor.moveToFirst()) {

            do {

                OrderItem item = new OrderItem();

                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                item.setOrderId(cursor.getInt(cursor.getColumnIndexOrThrow("order_id")));
                item.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow("product_id")));
                item.setProductName(cursor.getString(cursor.getColumnIndexOrThrow("product_name")));
                item.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow("quantity")));
                item.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow("price")));

                list.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    public int getLastOrderId() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT order_id FROM " + TABLE_ORDERS +
                        " ORDER BY order_id DESC LIMIT 1",
                null
        );

        int id = -1;

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return id;
    }
    public Order getOrderById(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_ID + "=?",
                new String[]{String.valueOf(orderId)}
        );

        Order order = null;

        if (cursor.moveToFirst()) {

            order = new Order();

            order.setOrderId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ID)));
            order.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_ID)));
            order.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_CUSTOMER_NAME)));
            order.setOrderDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATE)));
            order.setOrderStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_STATUS)));

            double total = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_ORDER_TOTAL));

            Invoice invoice = getInvoiceByOrderId(orderId);

            if (invoice != null &&
                    "ACTIVE".equals(invoice.getStatus())) {

                total = invoice.getGrandTotal();
            }

            order.setTotalAmount(total);
        }

        cursor.close();
        db.close();

        return order;
    }
    public boolean deleteOrderItems(int orderId) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_ORDER_ITEMS,
                "order_id=?",
                new String[]{String.valueOf(orderId)}
        );

        db.close();

        return result >= 0;
    }
    public boolean deletePaymentsByOrderId(int orderId) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_PAYMENTS,
                COLUMN_ORDER_ID + "=?",
                new String[]{String.valueOf(orderId)}
        );

        db.close();

        return result >= 0;
    }
    public boolean restoreOrderStock(int orderId) {

        ArrayList<OrderItem> items = getOrderItemsByOrderId(orderId);

        for (OrderItem item : items) {

            Product product = getProductById(item.getProductId());

            if (product != null) {

                updateStock(
                        product.getId(),
                        product.getQuantity() + item.getQuantity()
                );
            }
        }

        return true;
    }
    public boolean deductNewOrderStock(ArrayList<OrderItem> items) {

        for (OrderItem item : items) {

            Product product = getProductById(item.getProductId());

            if (product == null)
                return false;

            if (product.getQuantity() < item.getQuantity())
                return false;

            updateStock(
                    product.getId(),
                    product.getQuantity() - item.getQuantity()
            );
        }

        return true;
    }
    public int getTotalOrders(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=?",
                new String[]{String.valueOf(userId)}
        );

        int total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return total;
    }
    public double getTotalRevenue(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT IFNULL(SUM(" + COLUMN_ORDER_TOTAL + "),0) " +
                        "FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=?",

                new String[]{
                        String.valueOf(userId)
                });

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        db.close();

        return total;
    }
    public double getTodaySales(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String today = new java.text.SimpleDateFormat(
                "yyyy-MM-dd",
                java.util.Locale.getDefault())
                .format(new java.util.Date());

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + COLUMN_ORDER_TOTAL + ") FROM "
                        + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=? AND "
                        + COLUMN_ORDER_DATE + "=?",
                new String[]{
                        String.valueOf(userId),
                        today
                });

        double total = 0;

        if (cursor.moveToFirst()) {

            total = cursor.getDouble(0);

        }

        cursor.close();
        db.close();

        return total;
    }
    public double getMonthlySales(int userId){
        SQLiteDatabase db = this.getReadableDatabase();

        String month = new java.text.SimpleDateFormat(
                "yyyy-MM",
                java.util.Locale.getDefault())
                .format(new java.util.Date());

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_ORDER_TOTAL + "," +
                        COLUMN_ORDER_DATE +
                        " FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=?",
                new String[]{String.valueOf(userId)});
        double total = 0;

        if (cursor.moveToFirst()) {

            do {

                String date = cursor.getString(1);

                if (date != null && date.startsWith(month)) {

                    total += cursor.getDouble(0);

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return total;
    }
    public ArrayList<String> getTopSellingProducts(int userId){
        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT product_name, SUM(quantity) AS totalQty " +
                        "FROM " + TABLE_ORDER_ITEMS +
                        " WHERE " + COLUMN_ORDERITEM_USER_ID + "=?" +
                        " GROUP BY product_name " +
                        " ORDER BY totalQty DESC LIMIT 5",

                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                String product =
                        cursor.getString(0);

                double qty =
                        cursor.getDouble(1);

                list.add(product + "  (" + qty + ")");

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
// ================================
// INVENTORY REPORT
// ================================

    public int getTotalProductsCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }

    public double getInventoryValue(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + COLUMN_TOTAL_PRICE + ") FROM "
                        + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        double value = 0;

        if (cursor.moveToFirst()) {

            value = cursor.getDouble(0);
        }

        cursor.close();
        db.close();

        return value;
    }
    public ArrayList<InventoryValuation> getInventoryValuation(int userId) {

        ArrayList<InventoryValuation> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT product_name, quantity, price_per_unit, total_price FROM "
                        + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_PRODUCT_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                InventoryValuation valuation =
                        new InventoryValuation();

                valuation.setProductName(
                        cursor.getString(0));

                valuation.setStock(
                        cursor.getDouble(1));

                valuation.setPurchasePrice(
                        cursor.getDouble(2));

                valuation.setInventoryValue(
                        cursor.getDouble(3));

                list.add(valuation);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }

    public int getLowStockCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT COUNT(*) FROM " + TABLE_PRODUCTS +
                        " WHERE " +
                        COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_MIN_STOCK + " > 0 AND " +
                        COLUMN_QUANTITY + " < " +
                        COLUMN_MIN_STOCK,

                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {

            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }

    public int getOutOfStockCount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT COUNT(*) FROM " + TABLE_PRODUCTS +
                        " WHERE " +
                        COLUMN_PRODUCT_USER_ID + "=? AND " +
                        COLUMN_QUANTITY + " <=0",

                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {

            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }
    public boolean insertPayment(Payment payment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PAYMENT_ORDER_ID, payment.getOrderId());
        values.put(COLUMN_PAYMENT_CUSTOMER_ID, payment.getCustomerId());
        values.put(COLUMN_PAID_AMOUNT, payment.getPaidAmount());
        values.put(COLUMN_PENDING_AMOUNT, payment.getPendingAmount());
        values.put(COLUMN_PAYMENT_DATE, payment.getPaymentDate());
        values.put(COLUMN_PAYMENT_METHOD, payment.getPaymentMethod());

        long result = db.insert(TABLE_PAYMENTS, null, values);

        db.close();

        return result != -1;
    }
    public ArrayList<String> getCustomerDueReport(int userId) {

        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor orderCursor = db.rawQuery(

                "SELECT " +
                        COLUMN_ORDER_ID + ", " +
                        COLUMN_ORDER_CUSTOMER_NAME + ", " +
                        COLUMN_ORDER_TOTAL +
                        " FROM " + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=?",

                new String[]{String.valueOf(userId)});

        while (orderCursor.moveToNext()) {

            int orderId = orderCursor.getInt(0);
            String customer = orderCursor.getString(1);
            double total = orderCursor.getDouble(2);

            Invoice invoice = getInvoiceByOrderId(orderId);

            if (invoice != null &&
                    "ACTIVE".equals(invoice.getStatus())) {

                total = invoice.getGrandTotal();
            }

            double paid = getTotalPaidAmount(orderId);

            double due = total - paid;

            if (due > 0) {
                list.add(customer + "    ₹" + due);
            }
        }

        orderCursor.close();
        db.close();

        return list;
    }
    public double getTotalPendingAmount(int userId){

        double totalPending = 0;

        ArrayList<Order> orders = getAllOrders(userId);
        for (Order order : orders) {

            double total = order.getTotalAmount();

            Invoice invoice = getInvoiceByOrderId(order.getOrderId());

            if (invoice != null &&
                    "ACTIVE".equals(invoice.getStatus())) {

                total = invoice.getGrandTotal();
            }

            double paid = getTotalPaidAmount(order.getOrderId());

            totalPending += (total - paid);
        }

        return totalPending;
    }
    public double getTotalPaidAmount(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT IFNULL(SUM(" + COLUMN_PAID_AMOUNT + "),0) FROM "
                        + TABLE_PAYMENTS +
                        " WHERE " + COLUMN_ORDER_ID + "=?",
                new String[]{String.valueOf(orderId)}
        );

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();


        return total;
    }
    // ==============================
// INSERT INVOICE
// ==============================

    public boolean insertInvoice(Invoice invoice) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_INVOICE_NUMBER, invoice.getInvoiceNumber());
        values.put(COLUMN_INVOICE_ORDER_ID, invoice.getOrderId());
        values.put(COLUMN_INVOICE_CUSTOMER_ID, invoice.getCustomerId());
        values.put(COLUMN_INVOICE_CUSTOMER_NAME, invoice.getCustomerName());
        values.put(COLUMN_INVOICE_DATE, invoice.getInvoiceDate());
        values.put(COLUMN_SUBTOTAL, invoice.getSubTotal());
        values.put(COLUMN_GST_PERCENT, invoice.getGstPercent());
        values.put(COLUMN_GST_AMOUNT, invoice.getGstAmount());
        values.put(COLUMN_GRAND_TOTAL, invoice.getGrandTotal());
        values.put(COLUMN_INVOICE_STATUS, invoice.getStatus());

        long result = db.insert(TABLE_INVOICES, null, values);

        db.close();

        return result != -1;
    }
    // ==============================
// GET INVOICE BY ORDER ID
// ==============================

    public Invoice getInvoiceByOrderId(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_INVOICES +
                        " WHERE " + COLUMN_INVOICE_ORDER_ID + "=?",
                new String[]{String.valueOf(orderId)}
        );

        Invoice invoice = null;

        if (cursor.moveToFirst()) {

            invoice = new Invoice();

            invoice.setInvoiceId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_ID)));
            invoice.setInvoiceNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_NUMBER)));
            invoice.setOrderId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_ORDER_ID)));
            invoice.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_CUSTOMER_ID)));
            invoice.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_CUSTOMER_NAME)));
            invoice.setInvoiceDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_DATE)));
            invoice.setSubTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SUBTOTAL)));
            invoice.setGstPercent(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GST_PERCENT)));
            invoice.setGstAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GST_AMOUNT)));
            invoice.setGrandTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GRAND_TOTAL)));
            invoice.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_STATUS)));
        }

        cursor.close();


        return invoice;
    }
    public boolean updateInvoice(Invoice invoice) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_GST_PERCENT, invoice.getGstPercent());
        values.put(COLUMN_GST_AMOUNT, invoice.getGstAmount());
        values.put(COLUMN_GRAND_TOTAL, invoice.getGrandTotal());

        int result = db.update(
                TABLE_INVOICES,
                values,
                COLUMN_INVOICE_ID + "=?",
                new String[]{String.valueOf(invoice.getInvoiceId())}
        );

        // ============================
        // ALSO UPDATE ORDER TOTAL
        // ============================

        if (result > 0) {

            ContentValues orderValues = new ContentValues();

            orderValues.put(
                    COLUMN_ORDER_TOTAL,
                    invoice.getGrandTotal()
            );

            db.update(
                    TABLE_ORDERS,
                    orderValues,
                    COLUMN_ORDER_ID + "=?",
                    new String[]{
                            String.valueOf(invoice.getOrderId())
                    }
            );
        }

        db.close();

        return result > 0;
    }
    public boolean cancelInvoice(int invoiceId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_INVOICE_STATUS, "CANCELLED");

        int result = db.update(
                TABLE_INVOICES,
                values,
                COLUMN_INVOICE_ID + "=?",
                new String[]{String.valueOf(invoiceId)}
        );

        db.close();

        return result > 0;
    }
    // ==============================
// GET LAST INVOICE NUMBER
// ==============================

    public String getLastInvoiceNumber() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_INVOICE_NUMBER +
                        " FROM " + TABLE_INVOICES +
                        " ORDER BY " + COLUMN_INVOICE_ID +
                        " DESC LIMIT 1",
                null
        );

        String invoice = null;

        if (cursor.moveToFirst()) {
            invoice = cursor.getString(0);
        }

        cursor.close();


        return invoice;
    }
    // ==============================
// GET ALL INVOICES
// ==============================

    public ArrayList<Invoice> getAllInvoices() {

        ArrayList<Invoice> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_INVOICES +
                        " ORDER BY " + COLUMN_INVOICE_ID + " DESC",
                null
        );

        if (cursor.moveToFirst()) {

            do {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_ID)));
                invoice.setInvoiceNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_NUMBER)));
                invoice.setOrderId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_ORDER_ID)));
                invoice.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_CUSTOMER_ID)));
                invoice.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_CUSTOMER_NAME)));
                invoice.setInvoiceDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_DATE)));
                invoice.setSubTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SUBTOTAL)));
                invoice.setGstPercent(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GST_PERCENT)));
                invoice.setGstAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GST_AMOUNT)));
                invoice.setGrandTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_GRAND_TOTAL)));
                invoice.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INVOICE_STATUS)));

                list.add(invoice);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    // ==============================
// CHECK IF INVOICE EXISTS
// ==============================

    public boolean invoiceExists(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_INVOICE_ID +
                        " FROM " + TABLE_INVOICES +
                        " WHERE " + COLUMN_INVOICE_ORDER_ID + "=?",
                new String[]{String.valueOf(orderId)}
        );

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
    public boolean hasActiveInvoice(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_INVOICE_ID +
                        " FROM " + TABLE_INVOICES +
                        " WHERE " + COLUMN_INVOICE_ORDER_ID + "=? AND "
                        + COLUMN_INVOICE_STATUS + "='ACTIVE'",
                new String[]{String.valueOf(orderId)}
        );

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
    // ==============================
// GENERATE NEXT INVOICE NUMBER
// ==============================

    public String generateNextInvoiceNumber() {

        String last = getLastInvoiceNumber();

        if (last == null || last.isEmpty()) {
            return "INV-000001";
        }

        try {

            int number = Integer.parseInt(last.replace("INV-", ""));

            number++;

            return String.format(
                    java.util.Locale.getDefault(),
                    "INV-%06d",
                    number);

        } catch (Exception e) {

            return "INV-000001";
        }
    }
    public boolean insertBusinessProfile(BusinessProfile profile) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_BUSINESS_NAME, profile.getBusinessName());
        values.put(COLUMN_OWNER_NAME, profile.getOwnerName());
        values.put(COLUMN_GST_NUMBER, profile.getGstNumber());
        values.put(COLUMN_PHONE, profile.getPhone());
        values.put(COLUMN_EMAIL, profile.getEmail());
        values.put(COLUMN_ADDRESS, profile.getAddress());
        values.put(COLUMN_PROFILE_IMAGE, profile.getProfileImage());

        long result = db.insert(TABLE_BUSINESS, null, values);

        db.close();

        return result != -1;
    }
    public boolean updateBusinessProfile(BusinessProfile profile) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_BUSINESS_NAME, profile.getBusinessName());
        values.put(COLUMN_OWNER_NAME, profile.getOwnerName());
        values.put(COLUMN_GST_NUMBER, profile.getGstNumber());
        values.put(COLUMN_PHONE, profile.getPhone());
        values.put(COLUMN_EMAIL, profile.getEmail());
        values.put(COLUMN_ADDRESS, profile.getAddress());
        values.put(COLUMN_PROFILE_IMAGE, profile.getProfileImage());

        int result = db.update(
                TABLE_BUSINESS,
                values,
                COLUMN_BUSINESS_ID + "=?",
                new String[]{String.valueOf(profile.getId())});

        db.close();

        return result > 0;
    }
    public BusinessProfile getBusinessProfile() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_BUSINESS + " LIMIT 1",
                null);

        BusinessProfile profile = null;

        if (cursor.moveToFirst()) {

            profile = new BusinessProfile();

            profile.setId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BUSINESS_ID)));

            profile.setBusinessName(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BUSINESS_NAME)));

            profile.setOwnerName(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OWNER_NAME)));

            profile.setGstNumber(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GST_NUMBER)));

            profile.setPhone(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));

            profile.setEmail(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));

            profile.setAddress(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
            profile.setProfileImage(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_IMAGE)));
        }

        cursor.close();
        db.close();

        return profile;
    }
    // ==============================
// CHECK PAYMENT EXISTS FOR ORDER
// ==============================

    public boolean hasPayment(int orderId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_PAYMENT_ID +
                        " FROM " + TABLE_PAYMENTS +
                        " WHERE " + COLUMN_PAYMENT_ORDER_ID + "=? LIMIT 1",
                new String[]{String.valueOf(orderId)}
        );

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
    // ==============================
// CUSTOMER TRANSACTION HISTORY
// ==============================
    public ArrayList<CustomerTransaction> getCustomerTransactions(int customerId) {

        ArrayList<CustomerTransaction> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        double runningBalance = 0;

        // ==========================
        // INVOICES
        // ==========================

        Cursor invoiceCursor = db.rawQuery(

                "SELECT "
                        + COLUMN_INVOICE_ID + ", "
                        + COLUMN_INVOICE_NUMBER + ", "
                        + COLUMN_INVOICE_DATE + ", "
                        + COLUMN_GRAND_TOTAL
                        + " FROM " + TABLE_INVOICES
                        + " WHERE " + COLUMN_INVOICE_CUSTOMER_ID + "=?"
                        + " ORDER BY " + COLUMN_INVOICE_DATE + " ASC",

                new String[]{String.valueOf(customerId)}

        );

        while (invoiceCursor.moveToNext()) {

            CustomerTransaction transaction = new CustomerTransaction();

            transaction.setId(invoiceCursor.getInt(0));
            transaction.setTransactionType("INVOICE");
            transaction.setTitle("Invoice " + invoiceCursor.getString(1));
            transaction.setDate(invoiceCursor.getString(2));

            double amount = invoiceCursor.getDouble(3);

            transaction.setAmount(amount);

            runningBalance += amount;

            transaction.setRunningBalance(runningBalance);

            list.add(transaction);
        }

        invoiceCursor.close();

        // ==========================
        // PAYMENTS
        // ==========================

        Cursor paymentCursor = db.rawQuery(

                "SELECT "
                        + COLUMN_PAYMENT_ID + ", "
                        + COLUMN_PAYMENT_DATE + ", "
                        + COLUMN_PAID_AMOUNT + ", "
                        + COLUMN_PAYMENT_METHOD
                        + " FROM " + TABLE_PAYMENTS
                        + " WHERE " + COLUMN_PAYMENT_CUSTOMER_ID + "=?"
                        + " ORDER BY " + COLUMN_PAYMENT_DATE + " ASC",

                new String[]{String.valueOf(customerId)}

        );

        while (paymentCursor.moveToNext()) {

            CustomerTransaction transaction = new CustomerTransaction();

            transaction.setId(paymentCursor.getInt(0));
            transaction.setTransactionType("PAYMENT");
            transaction.setTitle("Payment (" + paymentCursor.getString(3) + ")");
            transaction.setDate(paymentCursor.getString(1));

            double amount = paymentCursor.getDouble(2);

            transaction.setAmount(amount);

            runningBalance -= amount;

            transaction.setRunningBalance(runningBalance);

            list.add(transaction);
        }

        paymentCursor.close();

        // ==========================
        // MANUAL DEBIT / CREDIT
        // ==========================

        Cursor ledgerCursor = db.rawQuery(

                "SELECT "
                        + COLUMN_LEDGER_ID + ", "
                        + COLUMN_LEDGER_TYPE + ", "
                        + COLUMN_LEDGER_TITLE + ", "
                        + COLUMN_LEDGER_AMOUNT + ", "
                        + COLUMN_LEDGER_DATE
                        + " FROM " + TABLE_LEDGER_ENTRIES
                        + " WHERE " + COLUMN_LEDGER_CUSTOMER_ID + "=?"
                        + " ORDER BY " + COLUMN_LEDGER_DATE + " ASC",

                new String[]{String.valueOf(customerId)}

        );

        while (ledgerCursor.moveToNext()) {

            CustomerTransaction transaction = new CustomerTransaction();

            transaction.setId(ledgerCursor.getInt(0));

            String type = ledgerCursor.getString(1);

            transaction.setTransactionType(type);

            transaction.setTitle(ledgerCursor.getString(2));

            double amount = ledgerCursor.getDouble(3);

            transaction.setAmount(amount);

            transaction.setDate(ledgerCursor.getString(4));

            if (type.equals("DEBIT")) {

                runningBalance += amount;

            } else if (type.equals("CREDIT")) {

                runningBalance -= amount;
            }

            transaction.setRunningBalance(runningBalance);

            list.add(transaction);
        }

        ledgerCursor.close();

        db.close();

        return list;
    }
    public boolean insertLedgerEntry(CustomerLedgerEntry entry) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_LEDGER_CUSTOMER_ID, entry.getCustomerId());
        values.put(COLUMN_LEDGER_TYPE, entry.getType());
        values.put(COLUMN_LEDGER_TITLE, entry.getTitle());
        values.put(COLUMN_LEDGER_AMOUNT, entry.getAmount());
        values.put(COLUMN_LEDGER_DATE, entry.getDate());

        long result = db.insert(TABLE_LEDGER_ENTRIES, null, values);

        db.close();

        return result != -1;
    }
    public ArrayList<CustomerLedgerEntry> getLedgerEntries(int customerId) {

        ArrayList<CustomerLedgerEntry> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT * FROM " + TABLE_LEDGER_ENTRIES +
                        " WHERE " + COLUMN_LEDGER_CUSTOMER_ID + "=?" +
                        " ORDER BY " + COLUMN_LEDGER_ID + " ASC",

                new String[]{String.valueOf(customerId)}
        );

        while (cursor.moveToNext()) {

            CustomerLedgerEntry entry = new CustomerLedgerEntry();

            entry.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_ID)));
            entry.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_CUSTOMER_ID)));
            entry.setType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_TYPE)));
            entry.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_TITLE)));
            entry.setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_AMOUNT)));
            entry.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LEDGER_DATE)));

            list.add(entry);
        }

        cursor.close();
        db.close();

        return list;
    }
    public double getLedgerBalance(int customerId) {

        double debit = 0;
        double credit = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_LEDGER_ENTRIES +
                        " WHERE " + COLUMN_LEDGER_CUSTOMER_ID + "=?",
                new String[]{String.valueOf(customerId)}
        );

        while (cursor.moveToNext()) {

            String type = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_LEDGER_TYPE));

            double amount = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_LEDGER_AMOUNT));

            if ("DEBIT".equalsIgnoreCase(type)) {

                debit += amount;

            } else if ("CREDIT".equalsIgnoreCase(type)) {

                credit += amount;
            }
        }

        cursor.close();
        db.close();

        return debit - credit;
    }
    // ==============================
// INSERT SUPPLIER
// ==============================

    public boolean insertSupplier(Supplier supplier, int userId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_SUPPLIER_NAME, supplier.getSupplierName());
        values.put(COLUMN_SUPPLIER_USER_ID, userId);
        values.put(COLUMN_COMPANY_NAME, supplier.getCompanyName());
        values.put(COLUMN_SUPPLIER_PHONE, supplier.getMobile());
        values.put(COLUMN_SUPPLIER_EMAIL, supplier.getEmail());
        values.put(COLUMN_SUPPLIER_GST, supplier.getGstNumber());
        values.put(COLUMN_SUPPLIER_ADDRESS, supplier.getAddress());
        values.put(COLUMN_SUPPLIER_CITY, supplier.getCity());
        values.put(COLUMN_SUPPLIER_STATE, supplier.getState());
        values.put(COLUMN_SUPPLIER_PINCODE, supplier.getPincode());
        values.put(COLUMN_OPENING_BALANCE, supplier.getOpeningBalance());
        values.put(COLUMN_BALANCE_TYPE, supplier.getBalanceType());
        values.put(COLUMN_SUPPLIER_NOTES, supplier.getNotes());

        long result = db.insert(TABLE_SUPPLIERS, null, values);

        db.close();

        return result != -1;
    }
    // ==============================
// GET ALL SUPPLIERS
// ==============================

    public ArrayList<Supplier> getAllSuppliers(int userId){

        ArrayList<Supplier> supplierList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_SUPPLIERS +
                        " WHERE " + COLUMN_SUPPLIER_USER_ID + "=? " +
                        "ORDER BY " + COLUMN_SUPPLIER_NAME,
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Supplier supplier = new Supplier();

                supplier.setSupplierId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ID)));

                supplier.setSupplierName(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NAME)));

                supplier.setCompanyName(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPANY_NAME)));

                supplier.setMobile(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PHONE)));

                supplier.setEmail(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_EMAIL)));

                supplier.setGstNumber(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_GST)));

                supplier.setAddress(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ADDRESS)));

                supplier.setCity(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_CITY)));

                supplier.setState(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_STATE)));

                supplier.setPincode(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PINCODE)));

                supplier.setOpeningBalance(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_OPENING_BALANCE)));

                supplier.setBalanceType(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BALANCE_TYPE)));

                supplier.setNotes(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NOTES)));

                supplierList.add(supplier);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return supplierList;
    }
    // ==============================
// GET SUPPLIER BY ID
// ==============================

    public Supplier getSupplierById(int id, int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_SUPPLIERS +
                        " WHERE " + COLUMN_SUPPLIER_ID + "=? AND "
                        + COLUMN_SUPPLIER_USER_ID + "=?",
                new String[]{
                        String.valueOf(id),
                        String.valueOf(userId)
                });
        Supplier supplier = null;

        if (cursor.moveToFirst()) {

            supplier = new Supplier();

            supplier.setSupplierId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ID)));

            supplier.setSupplierName(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NAME)));

            supplier.setCompanyName(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPANY_NAME)));

            supplier.setMobile(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PHONE)));

            supplier.setEmail(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_EMAIL)));

            supplier.setGstNumber(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_GST)));

            supplier.setAddress(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ADDRESS)));

            supplier.setCity(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_CITY)));

            supplier.setState(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_STATE)));

            supplier.setPincode(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PINCODE)));

            supplier.setOpeningBalance(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_OPENING_BALANCE)));

            supplier.setBalanceType(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BALANCE_TYPE)));

            supplier.setNotes(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NOTES)));
        }

        cursor.close();
        db.close();

        return supplier;
    }
    // ==============================
// UPDATE SUPPLIER
// ==============================

    public boolean updateSupplier(Supplier supplier) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_SUPPLIER_NAME, supplier.getSupplierName());
        values.put(COLUMN_COMPANY_NAME, supplier.getCompanyName());
        values.put(COLUMN_SUPPLIER_PHONE, supplier.getMobile());
        values.put(COLUMN_SUPPLIER_EMAIL, supplier.getEmail());
        values.put(COLUMN_SUPPLIER_GST, supplier.getGstNumber());
        values.put(COLUMN_SUPPLIER_ADDRESS, supplier.getAddress());
        values.put(COLUMN_SUPPLIER_CITY, supplier.getCity());
        values.put(COLUMN_SUPPLIER_STATE, supplier.getState());
        values.put(COLUMN_SUPPLIER_PINCODE, supplier.getPincode());
        values.put(COLUMN_OPENING_BALANCE, supplier.getOpeningBalance());
        values.put(COLUMN_BALANCE_TYPE, supplier.getBalanceType());
        values.put(COLUMN_SUPPLIER_NOTES, supplier.getNotes());

        int result = db.update(
                TABLE_SUPPLIERS,
                values,
                COLUMN_SUPPLIER_ID + "=?",
                new String[]{String.valueOf(supplier.getSupplierId())});

        db.close();

        return result > 0;
    }
    // ==============================
// DELETE SUPPLIER
// ==============================

    public boolean deleteSupplier(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_SUPPLIERS,
                COLUMN_SUPPLIER_ID + "=?",
                new String[]{String.valueOf(id)});

        db.close();

        return result > 0;
    }
    // ==============================
// SEARCH SUPPLIERS
// ==============================

    public ArrayList<Supplier> searchSuppliers(String keyword,int userId) {

        ArrayList<Supplier> supplierList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT * FROM " + TABLE_SUPPLIERS +
                        " WHERE " + COLUMN_SUPPLIER_USER_ID + "=? AND (" +
                        COLUMN_SUPPLIER_NAME + " LIKE ? OR " +
                        COLUMN_COMPANY_NAME + " LIKE ? OR " +
                        COLUMN_SUPPLIER_PHONE + " LIKE ?)" +
                        " ORDER BY " + COLUMN_SUPPLIER_NAME,

                new String[]{
                        String.valueOf(userId),
                        "%" + keyword + "%",
                        "%" + keyword + "%",
                        "%" + keyword + "%"
                });

        if (cursor.moveToFirst()) {

            do {

                Supplier supplier = new Supplier();

                supplier.setSupplierId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ID)));
                supplier.setSupplierName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NAME)));
                supplier.setCompanyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPANY_NAME)));
                supplier.setMobile(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PHONE)));
                supplier.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_EMAIL)));
                supplier.setGstNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_GST)));
                supplier.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ADDRESS)));
                supplier.setCity(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_CITY)));
                supplier.setState(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_STATE)));
                supplier.setPincode(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_PINCODE)));
                supplier.setOpeningBalance(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_OPENING_BALANCE)));
                supplier.setBalanceType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BALANCE_TYPE)));
                supplier.setNotes(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NOTES)));

                supplierList.add(supplier);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return supplierList;
    }
    // ==============================
// INSERT PURCHASE
// ==============================

    public boolean insertPurchase(Purchase purchase, int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PURCHASE_SUPPLIER_ID, purchase.getSupplierId());
        values.put(COLUMN_PURCHASE_USER_ID, userId);
        values.put(COLUMN_PURCHASE_PRODUCT_ID, purchase.getProductId());
        values.put(COLUMN_PURCHASE_QUANTITY, purchase.getQuantity());
        values.put(COLUMN_PURCHASE_PRICE, purchase.getPurchasePrice());
        values.put(COLUMN_PURCHASE_TOTAL, purchase.getTotalAmount());
        values.put(COLUMN_PURCHASE_DATE, purchase.getPurchaseDate());
        values.put(COLUMN_PURCHASE_NOTES, purchase.getNotes());

        long result = db.insert(TABLE_PURCHASE, null, values);

        db.close();

        return result != -1;

    }
    // ==============================
// GET ALL PURCHASES
// ==============================

    public ArrayList<Purchase> getAllPurchases(int userId) {

        ArrayList<Purchase> purchaseList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT * FROM " + TABLE_PURCHASE +
                        " WHERE " + COLUMN_PURCHASE_USER_ID + "=? " +
                        "ORDER BY " + COLUMN_PURCHASE_ID + " DESC",

                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                Purchase purchase = new Purchase();

                purchase.setPurchaseId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_ID)));

                purchase.setSupplierId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_SUPPLIER_ID)));

                purchase.setProductId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRODUCT_ID)));

                purchase.setQuantity(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_QUANTITY)));

                purchase.setPurchasePrice(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRICE)));

                purchase.setTotalAmount(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_TOTAL)));

                purchase.setPurchaseDate(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_DATE)));

                purchase.setNotes(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_NOTES)));

                purchaseList.add(purchase);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return purchaseList;

    }
    // ==============================
// GET PURCHASE BY ID
// ==============================

    public Purchase getPurchaseById(int purchaseId,int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.query(

                TABLE_PURCHASE,

                null,

                COLUMN_PURCHASE_ID+"=? AND "+COLUMN_PURCHASE_USER_ID+"=?",

                new String[]{
                        String.valueOf(purchaseId),
                        String.valueOf(userId)
                },

                null,
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {

            Purchase purchase = new Purchase();

            purchase.setPurchaseId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_ID)));

            purchase.setSupplierId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_SUPPLIER_ID)));

            purchase.setProductId(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRODUCT_ID)));

            purchase.setQuantity(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_QUANTITY)));

            purchase.setPurchasePrice(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRICE)));

            purchase.setTotalAmount(
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_TOTAL)));

            purchase.setPurchaseDate(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_DATE)));

            purchase.setNotes(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_NOTES)));

            cursor.close();
            db.close();

            return purchase;
        }

        if (cursor != null)
            cursor.close();

        db.close();

        return null;

    }
    // ==============================
// INCREASE PRODUCT STOCK
// ==============================

    public boolean increaseProductStock(int productId, double purchaseQuantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)}
        );

        if (cursor.moveToFirst()) {

            double currentStock = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));

            double pricePerUnit = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT));

            double newStock = currentStock + purchaseQuantity;

            double totalPrice = newStock * pricePerUnit;

            ContentValues values = new ContentValues();

            values.put(COLUMN_QUANTITY, newStock);
            values.put(COLUMN_TOTAL_PRICE, totalPrice);

            int result = db.update(
                    TABLE_PRODUCTS,
                    values,
                    COLUMN_ID + "=?",
                    new String[]{String.valueOf(productId)}
            );

            cursor.close();
            db.close();

            return result > 0;
        }

        cursor.close();
        db.close();

        return false;
    }
    // ==============================
// DECREASE PRODUCT STOCK
// ==============================

    public boolean decreaseProductStock(int productId, double qty) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PRODUCTS +
                        " WHERE " + COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)});

        if (!cursor.moveToFirst()) {

            cursor.close();
            db.close();
            return false;

        }

        double currentQty = cursor.getDouble(
                cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));

        double price = cursor.getDouble(
                cursor.getColumnIndexOrThrow(COLUMN_PRICE_PER_UNIT));

        double newQty = currentQty - qty;

        if (newQty < 0)
            newQty = 0;

        ContentValues values = new ContentValues();

        values.put(COLUMN_QUANTITY, newQty);
        values.put(COLUMN_TOTAL_PRICE, newQty * price);

        int result = db.update(
                TABLE_PRODUCTS,
                values,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)});

        cursor.close();
        db.close();

        return result > 0;
    }
    // ==============================
// DELETE PURCHASE
// ==============================

    public void deletePurchase(int purchaseId) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_PURCHASE,
                COLUMN_PURCHASE_ID + "=?",
                new String[]{String.valueOf(purchaseId)}
        );

        db.close();

    }
    // ==============================
// SEARCH PURCHASES
// ==============================

    public ArrayList<Purchase> searchPurchases(String keyword, int userId) {

        ArrayList<Purchase> purchaseList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(

                "SELECT * FROM " + TABLE_PURCHASE +
                        " WHERE " + COLUMN_PURCHASE_USER_ID + "=? AND (" +
                        COLUMN_PURCHASE_DATE + " LIKE ? OR " +
                        COLUMN_PURCHASE_NOTES + " LIKE ?) " +
                        "ORDER BY " + COLUMN_PURCHASE_ID + " DESC",

                new String[]{
                        String.valueOf(userId),
                        "%" + keyword + "%",
                        "%" + keyword + "%"
                });

        if (cursor.moveToFirst()) {

            do {

                Purchase purchase = new Purchase();

                purchase.setPurchaseId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_ID)));

                purchase.setSupplierId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_SUPPLIER_ID)));

                purchase.setProductId(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRODUCT_ID)));

                purchase.setQuantity(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_QUANTITY)));

                purchase.setPurchasePrice(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRICE)));

                purchase.setTotalAmount(
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_TOTAL)));

                purchase.setPurchaseDate(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_DATE)));

                purchase.setNotes(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_NOTES)));

                purchaseList.add(purchase);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return purchaseList;
    }
    // ==============================
// UPDATE PURCHASE
// ==============================

    public boolean updatePurchase(Purchase purchase) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PURCHASE_SUPPLIER_ID, purchase.getSupplierId());
        values.put(COLUMN_PURCHASE_PRODUCT_ID, purchase.getProductId());
        values.put(COLUMN_PURCHASE_QUANTITY, purchase.getQuantity());
        values.put(COLUMN_PURCHASE_PRICE, purchase.getPurchasePrice());
        values.put(COLUMN_PURCHASE_TOTAL, purchase.getTotalAmount());
        values.put(COLUMN_PURCHASE_DATE, purchase.getPurchaseDate());
        values.put(COLUMN_PURCHASE_NOTES, purchase.getNotes());

        int result = db.update(
                TABLE_PURCHASE,
                values,
                COLUMN_PURCHASE_ID + "=?",
                new String[]{String.valueOf(purchase.getPurchaseId())}
        );

        db.close();

        return result > 0;

    }
    // ==============================
// GET SUPPLIER LEDGER
// ==============================

    public ArrayList<SupplierLedger> getSupplierLedger(int userId) {
        ArrayList<SupplierLedger> ledgerList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor supplierCursor = db.rawQuery(
                "SELECT * FROM " + TABLE_SUPPLIERS +
                        " ORDER BY " + COLUMN_SUPPLIER_NAME,
                null);

        if (supplierCursor.moveToFirst()) {

            do {

                int supplierId = supplierCursor.getInt(
                        supplierCursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_ID));

                String supplierName = supplierCursor.getString(
                        supplierCursor.getColumnIndexOrThrow(COLUMN_SUPPLIER_NAME));

                double openingBalance = supplierCursor.getDouble(
                        supplierCursor.getColumnIndexOrThrow(COLUMN_OPENING_BALANCE));

                double totalPurchase = 0;

                Cursor purchaseCursor = db.rawQuery(

                        "SELECT SUM(" + COLUMN_PURCHASE_TOTAL + ") FROM " +
                                TABLE_PURCHASE +
                                " WHERE " +
                                COLUMN_PURCHASE_SUPPLIER_ID + "=?",

                        new String[]{
                                String.valueOf(supplierId)
                        });

                if (purchaseCursor.moveToFirst()) {

                    totalPurchase = purchaseCursor.getDouble(0);

                }

                purchaseCursor.close();

                SupplierLedger ledger = new SupplierLedger();

                ledger.setSupplierName(supplierName);

                ledger.setOpeningBalance(openingBalance);

                ledger.setTotalPurchase(totalPurchase);

                ledger.setTotalPaid(0);

                ledger.setRemainingBalance(
                        openingBalance + totalPurchase);

                ledgerList.add(ledger);

            } while (supplierCursor.moveToNext());

        }

        supplierCursor.close();

        db.close();

        return ledgerList;

    }
    // ==============================
// TOTAL PURCHASE AMOUNT
// ==============================

    public double getTotalPurchaseAmount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT IFNULL(SUM(" + COLUMN_PURCHASE_TOTAL + "),0) FROM "
                        + TABLE_PURCHASE +
                        " WHERE " + COLUMN_PURCHASE_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        db.close();

        return total;
    }
    // ==============================
// TOTAL SUPPLIERS
// ==============================

    public int getTotalSuppliers(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM "
                        + TABLE_SUPPLIERS +
                        " WHERE " + COLUMN_SUPPLIER_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }
    // ==============================
// INSERT PURCHASE RETURN
// ==============================

    public boolean insertPurchaseReturn(PurchaseReturn purchaseReturn,int userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_RETURN_PURCHASE_ID,
                purchaseReturn.getPurchaseId());
        values.put(
                COLUMN_PURCHASE_RETURN_USER_ID,
                userId);
        values.put(COLUMN_RETURN_SUPPLIER_ID,
                purchaseReturn.getSupplierId());

        values.put(COLUMN_RETURN_PRODUCT_ID,
                purchaseReturn.getProductId());

        values.put(COLUMN_RETURN_QUANTITY,
                purchaseReturn.getReturnQuantity());

        values.put(COLUMN_RETURN_AMOUNT,
                purchaseReturn.getReturnAmount());

        values.put(COLUMN_RETURN_DATE,
                purchaseReturn.getReturnDate());

        values.put(COLUMN_RETURN_REASON,
                purchaseReturn.getReason());

        long result = db.insert(
                TABLE_PURCHASE_RETURN,
                null,
                values);

        db.close();

        return result != -1;
    }
    // ==============================
// GET ALL PURCHASE RETURNS
// ==============================

    public ArrayList<PurchaseReturn> getAllPurchaseReturns(int userId) {

        ArrayList<PurchaseReturn> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PURCHASE_RETURN +
                        " WHERE " + COLUMN_PURCHASE_RETURN_USER_ID + "=? " +
                        "ORDER BY " + COLUMN_RETURN_ID + " DESC",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {

            do {

                PurchaseReturn pr = new PurchaseReturn();

                pr.setReturnId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_ID)));

                pr.setPurchaseId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_PURCHASE_ID)));

                pr.setSupplierId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_SUPPLIER_ID)));

                pr.setProductId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_PRODUCT_ID)));

                pr.setReturnQuantity(cursor.getDouble(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_QUANTITY)));

                pr.setReturnAmount(cursor.getDouble(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_AMOUNT)));

                pr.setReturnDate(cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_DATE)));

                pr.setReason(cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_RETURN_REASON)));

                list.add(pr);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
    // ==============================
// DELETE PURCHASE RETURN
// ==============================

    public void deletePurchaseReturn(int returnId) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_PURCHASE_RETURN,
                COLUMN_RETURN_ID + "=?",
                new String[]{String.valueOf(returnId)});

        db.close();
    }
    // ==============================
// GET PURCHASE RETURN BY ID
// ==============================

    public PurchaseReturn getPurchaseReturnById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_PURCHASE_RETURN +
                        " WHERE " + COLUMN_RETURN_ID + "=?",
                new String[]{String.valueOf(id)});

        PurchaseReturn pr = null;

        if (cursor.moveToFirst()) {

            pr = new PurchaseReturn();

            pr.setReturnId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_ID)));

            pr.setPurchaseId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_PURCHASE_ID)));

            pr.setSupplierId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_SUPPLIER_ID)));

            pr.setProductId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_PRODUCT_ID)));

            pr.setReturnQuantity(cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_QUANTITY)));

            pr.setReturnAmount(cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_AMOUNT)));

            pr.setReturnDate(cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_DATE)));

            pr.setReason(cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_RETURN_REASON)));
        }

        cursor.close();
        db.close();

        return pr;
    }
    public boolean insertStockMovement(StockMovement movement) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCT_ID, movement.getProductId());

        values.put(COLUMN_PRODUCT_NAME, movement.getProductName());

        values.put(COLUMN_MOVEMENT_TYPE, movement.getMovementType());

        values.put(COLUMN_MOVEMENT_QTY, movement.getQuantity());

        values.put(COLUMN_STOCK_AFTER, movement.getStockAfter());

        values.put(COLUMN_MOVEMENT_DATE, movement.getMovementDate());

        long result =
                db.insert(TABLE_STOCK_MOVEMENT,
                        null,
                        values);

        db.close();

        return result != -1;
    }
    public ArrayList<StockMovement> getAllStockMovements() {

        ArrayList<StockMovement> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_STOCK_MOVEMENT +
                        " ORDER BY " + COLUMN_MOVEMENT_ID + " DESC",
                null);

        if (cursor.moveToFirst()) {

            do {

                StockMovement movement = new StockMovement();

                movement.setMovementId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COLUMN_MOVEMENT_ID)));

                movement.setProductId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID)));

                movement.setProductName(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME)));

                movement.setMovementType(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_MOVEMENT_TYPE)));

                movement.setQuantity(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_MOVEMENT_QTY)));

                movement.setStockAfter(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(COLUMN_STOCK_AFTER)));

                movement.setMovementDate(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COLUMN_MOVEMENT_DATE)));

                list.add(movement);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }
    // ==========================
// TOTAL SALES AMOUNT
// ==========================

    public double getTotalSalesAmount(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT IFNULL(SUM(" + COLUMN_ORDER_TOTAL + "),0) FROM "
                        + TABLE_ORDERS +
                        " WHERE " + COLUMN_ORDER_USER_ID + "=?",
                new String[]{String.valueOf(userId)});

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        db.close();

        return total;
    }
    // ==========================
// TOTAL EXPENSES
// ==========================

    public double getTotalExpenses() {

        return 0.0;

    }

    public boolean isUsernameExists(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE " + COLUMN_USERNAME + "=?",
                new String[]{username});

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }
    public boolean registerUser(String username,
                                String loginId,
                                String shopName,
                                String mobile,
                                String gst,
                                String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_USER_LOGIN_ID, loginId);
        values.put(COLUMN_USER_SHOP, shopName);
        values.put(COLUMN_USER_MOBILE, mobile);
        values.put(COLUMN_USER_GST, gst);
        values.put(COLUMN_USER_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);

        db.close();

        return result != -1;
    }
    public int loginUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_USER_ID +
                        " FROM " + TABLE_USERS +
                        " WHERE " + COLUMN_USERNAME + "=? AND " +
                        COLUMN_USER_PASSWORD + "=?",
                new String[]{username, password});

        int userId = -1;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
        }

        cursor.close();
        db.close();

        return userId;
    }
    public boolean changePassword(String username,
                                  String currentPassword,
                                  String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE " +
                        COLUMN_USERNAME + "=? AND " +
                        COLUMN_USER_PASSWORD + "=?",
                new String[]{username, currentPassword});

        if (!cursor.moveToFirst()) {

            cursor.close();
            db.close();
            return false;
        }

        cursor.close();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_PASSWORD, newPassword);

        int rows = db.update(
                TABLE_USERS,
                values,
                COLUMN_USERNAME + "=?",
                new String[]{username});

        db.close();

        return rows > 0;
    }
    public boolean updatePassword(String username,
                                  String oldPassword,
                                  String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE " +
                        COLUMN_USERNAME + "=? AND " +
                        COLUMN_USER_PASSWORD + "=?",
                new String[]{username, oldPassword});

        if (cursor.moveToFirst()) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_PASSWORD, newPassword);

            db.update(
                    TABLE_USERS,
                    values,
                    COLUMN_USERNAME + "=?",
                    new String[]{username});

            cursor.close();
            db.close();

            return true;
        }

        cursor.close();
        db.close();

        return false;
    }

}