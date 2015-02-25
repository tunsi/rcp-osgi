package com.rcpfw.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;

public class WelcomePart {

	@Inject
	public WelcomePart() {

	}

	@Focus
	void setFocus() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		
	}

}