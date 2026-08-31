# Vyapaar-One – Smart Business Management Application

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Java-orange" alt="Language">
  <img src="https://img.shields.io/badge/UI-XML-blue" alt="UI">
  <img src="https://img.shields.io/badge/Database-SQLite-lightgrey" alt="Database">
  <img src="https://img.shields.io/badge/Status-In%20Development-yellow" alt="Status">
</p>

## 📱 About the Project

**Vyapaar-One** is a smart and user-friendly Android application designed to help small shopkeepers, retailers, wholesalers, grocery stores, and other small businesses manage their daily business operations digitally.

The application focuses on providing essential business management features such as **product and inventory management, customer management, billing, payments, customer ledger, supplier management, and business reports** in a simple interface.

The application is designed with an **offline-first approach**, allowing core business operations to work without requiring a continuous internet connection.

---

## 🎯 Problem Statement

Many small businesses still manage their inventory, customer accounts, sales, purchases, and payments using notebooks or spreadsheets.

This can result in:

- Difficulty tracking available stock
- Manual calculation errors
- Difficulty maintaining customer balances
- Problems managing invoices and payments
- Time-consuming record keeping
- Difficulty analyzing business performance
- Loss or duplication of important records

**Vyapaar-One** aims to provide a simple digital solution for these problems through a single Android application.

---

## 💡 Project Objective

The main objectives of Vyapaar-One are:

- Digitize daily business operations
- Simplify inventory management
- Manage customer information and balances
- Generate bills and invoices
- Record full and partial payments
- Maintain customer ledgers
- Manage supplier information
- Reduce manual calculations
- Provide useful business reports
- Work with an offline local database
- Provide a simple and easy-to-use interface

---

## ✨ Key Features

### 🔐 User Authentication

- User Registration
- User Login
- Password visibility option
- User profile management
- Secure access to application features

---

### 📊 Dashboard

The dashboard provides access to the major business modules from a centralized interface.

Main sections include:

- Inventory
- Orders
- Billing
- Customers
- Suppliers
- Reports
- Profile

---

### 📦 Inventory Management

Vyapaar-One provides tools to manage products and stock.

Features include:

- Add products
- View products
- Update product information
- Delete products
- Manage stock quantities
- Product pricing
- Automatic price calculation
- Decimal quantity support
- Multiple units support
- Stock alerts

The inventory module helps shopkeepers keep track of their available products without maintaining manual records.

---

### 🔎 Product Search

The application provides product searching functionality using a RecyclerView-based product list.

Users can quickly find products instead of manually searching through a large inventory.

---

### 🧾 Billing & Invoicing

Vyapaar-One includes a billing system for creating customer invoices.

Billing functionality includes:

- Create invoices
- Add products to bills
- Automatic amount calculation
- GST calculation
- Payment recording
- Full payment
- Partial payment
- Invoice generation
- PDF invoice
- Print invoice
- Share invoice

This reduces manual calculation and makes the billing process faster and more organized.

---

### 👥 Customer Management

The customer module allows businesses to maintain customer information.

Customer records can include:

- Customer name
- Phone number
- Email
- Address
- GST information
- Customer balance
- Additional notes

---

### 📒 Customer Ledger

The customer ledger helps track the financial relationship between the business and its customers.

It can be used to maintain:

- Customer outstanding balance
- Payments received
- Partial payments
- Full payments
- Transaction history

This is useful for businesses that sell products on credit.

---

### 🚚 Supplier Management

Vyapaar-One also includes supplier management functionality.

Supplier information can include:

- Supplier name
- Company name
- Phone number
- Email
- GST number
- Address
- City
- State
- Pincode
- Opening balance
- Balance type
- Notes

The supplier module helps businesses maintain organized supplier records.

---

### 🛒 Purchase Management

A purchase management module is being developed to manage purchases from suppliers.

The planned functionality includes connecting purchases with supplier records and maintaining purchase-related business information.

---

### 📈 Reports

The project roadmap includes a dedicated reporting module for helping business owners understand their business activity.

Planned reports include useful information related to:

- Sales
- Purchases
- Inventory
- Payments
- Business performance

---

## 📴 Offline-First Application

One of the important design goals of Vyapaar-One is to support business operations without requiring a constant internet connection.

Business data is stored locally using **SQLite**, allowing users to access and manage their records offline.

This approach is particularly useful for small businesses where reliable internet connectivity may not always be available.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java** | Application development |
| **Android Studio** | Development environment |
| **XML** | User interface design |
| **SQLite** | Local database |
| **RecyclerView** | Displaying lists of products and records |
| **Android SDK** | Android application development |
| **Git & GitHub** | Version control and project hosting |

---

## 🗄️ Database

Vyapaar-One uses **SQLite** as its local database.

The database is named:

```text
VyapaarOne.db

supplier_id
supplier_name
company_name
supplier_phone
supplier_email
supplier_gst
supplier_address
supplier_city
supplier_state
supplier_pincode
opening_balance
balance_type
supplier_notes

Vyapaar-One
                         │
                         ▼
                 ┌───────────────┐
                 │ Authentication│
                 └───────┬───────┘
                         │
                         ▼
                 ┌───────────────┐
                 │   Dashboard   │
                 └───────┬───────┘
                         │
       ┌─────────────────┼─────────────────┐
       │                 │                 │
       ▼                 ▼                 ▼
   Inventory          Billing          Customers
       │                 │                 │
       │                 ▼                 ▼
       │              Payments          Ledger
       │
       ▼
   Suppliers
       │
       ▼
   Purchases
       │
       ▼
     Reports

<img width="716" height="1599" alt="WhatsApp Image 2026-08-31 at 7 28 22 PM" src="https://github.com/user-attachments/assets/ff2e7443-c3e3-4a95-89ec-94b0bebfb413" />
<img width="716" height="1599" alt="WhatsApp Image 2026-08-31 at 7 28 22 PM (1)" src="https://github.com/user-attachments/assets/5cb36e27-cd3d-4c57-9f92-057a0940da3e" />
<img width="716" height="1599" alt="WhatsApp Image 2026-08-31 at 7 28 22 PM (2)" src="https://github.com/user-attachments/assets/a55c4bce-ab02-4f3a-a9a9-44795448aeff" />
<img width="716" height="1600" alt="WhatsApp Image 2026-08-31 at 7 28 23 PM (1)" src="https://github.com/user-attachments/assets/a9d3ee9f-e896-4106-9e98-fc7da81d84e6" />
<img width="716" height="1599" alt="WhatsApp Image 2026-08-31 at 7 28 23 PM" src="https://github.com/user-attachments/assets/20697fb5-8218-45d3-9d3e-6962ee3f3df3" />

Vyapaar-One/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── .../
│           │       │
│           │       ├── MainActivity.java
│           │       ├── LoginActivity.java
│           │       ├── RegisterActivity.java
│           │       ├── DashboardActivity.java
│           │       ├── AddProductActivity.java
│           │       ├── ProductListActivity.java
│           │       ├── ProfileActivity.java
│           │       │
│           │       ├── Supplier.java
│           │       ├── AddSupplierActivity.java
│           │       ├── SupplierListActivity.java
│           │       ├── SupplierAdapter.java
│           │       │
│           │       ├── Product.java
│           │       ├── ProductAdapter.java
│           │       └── DatabaseHelper.java
│           │
│           ├── res/
│           │   ├── layout/
│           │   ├── drawable/
│           │   ├── mipmap/
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
└── README.md

