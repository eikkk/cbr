package com.plainprog.crystalbookreader;

public class Paddings {
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;
    private float paddingBottom;

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
    }


    public Paddings(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
    }

    public Paddings() {
        this.paddingLeft = 0f;
        this.paddingRight = 0f;
        this.paddingTop = 0f;
        this.paddingBottom = 0f;

    }
}
