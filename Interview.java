package pinal.patel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import pinal.patel.R;

public class Interview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "interview.pdf");
        try
        {
            in = assetManager.open("interview.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(this, MyPdfViewerActivity.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, getFilesDir()+"/interview.pdf");
        startActivity(intent);
        finish();
/*
	    Intent intent = new Intent(Intent.ACTION_VIEW);
	    intent.setDataAndType(
	            Uri.parse("file://" + getFilesDir() + "/ABC.pdf"),
	            "application/pdf");

	    startActivity(intent);*/
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }
}
