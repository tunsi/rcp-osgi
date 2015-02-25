package com.rcpfw.application.extensionpoints;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.annotations.Creatable;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.rcpfw.application.models.MenuDescription;
import com.rcpfw.application.utils.BundleResourceUtil;

/**
 * 菜单扩展点处理器
 * 
 * @author Xiangxi
 *
 */
@Creatable
public class MenuExtensionHandler {
	private static final String EXTENSION_ID = "com.rcpfw.application.menu";
	private static final String XML_MENU = "mi";

	@Inject
	private IExtensionRegistry registry;

	@SuppressWarnings({ "unchecked" })
	public List<MenuDescription> evaluate() {
		IConfigurationElement[] configs = registry.getConfigurationElementsFor(EXTENSION_ID);

		List<MenuDescription> menus = new ArrayList<>();
		for (IConfigurationElement config : configs) {
			String uri = config.getAttribute("uri");

			InputStream inputStream = BundleResourceUtil.getInputStream(config.getContributor(), uri);

			if (inputStream != null) {
				try {
					Document xml = new SAXReader().read(inputStream);
					menus.addAll(Lists.transform((List<Element>) xml.getRootElement().elements(),
							(input) -> parse(input, null)));
				} catch (DocumentException e) {
					throw new RuntimeException(e);
				}

			}
		}
		return menus;
	}

	@SuppressWarnings("unchecked")
	protected MenuDescription parse(Element el, MenuDescription parent) {
		if (el != null && Objects.equal(el.getName(), XML_MENU)) {
			MenuDescription description = new MenuDescription();
			description.setLabel(el.attributeValue("label"));
			description.setPartId(el.attributeValue("part-id"));
			description.setParent(parent);
			if (el.elements() != null) {
				description.setChildren(Lists.transform((List<Element>) el.elements(),
						(input) -> parse(input, description)));
			}
			return description;
		}
		return null;
	}
}
