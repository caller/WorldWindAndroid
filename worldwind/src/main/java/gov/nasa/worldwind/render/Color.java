/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package gov.nasa.worldwind.render;

import gov.nasa.worldwind.util.Logger;

/**
 * Color with red, green, blue and alpha components. Each RGB component is a number between 0.0 and 1.0 indicating the
 * component's intensity. The alpha component is a number between 0.0 (fully transparent) and 1.0 (fully opaque)
 * indicating the color's opacity.
 */
public class Color {

    /**
     * The color's red component.
     */
    public float red = 1;

    /**
     * The color's green component.
     */
    public float green = 1;

    /**
     * The color's blue component.
     */
    public float blue = 1;

    /**
     * The color's alpha component.
     */
    public float alpha = 1;

    /**
     * Constructs a color with red, green, blue and alpha all 1.0.
     */
    public Color() {
    }

    /**
     * Constructs a color with specified red, green, blue and alpha components.
     *
     * @param red   the red component
     * @param green the green component
     * @param blue  the blue component
     * @param alpha the alpha component
     */
    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Constructs a color with components stored in a color int. Color ints are stored as packed ints as follows:
     * <code>(alpha << 24) | (red << 16) | (green << 8) | (blue)</code>. Each component is an 8 bit number between 0 and
     * 255 with 0 indicating the component's intensity.
     *
     * @param colorInt the color int specifying the components
     */
    public Color(int colorInt) {
        this.red = android.graphics.Color.red(colorInt) / 255f;
        this.green = android.graphics.Color.green(colorInt) / 255f;
        this.blue = android.graphics.Color.blue(colorInt) / 255f;
        this.alpha = android.graphics.Color.alpha(colorInt) / 255f;
    }

    /**
     * Constructs a color with the components of a specified color.
     *
     * @param color the color specifying the components
     *
     * @throws IllegalArgumentException If the color is null
     */
    public Color(Color color) {
        if (color == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "Color", "constructor", "missingColor"));
        }

        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
        this.alpha = color.alpha;
    }

    /**
     * Sets this color to the specified components.
     *
     * @param red   the new red component
     * @param green the new green component
     * @param blue  the new blue component
     * @param alpha the new alpha component
     *
     * @return this color with its components set to the specified values
     */
    public Color set(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        return this;
    }

    /**
     * Sets this color to the components stored in a color int. Color ints are stored as packed ints as follows:
     * <code>(alpha << 24) | (red << 16) | (green << 8) | (blue)</code>. Each component is an 8 bit number between 0 and
     * 255 with 0 indicating the component's intensity.
     *
     * @param colorInt the color int specifying the new components
     *
     * @return this color with its components set to those of the specified color int
     */
    public Color set(int colorInt) {
        this.red = android.graphics.Color.red(colorInt) / 255f;
        this.green = android.graphics.Color.green(colorInt) / 255f;
        this.blue = android.graphics.Color.blue(colorInt) / 255f;
        this.alpha = android.graphics.Color.alpha(colorInt) / 255f;
        return this;
    }

    /**
     * Sets this color to the components of a specified color.
     *
     * @param color the color specifying the new components
     *
     * @return this color with its components set to that of the specified color
     *
     * @throws IllegalArgumentException If the color is null
     */
    public Color set(Color color) {
        if (color == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "Color", "set", "missingColor"));
        }

        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
        this.alpha = color.alpha;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Color that = (Color) o;
        return this.red == that.red
            && this.green == that.green
            && this.blue == that.blue
            && this.alpha == that.alpha;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(red);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(green);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(blue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(alpha);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(r=" + this.red + ", g=" + this.green + ", b=" + this.blue + ", a=" + this.alpha + ")";
    }

    /**
     * Copies this color's components to the specified array. The result is compatible with GLSL uniform vectors, and
     * can be passed to the function glUniform4fv.
     *
     * @param result a pre-allocated array of length 4 in which to return the components
     *
     * @return the result argument set to this vector's components
     */
    public float[] toArray(float[] result, int offset) {
        if (result == null || result.length - offset < 4) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "Color", "toArray", "missingResult"));
        }

        result[offset++] = this.red;
        result[offset++] = this.green;
        result[offset++] = this.blue;
        result[offset] = this.alpha;

        return result;
    }
}
