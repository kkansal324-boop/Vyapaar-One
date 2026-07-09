package com.example.vyapaarone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;

public class PdfInvoiceGenerator {

    public static File createInvoicePdf(Context context) {

        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(
                        595,
                        842,
                        1)
                        .create();

        PdfDocument.Page page =
                document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint titlePaint = new Paint();
        titlePaint.setTextSize(24);
        titlePaint.setFakeBoldText(true);

        Paint textPaint = new Paint();
        textPaint.setTextSize(14);

        canvas.drawText(
                "Vyapaar One",
                180,
                50,
                titlePaint);

        canvas.drawText(
                "Business Management System",
                150,
                75,
                textPaint);

        canvas.drawText(
                "TAX INVOICE",
                220,
                120,
                titlePaint);

        document.finishPage(page);

        File pdfFile =
                new File(
                        context.getExternalFilesDir(null),
                        "Invoice.pdf");

        try {

            FileOutputStream outputStream =
                    new FileOutputStream(pdfFile);

            document.writeTo(outputStream);

            outputStream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        document.close();

        return pdfFile;
    }

}