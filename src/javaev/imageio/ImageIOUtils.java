package javaev.imageio;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import javaev.io.FileUtils;
import javaev.util.ArraysUtils;

public class ImageIOUtils {
	public static String FileSuffixes_Default = ".png";
	public static String FormatName_Default = "png";

	private FileUtils fileUtils = FileUtils.getInstance();

	public BufferedImage read(File file) {
		if (null != file) {
			try {
				return ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void read(List<File> fileList, List<BufferedImage> imageList) {
		if (null != fileList && null != imageList) {
			for (Iterator<File> iterator = fileList.iterator(); iterator.hasNext();) {
				imageList.add(read(iterator.next()));
			}
		}
	}

	public void write(BufferedImage image, String formatName, File file) {
		if (null != image && null != file) {
			if (null == formatName || !ArraysUtils.contains(ImageIO.getWriterFormatNames(), formatName)) {
				formatName = FormatName_Default;
			}
			try {
				if (file.isDirectory()) {
					file = fileUtils.getFile(file, FileSuffixes_Default);
				}
				File dir = file.getParentFile();
				if(!dir.exists()) {
					dir.mkdir();
				}
				ImageIO.write(image, formatName, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
