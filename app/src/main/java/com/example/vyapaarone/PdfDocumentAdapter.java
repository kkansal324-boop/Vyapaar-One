package com.example.vyapaarone;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfDocumentAdapter extends PrintDocumentAdapter {

    private final Context context;
    private final String pdfPath;

    public PdfDocumentAdapter(Context context, String pdfPath) {
        this.context = context;
        this.pdfPath = pdfPath;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle extras) {

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo info =
                new PrintDocumentInfo.Builder("Invoice.pdf")
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .build();

        callback.onLayoutFinished(info, true);
    }

    @Override
    public void onWrite(android.print.PageRange[] pages,
                        ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        WriteResultCallback callback) {

        try {

            FileInputStream input =
                    new FileInputStream(pdfPath);

            FileOutputStream output =
                    new FileOutputStream(destination.getFileDescriptor());

            byte[] buffer = new byte[4096];

            int size;

            while ((size = input.read(buffer)) > 0) {
                output.write(buffer, 0, size);
            }

            input.close();
            output.close();

            callback.onWriteFinished(
                    new android.print.PageRange[]{
                            android.print.PageRange.ALL_PAGES
                    });

        } catch (IOException e) {

            callback.onWriteFailed(e.getMessage());
        }
    }
}