package com.plainprog.crystalbookreader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class PageView extends View {
    private Context context;
    private TextPaintCollection paints;
    private Page page;
    private Paddings paddings;
    private float screenWidth;
    private float screenHeight;
    private  GestureDetector gestureDetector;
    private boolean isCursorState;
    private boolean isCursorMovingState;
    private PointF cursorCoordinates;
    private RectF cursorActual;
    private RectF cursorForUserTouch;
    //bool mIsOriginal;
    //darchSentence mDarchSentence;
    //Android.Graphics.Color mColor = Color.Black;
    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public PageView(Context context, TextPaintCollection paints, Page page, Paddings paddings, Point displayDimensions) {
        super(context);
        this.screenWidth = displayDimensions.x;
        this.screenHeight = displayDimensions.y;
        this.paints = paints;
        this.context = context;
        this.page = page;
        this.paddings = paddings;
        gestureDetector = new GestureDetector(context, new GestureListener());
        isCursorState  = false;
        isCursorMovingState = false;
        //mIsOriginal = true;
        //mDarchSentence = darchSentence;
        //mColor = color;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        DrawPage(page, canvas);
        if (isCursorState)
            DrawSelectionCursor(canvas, cursorCoordinates.y, cursorCoordinates.x,100,"#00ff00");
    }
    private void DrawSelectionCursor(Canvas canvas, float y, float x, float stringHeight, String color){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor(color));
        float cursorWidth = 20;
        float left = x - cursorWidth;
        float top = y - stringHeight;
        float right = x;
        float bottom = y;
        cursorActual = new RectF(left,top,right,bottom);
        float additionalCursorSpace = 20;
        cursorForUserTouch = new RectF(left-additionalCursorSpace, top - additionalCursorSpace, right + additionalCursorSpace, bottom + additionalCursorSpace);
        canvas.drawRect(cursorActual,paint);
    }
    private void DrawPage(Page page, Canvas canvas)
    {
        float currentheight = 0f;
        for (int i =0; i < page.getLines().size(); i++)
        {
            Line line = page.getLines().get(i);
            float lineHeight = paints.getHelper().measureLineHeight(line);
            float lineSpacing = paints.getHelper().getLineSpacing(line);
            //setting color to sublines. we cant do it in DrawLine method because that method is used by drawing translated text and sublines have not drchsentences there
            //foreach (Subline subline in page.Lines[i].Sublines)
            //if(mDarchSentence.Equals(subline.darchSentence))
            //    subline.Color = mColor;
            //if (page.Lines[i].IsFirstInParagraph && i !=0)
            //{
                DrawLine(line, canvas, currentheight + lineHeight, screenWidth - paddings.getPaddingLeft() - paddings.getPaddingRight());
                currentheight += lineHeight /*+ lineSpacing*/;
            //}
            /*else
            {
                if (i == 0)
                {
                    DrawLine(page.Lines[i], canvas, currentheight + page.Lines[i].LineHeight, screenWidth - mPaddingLeft - paddingRight);
                    currentheight += page.Lines[i].LineHeight;
                }
                else
                {
                    DrawLine(page.Lines[i], canvas, currentheight + page.Lines[i-1].LineSpacing + page.Lines[i].LineHeight , screenWidth - mPaddingLeft - paddingRight);
                    currentheight += page.Lines[i].LineHeight + page.Lines[i-1].LineSpacing;
                }
            }*/
        }

    }
    private void DrawLine(Line line, Canvas canvas, float y, float maxWidth)
    {
        //line.EndHeight = y + paddingTop;
        //line.StartHeight = line.EndHeight - line.LineHeight;
        int partscount = 0;
        float currentwidth = 0f;
        //float spacewidth = paints.MeasureText(new Text(" ", line.Sublines[0].Content[0].Type));
        float partsWidth = 0f;

        /*foreach(Subline subline in line.Sublines)
        {
            subline.StartHeight = line.StartHeight;
            subline.EndHeight = subline.StartHeight + line.LineHeight;
            foreach (Text el in subline.Content)
            {
                partsWidth += el.Width;
                partscount++;
            }
        }*/
        //int spacescount = partscount - 1;
        //float spaceToFill = maxWidth - partsWidth;
        //float singleSpaceWidth = spaceToFill / spacescount;

        //line.Sublines[line.Sublines.Count - 1].Content[line.Sublines[line.Sublines.Count - 1].Content.Count - 1].Value = line.Sublines[line.Sublines.Count - 1].Content[line.Sublines[line.Sublines.Count - 1].Content.Count - 1].Value.TrimEnd();

            for (int q = 0; q < line.getContent().size(); q++)
            {
                //line.Sublines[q].StartWidth = currentwidth;
                    DrawText(line.getContent().get(q), canvas, currentwidth + paddings.getPaddingLeft(), y + paddings.getPaddingTop());
                    currentwidth += paints.getHelper().measureText(line.getContent().get(q));
            }


    }
    private void DrawText(Text text, Canvas canvas,float x, float y)
    {
        Paint paint = new Paint(paints.getPaintForText(text));
        paint.setColor(Color.parseColor("#000000"));
        //text.RenderHeight = y;
        //text.StartWidth = x;
        //if (!string.IsNullOrWhiteSpace(text.Value))
         canvas.drawText(text.getValue(), x, y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (isCursorState)
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (isCursorTouched(x,y)){
                    isCursorMovingState = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isCursorMovingState)
                    isCursorMovingState = false;
                break;
                case MotionEvent.ACTION_MOVE:
                    if (isCursorMovingState){
                        cursorCoordinates.set(x,y);
                        invalidate();
                    }
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private boolean isCursorTouched(float x, float y){
        if (x<= cursorForUserTouch.right && x >= cursorForUserTouch.left && y <= cursorForUserTouch.bottom && y >= cursorForUserTouch.top)
            return true;
        return  false;
    }
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public GestureListener() {
            super();
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if (!isCursorState){
                isCursorState = true;
                cursorCoordinates = new PointF(e.getX(),e.getY());
                invalidate();
            }
        }
    }
}
