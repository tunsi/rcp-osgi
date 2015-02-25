package com.rcpfw.application.utils;

import java.io.InputStream;

import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.eclipse.e4.ui.internal.workbench.Activator;
import org.osgi.framework.Bundle;

import com.google.common.base.Strings;

/**
 * bundle资源工具
 * 
 * @author Xiangxi
 *
 */
public class BundleResourceUtil {
	public static InputStream getInputStream(IContributor contributor, String uri) {
		if (uri != null && uri.length() > 0 && contributor != null) {
			String bundleName = null;
			if (contributor instanceof RegistryContributor) {
				bundleName = ((RegistryContributor) contributor).getActualName();
			} else {
				bundleName = contributor.getName();
			}

			return getInputStream(bundleName, uri);
		}
		return null;
	}

	public static InputStream getInputStream(String bundleName, String uri) {
		if (uri != null && uri.length() > 0 && !Strings.isNullOrEmpty(bundleName)) {
			Bundle bundle = Activator.getDefault().getBundleForName(bundleName);
			try {
				InputStream inputStream = bundle.getResource(uri).openStream();
				return inputStream;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
}
