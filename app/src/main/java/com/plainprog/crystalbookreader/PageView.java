package com.plainprog.crystalbookreader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PageView extends View {
    private Context context;
    private TextPaintCollection paints;
    private Page page;
    private Page nextPage;
    private Page previousPage;
    private Paddings paddings;
    private float screenWidth;
    private float screenHeight;
    //bool mIsOriginal;
    //darchSentence mDarchSentence;
    //Android.Graphics.Color mColor = Color.Black;
    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public PageView(Context context, TextPaintCollection paints, Page page, Paddings paddings, float screenWidth, float screenHeight) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.paints = paints;
        this.context = context;
        this.page = page;
        this.paddings = paddings;
        //mIsOriginal = true;
        //mDarchSentence = darchSentence;
        //mColor = color;
    }
    public PageView(Context context, TextPaintCollection paints, Page page, Paddings paddings, float screenWidth, float screenHeight,  Page nextPage, Page previousPage) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.paints = paints;
        this.context = context;
        this.page = page;
        this.paddings = paddings;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        //mIsOriginal = true;
        //mDarchSentence = darchSentence;
        //mColor = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DrawPage(page, canvas);
    }

    private void DrawChapter(PaginatedChapter chapter, Canvas canvas)
    {
        for(Page page : chapter.getPages())
            DrawPage(page, canvas);
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
                DrawLine(line, canvas, currentheight + lineHeight + lineSpacing, screenWidth - paddings.getPaddingLeft() - paddings.getPaddingRight());
                currentheight += lineHeight + lineSpacing;
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

}
