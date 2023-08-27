package javaev.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.charset.Charset;

import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {
	public static String encoding_default = "utf-8";
	public static String suffix_properties_xml = ".xml";

	private Properties propertiesLocal;

	private String encoding_properties;
	private String suffix_properties;
	private String tag_comment = "setting";

	public PropertiesUtils() {
	}

	public PropertiesUtils(Properties properties) {
		propertiesLocal = properties;
	}

	private String getEcoding() {
		if (null == encoding_properties || (null != encoding_properties && !Charset.isSupported(encoding_properties))) {
			encoding_properties = encoding_default;
		}
		return encoding_properties;
	}

	public Properties getProperties() {
		return propertiesLocal;
	}

	public Properties getPropertiesString() {
		return getPropertiesString(getProperties());
	}

	public Properties getPropertiesString(Properties properties) {
		if (null != properties) {
			Properties prop = new Properties();
			Entry<Object, Object> entry;
			Object key;
			Object value;
			Set<Entry<Object, Object>> eSet = properties.entrySet();
			for (Iterator<Entry<Object, Object>> iterator = eSet.iterator(); iterator.hasNext();) {
				entry = iterator.next();
				if (null != entry) {
					key = entry.getKey();
					value = entry.getValue();
					if (null != key && null != value) {
						prop.put(key.toString(), value.toString());
					}
				}
			}
			return prop;
		}
		return null;
	}

	public String getSuffixProperties() {
		if (null == suffix_properties) {
			suffix_properties = suffix_properties_xml;
		}
		return suffix_properties;
	}

	public boolean isFileProperties(File file) {
		if (null != file) {
			String name = file.getName().toLowerCase();
			if (name.endsWith(getSuffixProperties().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public Properties loadProperties(File file) {
		return loadProperties(file, false);
	}

	private Properties loadProperties(File file, boolean isTypeXml) {
		if (null != file) {
			if (isFileProperties(file)) {
				try {
					return loadProperties(new FileInputStream(file), isTypeXml);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public Properties loadProperties(File file, String fileSuffix) {
		setSuffixProperties(fileSuffix);
		return loadProperties(file);
	}

	private Properties loadProperties(InputStream stream, boolean isTypeXml) {
		if (null != stream) {
			Properties properties = new Properties();
			try {
				if (isTypeXml) {
					properties.loadFromXML(stream);
				} else {
					properties.load(stream);
				}
				stream.close();
				return properties;
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Properties loadPropertiesXml(File file) {
		return loadProperties(file, true);
	}

	public Properties loadPropertiesXml(File file, String fileSuffix) {
		setSuffixProperties(fileSuffix);
		return loadPropertiesXml(file);
	}

	private void setEcoding(String encoding) {
		encoding_properties = encoding;
	}

	public void setProperties(Properties properties) {
		propertiesLocal = properties;
	}

	public void setSuffixProperties(String suffix) {
		suffix_properties = suffix;
	}

	private void storeProperties(boolean isTypeXml, File file, String comment, String encoding) {
		storeProperties(getProperties(), isTypeXml, file, comment, encoding);
	}

	public void storeProperties(File file) {
		storeProperties(file, tag_comment, getEcoding());
	}

	public void storeProperties(File file, String fileSuffix) {
		storeProperties(file, fileSuffix, tag_comment, getEcoding());
	}

	public void storeProperties(File file, String comment, String encoding) {
		storeProperties(false, file, comment, encoding);
	}

	public void storeProperties(File file, String fileSuffix, String comment, String encoding) {
		setSuffixProperties(fileSuffix);
		storeProperties(file, comment, encoding);
	}

	private void storeProperties(Properties properties, boolean isTypeXml, File file, String comment, String encoding) {
		if (null != file) {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				if (isFileProperties(file)) {
					storeProperties(properties, isTypeXml, new FileOutputStream(file), comment, encoding);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void storeProperties(Properties properties, boolean isTypeXml, OutputStream stream, String comment,
			String encoding) {
		if (null != properties) {
			properties = getPropertiesString(properties);
			setEcoding(encoding);
			encoding = getEcoding();
			try {
				if (isTypeXml) {
					properties.storeToXML(stream, comment, encoding);
				} else {
					properties.store(stream, comment);
				}
				stream.flush();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void storeProperties(Properties properties, File file) {
		storeProperties(properties, file, tag_comment, getEcoding());
	}

	public void storeProperties(Properties properties, File file, String fileSuffix) {
		setSuffixProperties(fileSuffix);
		storeProperties(properties, file, tag_comment, getEcoding());
	}

	public void storeProperties(Properties properties, File file, String comment, String encoding) {
		storeProperties(properties, false, file, comment, encoding);
	}

	public void storeProperties(Properties properties, File file, String fileSuffix, String comment, String encoding) {
		setSuffixProperties(fileSuffix);
		storeProperties(properties, file, comment, encoding);
	}

	public void storePropertiesXml(File file) {
		storePropertiesXml(file, tag_comment, getEcoding());
	}

	public void storePropertiesXml(File file, String fileSuffix) {
		storePropertiesXml(file, fileSuffix, tag_comment, getEcoding());
	}

	public void storePropertiesXml(File file, String comment, String encoding) {
		storeProperties(true, file, comment, encoding);
	}

	public void storePropertiesXml(File file, String fileSuffix, String comment, String encoding) {
		setSuffixProperties(fileSuffix);
		storePropertiesXml(file, comment, encoding);
	}

	public void storePropertiesXml(Properties properties, File file) {
		storePropertiesXml(properties, file, tag_comment, getEcoding());
	}

	public void storePropertiesXml(Properties properties, File file, String fileSuffix) {
		storePropertiesXml(properties, file, fileSuffix, tag_comment, getEcoding());
	}

	public void storePropertiesXml(Properties properties, File file, String comment, String encoding) {
		storeProperties(properties, true, file, comment, encoding);
	}

	public void storePropertiesXml(Properties properties, File file, String fileSuffix, String comment,
			String encoding) {
		setSuffixProperties(fileSuffix);
		storePropertiesXml(properties, file, comment, encoding);
	}
}
