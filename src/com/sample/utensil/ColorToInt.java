package com.sample.utensil;

import javafx.scene.paint.Color;

public class ColorToInt {
    /**
     * Color类型转int类型
     */
    public static int getColorToInt(Color color) {

        float alpha = (float) color.getOpacity();
        float red = (float) color.getRed();
        float green = (float) color.getGreen();
        float blue = (float) color.getBlue();

        int A = Math.round(255 * alpha);
        int R = Math.round(255 * red);
        int G = Math.round(255 * green);
        int B = Math.round(255 * blue);

        A = (A << 24) & 0xFF000000;
        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = (B) & 0x000000FF;

        return A | R | G | B;


    }


}
