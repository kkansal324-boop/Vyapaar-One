package com.example.vyapaarone;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

public class PdfReportHelper {

    public static void generateReport(

            Context context,

            String todaySales,
            String monthSales,
            String revenue,
            String orders,

            String totalProducts,
            String inventoryValue,
            String lowStockCount,
            String outOfStock,

            String pendingAmount,
            String customerDue,

            String topProducts,
            String lowStockProducts

    ) {

        PdfDocument pdfDocument = new PdfDocument();

        Paint titlePaint = new Paint();
        titlePaint.setTextSize(22);
        titlePaint.setFakeBoldText(true);

        Paint headingPaint = new Paint();
        headingPaint.setTextSize(17);
        headingPaint.setFakeBoldText(true);

        Paint textPaint = new Paint();
        textPaint.setTextSize(14);

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(595,842,1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        int y = 40;

        page.getCanvas().drawText(
                "VYAPAAR ONE",
                180,
                y,
                titlePaint
        );

        y += 30;

        page.getCanvas().drawText(
                "Business Report",
                210,
                y,
                headingPaint
        );

        y += 35;

        page.getCanvas().drawLine(30,y,560,y,textPaint);

        y += 30;

        page.getCanvas().drawText("Today's Sales : " + todaySales,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Monthly Sales : " + monthSales,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Total Revenue : " + revenue,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Total Orders : " + orders,30,y,textPaint);
        y+=35;

        page.getCanvas().drawText("Inventory Report",30,y,headingPaint);
        y+=25;

        page.getCanvas().drawText("Products : " + totalProducts,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Inventory Value : " + inventoryValue,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Low Stock : " + lowStockCount,30,y,textPaint);
        y+=22;

        page.getCanvas().drawText("Out Of Stock : " + outOfStock,30,y,textPaint);
        y+=35;

        page.getCanvas().drawText("Customer Due",30,y,headingPaint);
        y+=25;

        page.getCanvas().drawText("Total Pending : " + pendingAmount,30,y,textPaint);
        y+=25;

        for(String line : customerDue.split("\n")){

            page.getCanvas().drawText(line,30,y,textPaint);

            y+=18;
        }

        y+=20;

        page.getCanvas().drawText("Top Selling Products",30,y,headingPaint);

        y+=25;

        for(String line : topProducts.split("\n")){

            page.getCanvas().drawText(line,30,y,textPaint);

            y+=18;
        }

        y+=20;

        page.getCanvas().drawText("Low Stock Products",30,y,headingPaint);

        y+=25;

        for(String line : lowStockProducts.split("\n")){

            page.getCanvas().drawText(line,30,y,textPaint);

            y+=18;
        }

        y+=35;

        page.getCanvas().drawText(
                "Generated : "
                        + new SimpleDateFormat(
                        "dd-MM-yyyy HH:mm",
                        Locale.getDefault()).format(new Date()),
                30,
                y,
                textPaint
        );

        pdfDocument.finishPage(page);

        try{

            File folder = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

            if(folder!=null && !folder.exists()){

                folder.mkdirs();
            }

            File file = new File(
                    folder,
                    "VyapaarOne_Report.pdf"
            );

            FileOutputStream fos = new FileOutputStream(file);

            pdfDocument.writeTo(fos);

            fos.close();

            pdfDocument.close();

            new AlertDialog.Builder(context)

                    .setTitle("Report Exported")

                    .setMessage("PDF saved successfully.\n\nWhat would you like to do?")

                    .setPositiveButton("Open", (dialog, which) -> {

                        try {

                            Uri uri = FileProvider.getUriForFile(

                                    context,

                                    context.getPackageName() + ".provider",

                                    file
                            );

                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            intent.setDataAndType(uri, "application/pdf");

                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            context.startActivity(intent);

                        } catch (Exception e) {

                            Toast.makeText(
                                    context,
                                    "No PDF Viewer Installed",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    })

                    .setNeutralButton("Share", (dialog, which) -> {

                        Uri uri = FileProvider.getUriForFile(

                                context,

                                context.getPackageName() + ".provider",

                                file
                        );

                        Intent share = new Intent(Intent.ACTION_SEND);

                        share.setType("application/pdf");

                        share.putExtra(Intent.EXTRA_STREAM, uri);

                        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        context.startActivity(

                                Intent.createChooser(
                                        share,
                                        "Share Report"
                                )
                        );

                    })

                    .setNegativeButton("Close", null)

                    .show();

        }

        catch (Exception e){

            Toast.makeText(
                    context,
                    e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }

    }

}