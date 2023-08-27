package javaev.awt;

import java.awt.Dimension;

import javaev.lang.ObjectUtils;

public class DimensionUtils {
	public int height;

	private double height_;

	private boolean isNull;

	private String tag_bracket_close = ObjectUtils.tag_bracket_close;
	private String tag_bracket_open = ObjectUtils.tag_bracket_open;
	private String tag_comma = ObjectUtils.tag_comma;
	private String tag_equals = ObjectUtils.tag_equals;
	private String tag_height = ObjectUtils.tag_height;

	private String tag_width = ObjectUtils.tag_width;

	public int width;

	private double width_;

	public DimensionUtils() {
	}

	public DimensionUtils(Dimension dimension) {
		set(dimension);
	}

	public DimensionUtils(DimensionUtils dimension) {
		set(dimension);
	}

	public DimensionUtils(double width, double height) {
		setSize(width, height);
	}

	public DimensionUtils(int width, int height) {
		setSize(width, height);
	}

	public Dimension get() {
		if (isNull) {
			return null;
		} else {
			Dimension dimension = new Dimension();
			dimension.setSize(getWidth(), getHeight());
			return dimension;
		}
	}

	public double getHeight() {
		if (isNull) {
			return -1;
		} else {
			if (height_ == 0) {
				height_ = height;
			}
			return height_;
		}
	}

	public double getWidth() {
		if (isNull) {
			return -1;
		} else {
			if (width_ == 0) {
				width_ = width;
			}
			return width_;
		}
	}

	public void set(Dimension dimension) {
		if (null != dimension) {
			setSize(dimension.getWidth(), dimension.getHeight());
			isNull = false;
		} else {
			isNull = true;
		}
	}

	public void set(DimensionUtils dimension) {
		if (null != dimension) {
			setSize(dimension.getWidth(), dimension.getHeight());
			isNull = false;
		} else {
			isNull = true;
		}
	}

	public void setSize(double width, double height) {
		this.width_ = width;
		this.height_ = height;
		setSize((int) width, (int) height);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public String toString() {
		if (isNull) {
			return null;
		} else {
			StringBuffer sbuf = new StringBuffer();
			sbuf.append(this.getClass().getName());
			sbuf.append(tag_bracket_open);
			sbuf.append(tag_width);
			sbuf.append(tag_equals);
			sbuf.append(getWidth());
			sbuf.append(tag_comma);
			sbuf.append(tag_height);
			sbuf.append(tag_equals);
			sbuf.append(getHeight());
			sbuf.append(tag_bracket_close);
			return sbuf.toString();
		}
	}
}
