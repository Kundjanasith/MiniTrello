package com.example.exceed.minitrello.models;

public class ColorAll {
    private int[] textColor;
    private int[] toolbarColor;
    private int[] backgroundColor;

    public ColorAll(int[] textColor, int[] toolbarColor, int[] backgroundColor) {
        this.textColor = textColor;
        this.toolbarColor = toolbarColor;
        this.backgroundColor = backgroundColor;
    }

    public int[] getTextColor() {
        return textColor;
    }

    public void setTextColor(int[] textColor) {
        this.textColor = textColor;
    }

    public int[] getToolbarColor() {
        return toolbarColor;
    }

    public void setToolbarColor(int[] toolbarColor) {
        this.toolbarColor = toolbarColor;
    }

    public int[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
