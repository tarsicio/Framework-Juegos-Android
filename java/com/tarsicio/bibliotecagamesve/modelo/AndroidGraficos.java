package com.tarsicio.bibliotecagamesve.modelo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.tarsicio.bibliotecagamesve.interfaces.Graficos;
import com.tarsicio.bibliotecagamesve.interfaces.Pixmap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Graficos
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class AndroidGraficos implements Graficos {

    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;

    /**
     *
     * @param assets
     * @param frameBuffer
     */

    public AndroidGraficos(AssetManager assets, Bitmap frameBuffer){
        setAssets(assets);
        setFrameBuffer(frameBuffer);
        setCanvas(frameBuffer);
        setPaint();
        setSrcRect();
        setDstRect();
    }

    /**
     *
     * @param fileName
     * @param format
     * @return
     */
    @Override
    public Pixmap nuevoMapaPixeles(String fileName, formaMapaPixeles format){
        Bitmap.Config config = null;
        if (format == formaMapaPixeles.ARGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == formaMapaPixeles.ARGB4444)
                config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;

        try {
            in = getAssets().open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("No se ha podido cargar bitmap desde el Assets'" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido cargar bitmap desde el Assets'" + fileName + "'");
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e){
                    //Nada
                }
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = formaMapaPixeles.ARGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = formaMapaPixeles.ARGB4444;
        else
            format= formaMapaPixeles.ARGB8888;

        return  new AndroidPixmap(bitmap,format);
    }

    /**
     *
     * @param color
     */
    @Override
    public void limpiar(int color){
        getCanvas().drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    /**
     *
     * @param x
     * @param y
     * @param color
     */
    @Override
    public void dibujarPixel(int x, int y, int color){
        getPaint().setColor(color);
        getCanvas().drawPoint(x,y,getPaint());
    }

    /**
     *
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    @Override
    public void dibujarLinea(int x, int y, int x2, int y2, int color){
        getPaint().setColor(color);
        getCanvas().drawLine(x,y,x2,y2,getPaint());
    }

    /**
     *
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @param color
     */
    @Override
    public void dibujarRectangulo(int x, int y, int ancho, int alto, int color){
        getPaint().setColor(color);
        getPaint().setStyle(Paint.Style.FILL);
        getCanvas().drawRect(x,y,x + ancho - 1,y + ancho - 1,getPaint());
    }

    /**
     *
     * @param pixmap
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    @Override
    public  void dibujarMapaPixeles(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight){
        getSrcRect().left = srcX;
        getSrcRect().top = srcY;
        getSrcRect().right = srcY + srcWidth -1;
        getSrcRect().bottom = srcY + srcHeight -1;

        getDstRect().left = x;
        getDstRect().top = y;
        getDstRect().right = x + srcWidth -1;
        getDstRect().bottom = y + srcHeight -1;

        getCanvas().drawBitmap(((AndroidPixmap) pixmap).getBitmap(),getSrcRect(),getDstRect(),null);
    }

    /**
     *
     * @param pixmap
     * @param x
     * @param y
     */
    @Override
    public void dibujarMapaPixeles(Pixmap pixmap, int x, int y){
        getCanvas().drawBitmap(((AndroidPixmap) pixmap).getBitmap(),x,y,null);
    }

    @Override
    public int getAncho(){
        return frameBuffer.getWidth();
    }

    @Override
    public int getAlto(){
        return frameBuffer.getHeight();
    }

    /************************ SET *****************************************************
     *
     */
    /**
     *
     * @param assets
     */
    private void setAssets(AssetManager assets){
        this.assets = assets;
    }

    private void setFrameBuffer(Bitmap frameBuffer){
        this.frameBuffer = frameBuffer;
    }

    private void setCanvas(Bitmap frameBuffer){
        this.canvas = new Canvas(frameBuffer);
    }

    private void setPaint(){
        this.paint = new Paint();
    }

    private void setSrcRect(){
        this.srcRect = new Rect();
    }

    private void setDstRect(){
        this.dstRect = new Rect();
    }

    /******************************************** GET ***********************************
     *
     */

    public AssetManager getAssets(){
        return this.assets;
    }

    public Bitmap getFrameBuffer(){
        return  this.frameBuffer;
    }

    public Canvas getCanvas(){
        return this.canvas;
    }

    public Paint getPaint(){
        return this.paint;
    }

    public Rect getSrcRect(){
        return this.srcRect;
    }

    public Rect getDstRect(){
        return this.dstRect;
    }
}
