package com.rcpfw.application.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.rcpfw.application.extensionpoints.MenuExtensionHandler;
import com.rcpfw.application.models.MenuDescription;

/**
 * 导航页
 * 
 * @author Xiangxi
 *
 */
public class NavigatorPart {

	public static final String M_PARTSTACK_MAIN = "com.rcpfw.application.partstack.indexstack";

	@Inject
	private MenuExtensionHandler menuExtensionHandler;

	@Inject
	private EPartService partService;

	@Inject
	private EModelService modelService;

	@Inject
	private MApplication app;

	private TreeViewer viewer;

	@Inject
	public NavigatorPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		viewer = new TreeViewer(parent, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new TreeNodeContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof TreeNode) {
					MenuDescription menuDescription = (MenuDescription) ((TreeNode) element).getValue();
					return menuDescription.getLabel();
				}
				return super.getText(element);
			}
		});
		viewer.setInput(createDummyModel());

		viewer.getTree().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] treeItems = ((Tree) e.getSource()).getSelection();
				if (treeItems != null) {
					Lists.newArrayList(treeItems)
							.stream()
							.forEach(
									(item) -> {
										TreeNode node = (TreeNode) item.getData();
										MenuDescription desc = (MenuDescription) node.getValue();
										String partId = desc.getPartId();
										if (!Strings.isNullOrEmpty(partId)) {
											MPartStack stack = (MPartStack) modelService.find(M_PARTSTACK_MAIN, app);
											Optional<MStackElement> optional = Iterables.tryFind(stack.getChildren(), (
													input) -> Objects.equal(input.getElementId(), partId));
											if (optional.isPresent()) {
												MStackElement el = optional.get();
												if(!el.isToBeRendered()) {
													el.setToBeRendered(true);
												}
												stack.setSelectedElement(optional.get());
											} else {
												MPart part = partService.createPart(partId);
												stack.getChildren().add(part);
												stack.setSelectedElement(part);
											}
										}
									});
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	private TreeNode[] createDummyModel() {
		List<MenuDescription> descriptions = menuExtensionHandler.evaluate();
		if (descriptions != null) {
			return Iterables
					.toArray(Iterables.transform(descriptions, (input) -> convert(input, null)), TreeNode.class);
		}
		return null;
	}

	private TreeNode convert(MenuDescription desc, TreeNode parent) {
		TreeNode treeNode = new TreeNode(desc);
		treeNode.setParent(parent);

		if (desc.getChildren() != null && desc.getChildren().size() > 0) {
			treeNode.setChildren(Iterables.toArray(
					Iterables.transform(desc.getChildren(), (input) -> convert(input, null)), TreeNode.class));
		}
		return treeNode;
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}