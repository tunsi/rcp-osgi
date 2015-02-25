package com.rcpfw.client.core.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.rcpfw.client.core.dialogs.UserDetailFormDialog;
import com.rcpfw.client.core.jface.viewers.ColumnViewerSorter;
import com.rcpfw.client.core.models.User;

public class UserPart {
	@Inject
	public UserPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout());
		
		Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayoutData(new GridData(GridData.FILL));
		panel.setLayout(new RowLayout(SWT.HORIZONTAL));
		Button createdButton = new Button(panel, SWT.PUSH);
		createdButton.setText("创建用户");
		createdButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);

				UserDetailFormDialog detailFormDialog = new UserDetailFormDialog(parent.getShell());
				detailFormDialog.create();
				detailFormDialog.getShell().setText("创建用户");
				detailFormDialog.getShell().setSize(320, 360);
				detailFormDialog.open();
			}
		});
		createdButton.setLayoutData(new RowData(100, 40));

		TableViewer v = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		v.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		v.setContentProvider(new IStructuredContentProvider() {
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return (User[]) inputElement;
			}
		});

		TableViewerColumn column = new TableViewerColumn(v, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setText("Login Name");
		column.getColumn().setMoveable(true);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((User) element).getLoginName();
			}
		});
		new ColumnViewerSorter<User>(v, column) {
			@Override
			protected int doCompare(Viewer viewer, User e1, User e2) {
				return e1.getLoginName().compareToIgnoreCase(e2.getLoginName());
			}
		};

		column = new TableViewerColumn(v, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setText("Full Name");
		column.getColumn().setMoveable(true);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((User) element).getFullName();
			}
		});
		new ColumnViewerSorter<User>(v, column) {
			@Override
			protected int doCompare(Viewer viewer, User e1, User e2) {
				return e1.getFullName().compareToIgnoreCase(e2.getFullName());
			}
		};

		column = new TableViewerColumn(v, SWT.NONE);
		column.getColumn().setWidth(300);
		column.getColumn().setText("Email");
		column.getColumn().setMoveable(true);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((User) element).getEmail();
			}
		});
		new ColumnViewerSorter<User>(v, column) {
			@Override
			protected int doCompare(Viewer viewer, User e1, User e2) {
				return e1.getEmail().compareToIgnoreCase(e2.getEmail());
			}
		};

		v.setInput(new User[] { new User("aaa", "bbb", "ccc"), new User("aaa1", "bbb1", "ccc1"),
				new User("aaa2", "bbb2", "ccc2") });
		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);

	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {

	}

	public class UserLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof User) {
				if (columnIndex == 0) {
					return ((User) element).getEmail();
				}
			}
			return null;
		}

	}

}