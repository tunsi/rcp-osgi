package com.rcpfw.client.core.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class UserDetailFormDialog extends FormDialog {

	public UserDetailFormDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		ScrolledForm form = mform.getForm();
		FormToolkit toolkit = mform.getToolkit();
		toolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);
		form.setText("请输入用户的信息");
		toolkit.decorateFormHeading(form.getForm());
		form.getForm().addMessageHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				super.linkActivated(e);
			}
		});

		IMessageManager mmng = mform.getMessageManager();
		TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR);
		section.setText("基本信息");
		Composite sbody = toolkit.createComposite(section);
		section.setClient(sbody);
		GridLayout glayout = new GridLayout();
		glayout.horizontalSpacing = 10;
		glayout.numColumns = 2;
		sbody.setLayout(glayout);
		toolkit.paintBordersFor(sbody);

		createDecoratedTextField("登录名", toolkit, sbody, mmng);
		createDecoratedTextField("全名", toolkit, sbody, mmng);
		createDecoratedTextField("电子邮件", toolkit, sbody, mmng);

		final Button button1 = toolkit.createButton(form.getBody(), "Add general error", SWT.CHECK);
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button1.getSelection()) {
					mmng.addMessage("saveError", "Save Error", null, IMessageProvider.ERROR);
				} else {
					mmng.removeMessage("saveError");
				}
			}
		});
		final Button button2 = toolkit.createButton(form.getBody(), "Add static message", SWT.CHECK);
		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (button2.getSelection()) {
					mmng.addMessage("info", "Secondary info", null, IMessageProvider.NONE);
				} else {
					mmng.removeMessage("info");
				}
			}
		});
		final Button button3 = toolkit.createButton(form.getBody(), "Auto update", SWT.CHECK);
		button3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				mmng.setAutoUpdate(button3.getSelection());
			}
		});
		button3.setSelection(true);
	}

	private void createDecoratedTextField(String label, FormToolkit toolkit, Composite parent,
			final IMessageManager mmng) {
		toolkit.createLabel(parent, label);
		final Text text = toolkit.createText(parent, "");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 150;
		text.setLayoutData(gd);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String s = text.getText();
				// flag length
				if (s.length() > 0 && s.length() <= 5) {
					mmng.addMessage("textLength", "Text is longer than 0 characters", null,
							IMessageProvider.INFORMATION, text);
				} else if (s.length() > 5 && s.length() <= 10) {
					mmng.addMessage("textLength", "Text is longer than 5 characters", null, IMessageProvider.WARNING,
							text);
				} else if (s.length() > 10) {
					mmng.addMessage("textLength", "Text is longer than 10 characters", null, IMessageProvider.ERROR,
							text);
				} else {
					mmng.removeMessage("textLength", text);
				}
				// flag type
				boolean badType = false;
				for (int i = 0; i < s.length(); i++) {
					if (!Character.isLetter(s.charAt(i))) {
						badType = true;
						break;
					}
				}
				if (badType) {
					mmng.addMessage("textType", "Text must only contain letters", null, IMessageProvider.ERROR, text);
				} else {
					mmng.removeMessage("textType", text);
				}
			}
		});
	}

}
