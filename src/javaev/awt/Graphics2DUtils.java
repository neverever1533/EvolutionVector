package javaev.awt;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Graphics2DUtils {
	public BufferedImage drawImage(BufferedImage image, AffineTransform xform, ImageObserver obs) {
		if (null != image) {
			BufferedImage imageRoot = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			return drawImage(imageRoot, image, xform, -1, obs);
		}
		return image;
	}

//	public BufferedImage drawImage(BufferedImage imageRoot, BufferedImage image, AffineTransform xform, float alpha,
//			ImageObserver obs) {
//		if (null != imageRoot) {
//			Graphics2D graphics2d = imageRoot.createGraphics();
//			if (alpha >= 0 && alpha <= 1) {
//				AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//				graphics2d.setComposite(alphaComposite);
//			}
//			graphics2d.drawImage(image, xform, obs);
//			graphics2d.dispose();
//			return imageRoot;
//		}
//		return imageRoot;
//	}

	public BufferedImage drawImage(BufferedImage imageRoot, BufferedImage image, AffineTransform xform, float alpha,
			ImageObserver obs) {
		if (null != imageRoot) {
			Graphics2D graphics2d = imageRoot.createGraphics();
			drawImage(graphics2d, image, xform, alpha, obs);
			graphics2d.dispose();
			return imageRoot;
		}
		return imageRoot;
	}

	public void drawImage(Graphics2D graphics2d, BufferedImage image, AffineTransform xform, float alpha,
			ImageObserver obs) {
		if (null != graphics2d) {
			if (alpha >= 0 && alpha <= 1) {
				AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
				graphics2d.setComposite(alphaComposite);
			}
			graphics2d.drawImage(image, xform, obs);
		}
	}

	public BufferedImage drawImage(int width, int height, BufferedImage image, AffineTransform xform, float alpha,
			ImageObserver obs) {
		if (width > 0 && height > 0) {
			BufferedImage imageRoot = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			return drawImage(imageRoot, image, xform, alpha, obs);
		}
		return image;
	}

	public BufferedImage filterImage(BufferedImage src, BufferedImage dst, AffineTransform affineTransform) {
		int interpolationType = AffineTransformOp.TYPE_NEAREST_NEIGHBOR;
		return filterImage(src, dst, affineTransform, interpolationType);
	}

	public BufferedImage filterImage(BufferedImage src, BufferedImage dst, AffineTransform affineTransform,
			int interpolationType) {
		if (null != affineTransform) {
			AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, interpolationType);
			return affineTransformOp.filter(src, dst);
		}
		return src;
	}

}
