package com.example.appfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ViewDibujo extends View {
    public ViewGroup.LayoutParams lp;
    private Path path= new Path();
    private Paint dibujar = new Paint();

    public ViewDibujo(Context context) {
        super(context);
        dibujar.setAntiAlias(true);
        dibujar.setColor(Color.rgb(0,0,0));
        dibujar.setStyle(Paint.Style.STROKE);
        dibujar.setStrokeJoin(Paint.Join.ROUND);
        dibujar.setStrokeWidth(8f);
        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,dibujar);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

}
