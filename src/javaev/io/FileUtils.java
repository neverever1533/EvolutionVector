package javaev.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import java.nio.charset.Charset;

import java.util.Iterator;
import java.util.List;

import cn.imaginary.toolkit.image.ImageLayer;

public class FileUtils {
	private static FileUtils instance;

	public static FileUtils getInstance() {
		if (null == instance) {
			syncInit();
		}
		return instance;
	}

	private static synchronized void syncInit() {
		if (null == instance) {
			instance = new FileUtils();
		}
	}

	private String[] suffixArray;

	private FileUtils() {
	}

	private boolean checkFileArray(File[] fileArray, boolean isDirectoryOnly) {
		if (null == fileArray) {
			return false;
		}
		File file;
		for (int i = 0; i < fileArray.length; i++) {
			file = fileArray[i];
			if (isDirectoryOnly) {
				if (!file.isDirectory()) {
					return false;
				}
			} else {
				if (!file.isFile()) {
					return false;
				}
			}
		}
		return true;
	}

	public String[] getCharsetArray() {
		return (String[]) Charset.availableCharsets().keySet().toArray();
	}

	public String getContentType(File file) {
		if (null != file) {
			FileNameMapUtils fnmu = new FileNameMapUtils();
			return fnmu.getContentTypeFor(file);
		}
		return null;
	}

	public File getFile(File file, Object name, boolean isReplace, String suffix) {
		if (null != file && null != suffix) {
			File dir;
			String fileName;
			if (file.isDirectory()) {
				dir = file;
				fileName = dir.getName();
			} else {
				dir = file.getParentFile();
				fileName = getFileNamePrefix(file);
			}
			StringBuffer sbuf = new StringBuffer();
			if (null != dir && null != fileName) {
				if (null == name || !isReplace) {
					sbuf.append(fileName);
				} else {
					sbuf.append(name);
				}
				sbuf.append(suffix);
				return new File(dir, sbuf.toString());
			}
		}
		return null;
	}

	public File getFile(File file, Object name, String suffix) {
		return getFile(file, name, false, suffix);
	}

	public File getFile(File file, String suffix) {
		return getFile(file, null, suffix);
	}

	public File getFile(List<File> list, String fileName) {
		if (null != list && null != fileName) {
			File file;
			for (Iterator<File> iterator = list.iterator(); iterator.hasNext();) {
				file = (File) iterator.next();
				if (null != file) {
					if (file.getAbsolutePath().endsWith(fileName)) {
						return file;
					}
				}
			}
		}
		return null;
	}

	public FileFilter getFileFilter() {
		return getFileFilter(getSuffixArray());
	}

	public FileFilter getFileFilter(final String[] suffixArray) {
		if (null != suffixArray) {
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File pathname) {
					String name = pathname.getName().toLowerCase();
					String suffix;
					for (int i = 0, iLength = suffixArray.length; i < iLength; i++) {
						suffix = suffixArray[i];
						if (pathname.isDirectory() || (null != suffix && name.endsWith(suffix))) {
							return true;
						}
					}
					return false;
				}
			};
			return fileFilter;
		}
		return null;
	}

	private String getFileName(File file, boolean isSuffix) {
		if (null != file) {
			String fileName = file.getName();
			int ids = fileName.lastIndexOf(".");
			if (isSuffix) {
				if (ids != -1) {
					return fileName.substring(ids);
				}
			} else {
				if (ids != -1) {
					return fileName.substring(0, ids);
				} else {
					return fileName;
				}
			}
		}
		return null;
	}

	public String[] getFileNameArray(File[] fileArray) {
		return toStringArray(fileArray, true);
	}

	public FilenameFilter getFileNameFilter() {
		return getFileNameFilter(getSuffixArray());
	}

	public FilenameFilter getFileNameFilter(final String[] suffixArray) {
		FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String fName = name.toLowerCase();
				File file = new File(dir, name);
				String temp;
				for (int i = 0, iLength = suffixArray.length; i < iLength; i++) {
					temp = suffixArray[i];
					if (file.isDirectory() || (null != temp && fName.endsWith(temp))) {
						return true;
					}
				}
				return false;
			}
		};
		return fileFilter;
	}

	public String getFileNamePrefix(File file) {
		return getFileName(file, false);
	}

	public String getFileNameSuffix(File file) {
		return getFileName(file, true);
	}

	public String[] getFilePathArray(File[] fileArray) {
		return toStringArray(fileArray, false);
	}

	public File getFileReplace(File file, Object name, String suffix) {
		return getFile(file, name, true, suffix);
	}

	public ImageLayer getImageLayer(List<ImageLayer> list, File file) {
		if (null != file) {
			return getImageLayer(list, file.getPath());
		}
		return null;
	}

	public ImageLayer getImageLayer(List<ImageLayer> layerList, int depth) {
		if (null != layerList) {
			ImageLayer imageLayer;
			int depthLayer;
			for (Iterator<ImageLayer> iterator = layerList.iterator(); iterator.hasNext();) {
				imageLayer = iterator.next();
				if (null != imageLayer) {
					depthLayer = imageLayer.getDepth();
					if (depthLayer == depth) {
						return imageLayer;
					}
				}
			}
		}
		return null;
	}

	public ImageLayer getImageLayer(List<ImageLayer> list, String fileName) {
		if (null != list && null != fileName) {
			ImageLayer imageLayer;
			String path;
			for (Iterator<ImageLayer> iterator = list.iterator(); iterator.hasNext();) {
				imageLayer = iterator.next();
				if (null != imageLayer) {
					path = imageLayer.getImagePath();
					if (null != path && path.endsWith(fileName)) {
						return imageLayer;
					}
				}
			}
		}
		return null;
	}

	public int getImageLayerDepthMax(List<ImageLayer> list) {
		if (null != list) {
			ImageLayer layer;
			int depth;
			int index = -1;
			for (Iterator<ImageLayer> iterator = list.iterator(); iterator.hasNext();) {
				layer = iterator.next();
				if (null != layer) {
					depth = layer.getDepth();
					if (depth > index) {
						index = depth;
					}
				}
			}
			return index;
		}
		return -1;
	}

	public String[] getSuffixArray() {
		return suffixArray;
	}

	public int indexOf(List<ImageLayer> list, String fileName) {
		ImageLayer layer = getImageLayer(list, fileName);
		if (null != layer) {
			return list.indexOf(layer);
		}
		return -1;
	}

	public boolean isDirectoryArrayOnly(File[] fileArray) {
		return checkFileArray(fileArray, true);
	}

	public boolean isFileArrayOnly(File[] fileArray) {
		return checkFileArray(fileArray, false);
	}

	public void setSuffixArray(String[] array) {
		suffixArray = array;
	}

	private String[] toStringArray(File[] fileArray, boolean isFileName) {
		int len = fileArray.length;
		String[] array = new String[len];
		for (int i = 0, iLength = len; i < iLength; i++) {
			File file = fileArray[i];
			if (isFileName) {
				array[i] = file.getName();
			} else {
				array[i] = file.getAbsolutePath();
			}
		}
		return array;
	}
}
