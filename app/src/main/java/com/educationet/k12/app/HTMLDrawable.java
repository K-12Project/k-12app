package com.educationet.k12.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/***
 * Created by K12-Dev-Team on 20/03/2015.
 */

/**
 * Vecchi gestore delle immagini
 * @author K12-Dev-Team
 * @deprecated
 */
@Deprecated
public class HTMLDrawable extends AsyncTask<Void,Void,Drawable> {
    private String site = "";
    public HTMLDrawable(String site) {
        this.site=site;
    }
    private Bitmap fetchImage()
    {
        try
        {
            URL url;
            url = new URL( this.site );
            HttpURLConnection c = ( HttpURLConnection ) url.openConnection();
            c.setDoInput( true );
            c.connect();
            InputStream is = c.getInputStream();
            Bitmap img;
            img = BitmapFactory.decodeStream(is);
            return img;
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        return null;
    }
    public Drawable getimg(){
        try {
            return this.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Drawable doInBackground(Void... params) {//Il metodo doinbackground specifica le azioni da eseguire in un Thread separato
        return new BitmapDrawable(fetchImage());
    }
}
