package com.rcpfw.application.models;

import java.util.List;

import com.rcpfw.base.models.Hierarchical;

public class MenuDescription implements Hierarchical<MenuDescription> {
	private String label;
	private String icon;
	private String partId;

	private MenuDescription parent;
	private List<MenuDescription> children;

	@Override
	public MenuDescription getParent() {
		return parent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public List<MenuDescription> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDescription> children) {
		this.children = children;
	}

	public void setParent(MenuDescription parent) {
		this.parent = parent;
	}

}
