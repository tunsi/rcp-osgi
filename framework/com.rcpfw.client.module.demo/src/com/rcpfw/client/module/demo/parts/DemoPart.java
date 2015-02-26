package com.rcpfw.client.module.demo.parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DemoPart {
	@Inject
	public DemoPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		new Label(parent, SWT.NONE).setText("Demo");
	}

}