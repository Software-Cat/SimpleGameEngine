package io.github.softwarecat.simplegameengine.math;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class Vector2 extends Point2D {

    /**
     * Point or vector with both coordinates set to 0.
     */
    public static final Vector2 ZERO = new Vector2(0.0, 0.0);

    /**
     * Cache the hash code to make computing hashes faster.
     */
    private int hash = 0;

    /**
     * Creates a new instance of {@code Vector2}.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Vector2(double x, double y) {
        super(x, y);
    }

    /**
     * Returns a point with the specified coordinates added to the coordinates of this point.
     *
     * @param x the X coordinate addition
     * @param y the Y coordinate addition
     * @return the point with added coordinates
     * @since JavaFX 8.0
     */
    public Vector2 add(double x, double y) {
        return new Vector2(
                getX() + x,
                getY() + y);
    }

    /**
     * Returns a point with the coordinates of the specified point added to the coordinates of this point.
     *
     * @param point the point whose coordinates are to be added
     * @return the point with added coordinates
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Vector2 add(Vector2 point) {
        return add(point.getX(), point.getY());
    }

    /**
     * Returns a point with the specified coordinates subtracted from the coordinates of this point.
     *
     * @param x the X coordinate subtraction
     * @param y the Y coordinate subtraction
     * @return the point with subtracted coordinates
     * @since JavaFX 8.0
     */
    public Vector2 subtract(double x, double y) {
        return new Vector2(
                getX() - x,
                getY() - y);
    }

    /**
     * Returns a point with the coordinates of this point multiplied by the specified factor
     *
     * @param factor the factor multiplying the coordinates
     * @return the point with multiplied coordinates
     * @since JavaFX 8.0
     */
    public Vector2 multiply(double factor) {
        return new Vector2(getX() * factor, getY() * factor);
    }

    /**
     * Returns a point with the coordinates of the specified point subtracted from the coordinates of this point.
     *
     * @param point the point whose coordinates are to be subtracted
     * @return the point with subtracted coordinates
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Vector2 subtract(Vector2 point) {
        return subtract(point.getX(), point.getY());
    }

    /**
     * Normalizes the relative magnitude vector represented by this instance. Returns a vector with the same direction
     * and magnitude equal to 1. If this is a zero vector, a zero vector is returned.
     *
     * @return the normalized vector represented by a {@code Point2D} instance
     * @since JavaFX 8.0
     */
    public Vector2 normalize() {
        final double mag = magnitude();

        if (mag == 0.0) {
            return new Vector2(0.0, 0.0);
        }

        return new Vector2(
                getX() / mag,
                getY() / mag);
    }

    /**
     * Returns a point which lies in the middle between this point and the specified coordinates.
     *
     * @param x the X coordinate of the second endpoint
     * @param y the Y coordinate of the second endpoint
     * @return the point in the middle
     * @since JavaFX 8.0
     */
    public Vector2 midpoint(double x, double y) {
        return new Vector2(
                x + (getX() - x) / 2.0,
                y + (getY() - y) / 2.0);
    }

    /**
     * Returns a point which lies in the middle between this point and the specified point.
     *
     * @param point the other endpoint
     * @return the point in the middle
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Vector2 midpoint(Vector2 point) {
        return midpoint(point.getX(), point.getY());
    }


    /**
     * Computes the angle (in degrees) between the vector represented by this point and the vector represented by the
     * specified point.
     *
     * @param point the other vector
     * @return the angle between the two vectors measured in degrees, {@code NaN} if any of the two vectors is a zero
     * vector
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public double angle(Vector2 point) {
        return angle(point.getX(), point.getY());
    }

    /**
     * Computes the angle (in degrees) between the three points with this point as a vertex.
     *
     * @param p1 one point
     * @param p2 other point
     * @return angle between the vectors (this, p1) and (this, p2) measured in degrees, {@code NaN} if the three points
     * are not different from one another
     * @throws NullPointerException if {@code p1} or {@code p2} is null
     * @since JavaFX 8.0
     */
    public double angle(Vector2 p1, Vector2 p2) {
        final double x = getX();
        final double y = getY();

        final double ax = p1.getX() - x;
        final double ay = p1.getY() - y;
        final double bx = p2.getX() - x;
        final double by = p2.getY() - y;

        final double delta = (ax * bx + ay * by) / Math.sqrt(
                (ax * ax + ay * ay) * (bx * bx + by * by));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance and the specified vector.
     *
     * @param vector the other vector
     * @return the dot product of the two vectors
     * @throws NullPointerException if the specified {@code vector} is null
     * @since JavaFX 8.0
     */
    public double dotProduct(Vector2 vector) {
        return dotProduct(vector.getX(), vector.getY());
    }

    /**
     * Computes cross product of the vector represented by this instance and the specified vector.
     *
     * @param vector the other vector
     * @return the cross product of the two vectors
     * @throws NullPointerException if the specified {@code vector} is null
     * @since JavaFX 8.0
     */
    public Point3D crossProduct(Vector2 vector) {
        return crossProduct(vector.getX(), vector.getY());
    }

    /**
     * {@inheritDoc}
     *
     * @since 13
     */
    @Override
    public Vector2 interpolate(Point2D endValue, double t) {
        if (t <= 0.0) return this;
        if (t >= 1.0) return new Vector2(endValue.getX(), endValue.getY());
        return new Vector2(
                getX() + (endValue.getX() - getX()) * t,
                getY() + (endValue.getY() - getY()) * t
        );
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this point is the same as the obj argument; false otherwise
     */
    @Override public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Vector2) {
            Vector2 other = (Vector2) obj;
            return getX() == other.getX() && getY() == other.getY();
        } else return false;
    }

    /**
     * Returns a hash code value for the point.
     * @return a hash code value for the point.
     */
    @Override public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getX());
            bits = 31L * bits + Double.doubleToLongBits(getY());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }

    /**
     * Returns a string representation of this {@code Point2D}.
     * This method is intended to be used only for informational purposes.
     * The content and format of the returned string might vary between
     * implementations.
     * The returned string might be empty but cannot be {@code null}.
     */
    @Override public String toString() {
        return "Vector2 [x = " + getX() + ", y = " + getY() + "]";
    }
}
