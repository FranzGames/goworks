/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.netbeans.editor.navigation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.TreePath;
import org.antlr.netbeans.editor.navigation.actions.FilterSubmenuAction;
import org.antlr.netbeans.editor.navigation.actions.SortByNameAction;
import org.antlr.netbeans.editor.navigation.actions.SortBySourceAction;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.*;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "TIP_CollapsiblePanel=Click or press {0} to hide/show when the Navigator is active"
})
public abstract class NavigatorPanelUI extends javax.swing.JPanel implements ExplorerManager.Provider, FiltersManager.FilterChangeListener, PropertyChangeListener {

    private static final Rectangle ZERO = new Rectangle(0, 0, 1, 1);
    private static final Logger PERF_LOG = Logger.getLogger(NavigatorPanelUI.class.getName() + ".perf");
    private static final RequestProcessor RP = new RequestProcessor(NavigatorPanelUI.class.getName(), 1);

    private final NavigatorNode.Factory nodeFactory;
    private final ExplorerManager explorerManager = new ExplorerManager();
    private final NavigatorBeanTreeView treeView;
    private final CollapsiblePanel filtersPanel;
    private final InstanceContent selectedNodes = new InstanceContent();
    private final Lookup lookup = new AbstractLookup(selectedNodes);
    private final Filters filters;

    private Action[] actions;

    private long lastShowWaitNodeTime = -1;

    @SuppressWarnings({"LeakingThisInConstructor", "OverridableMethodCallInConstructor"})
    public NavigatorPanelUI(NavigatorNode.Factory nodeFactory) {
        this.nodeFactory = nodeFactory;

        setLayout(new BorderLayout());
        explorerManager.addPropertyChangeListener(this);

        // tree view of rules
        treeView = new NavigatorBeanTreeView(this);
        this.add(treeView, BorderLayout.CENTER);

        // filters
        filtersPanel = new CollapsiblePanel();
        filtersPanel.setOrientation(CollapsiblePanel.DOWN);
        // tooltip
        KeyStroke toggleKey = KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        String keyText = Utilities.keyToString(toggleKey);
        filtersPanel.setToolTipText(Bundle.TIP_CollapsiblePanel(keyText));

        filters = createFilters();
        filters.getInstance().hookChangeListener(this);
        JComponent buttons = filters.getComponent();
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        filtersPanel.add(buttons);
        if ("Aqua".equals(UIManager.getLookAndFeel().getID())) {
            filtersPanel.setBackground(UIManager.getColor("NbExplorerView.background"));
        }

        actions = new Action[]
            {
                new SortByNameAction(filters),
                new SortBySourceAction(filters),
                null,
                new FilterSubmenuAction(filters.getInstance()),
            };

        this.add(filtersPanel, BorderLayout.SOUTH);

        explorerManager.setRootContext(NavigatorNode.getWaitNode());

        boolean expanded = NbPreferences.forModule(getClass()).getBoolean("filtersCollapsiblePanel.expanded", true);
        filtersPanel.setExpanded(expanded);
        filtersPanel.addPropertyChangeListener(this);
    }

    @Override
    public boolean requestFocusInWindow() {
        boolean result = super.requestFocusInWindow();
        treeView.requestFocusInWindow();
        return result;
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        treeView.requestFocus();
    }

    public Lookup getLookup() {
        return this.lookup;
    }

    public void showWaitNode() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                treeView.setRootVisible(true);
                explorerManager.setRootContext(NavigatorNode.getWaitNode());
                lastShowWaitNodeTime = System.currentTimeMillis();
            }
        });
    }

    public boolean isShowingWaitNode() {
        return explorerManager.getRootContext() == NavigatorNode.getWaitNode();
    }

    protected abstract Filters createFilters();

    public boolean getScrollOnExpand() {
        return treeView == null ? true : treeView.getScrollOnExpand();
    }

    public void setScrollOnExpand(boolean value) {
        if (treeView != null) {
            treeView.setScrollOnExpand(value);
        }
    }

    public void expandNode(Node node) {
        treeView.expandNode(node);
    }

    public Filters getFilters() {
        return filters;
    }

    public Action[] getActions() {
        return actions;
    }

    public void sort() {
        NavigatorNode node = getRootNode();
        if (node != null) {
            node.refreshRecursively();
        }
    }

    public FileObject getFileObject() {
        NavigatorNode node = getRootNode();
        if (node != null) {
            return node.getDescription().getFileObject();
        } else {
            return null;
        }
    }

    @Override
    public void filterStateChanged(ChangeEvent e) {
        NavigatorNode root = getRootNode();

        if ( root != null ) {
            root.refreshRecursively();
        }
    }

    private NavigatorNode getRootNode() {
        Node node = explorerManager.getRootContext();
        if (node instanceof NavigatorNode) {
            return (NavigatorNode)node;
        } else {
            return null;
        }
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(event.getPropertyName())) {
            for (Node node : (Node[])event.getOldValue()) {
                selectedNodes.remove(node);
            }
            for (Node node : (Node[])event.getNewValue()) {
                selectedNodes.add(node);
            }
        } else if (CollapsiblePanel.EXPANDED_PROPERTY.equals(event.getPropertyName())) {
            NbPreferences.forModule(getClass()).putBoolean("filtersCollapsiblePanel.expanded", filtersPanel.isExpanded());
        }
    }

    public void refresh(final Description description) {
        final NavigatorNode rootNode = getRootNode();
        if (rootNode != null && rootNode.getDescription().getFileObject().equals(description.getFileObject())) {
            RP.post(new Runnable() {
                    @Override
                    public void run() {
                        rootNode.updateRecursively(description);
                    }
                });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        treeView.setRootVisible(false);
                        explorerManager.setRootContext(nodeFactory.createNode(description));
                        boolean scrollOnExpand = getScrollOnExpand();
                        treeView.setAutoWaitCursor(false);
                        treeView.expandAll();
                        treeView.setAutoWaitCursor(true);
                        setScrollOnExpand(scrollOnExpand);

                        if (PERF_LOG.isLoggable(Level.FINE)) {
                            long tm2 = System.currentTimeMillis();
                            long tm1 = lastShowWaitNodeTime;
                            if (tm1 != -1) {
                                lastShowWaitNodeTime = -1;
                                PERF_LOG.log(Level.FINE,
                                             String.format("GrammarRulesPanelUI refresh took: %d ms", (tm2 - tm1)),
                                             new Object[] { description.getFileObject().getName(), (tm2 - tm1) });
                            }
                        }
                    }
                });
        }
    }

    protected static class NavigatorBeanTreeView extends BeanTreeView implements ToolTipManagerEx.ToolTipProvider {

        private final NavigatorPanelUI navigatorPanelUI;
        private final ToolTipManagerEx toolTipManager;

        public NavigatorBeanTreeView(NavigatorPanelUI ui) {
            this.navigatorPanelUI = ui;
            this.toolTipManager = new ToolTipManagerEx( this );
        }

        public boolean getScrollOnExpand() {
            return tree.getScrollsOnExpand();
}

        public void setScrollOnExpand( boolean scroll ) {
            this.tree.setScrollsOnExpand( scroll );
        }

        @Override
        public JComponent getComponent() {
            return tree;
        }

        @Override
        public String getToolTipText(Point loc) {
            return null;
            //ElementJavadoc doc = getDocumentation(loc);
            //return null == doc ? null : doc.getText();
        }

        /*private ElementJavadoc getDocumentation( Point loc ) {
            TreePath path = tree.getPathForLocation( loc.x, loc.y );
            if( null == path )
                return null;
            Node node = Visualizer.findNode( path.getLastPathComponent() );
            if( node instanceof ElementNode ) {
                return getJavaDocFor( (ElementNode)node );
            }
            return null;
        }*/

        @Override
        public Rectangle getToolTipSourceBounds(Point loc) {
            NavigatorNode root = navigatorPanelUI.getRootNode();
            if ( root == null ) {
                return null;
            }
            TreePath path = tree.getPathForLocation( loc.x, loc.y );
            return null == path ? null : tree.getPathBounds( path );
        }

        @Override
        public Point getToolTipLocation( Point mouseLocation, Dimension tipSize ) {
            Point screenLocation = getLocationOnScreen();
            Rectangle sBounds = getGraphicsConfiguration().getBounds();
            Dimension compSize = getSize();
            Point res = new Point();
            Rectangle tooltipSrcRect = getToolTipSourceBounds( mouseLocation );
            //May be null, prevent the NPE, nothing will be shown anyway.
            if (tooltipSrcRect == null) {
                tooltipSrcRect = new Rectangle();
            }

            Point viewPosition = getViewport().getViewPosition();
            screenLocation.x -= viewPosition.x;
            screenLocation.y -= viewPosition.y;

            //first try bottom right
            res.x = screenLocation.x + compSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y+tooltipSrcRect.height;

            if( res.x + tipSize.width <= sBounds.x+sBounds.width
                    && res.y + tipSize.height <= sBounds.y+sBounds.height ) {
                return res;
            }

            //upper right
            res.x = screenLocation.x + compSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y - tipSize.height;

            if( res.x + tipSize.width <= sBounds.x+sBounds.width
                    && res.y >= sBounds.y ) {
                return res;
            }

            //lower left
            res.x = screenLocation.x - tipSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y;

            if( res.x >= sBounds.x
                    && res.y + tipSize.height <= sBounds.y+sBounds.height ) {
                return res;
            }

            //upper left
            res.x = screenLocation.x - tipSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height - tipSize.height;

            if( res.x >= sBounds.x && res.y >= sBounds.y ) {
                return res;
            }

            //give up (who's got such a small display anyway?)
            res.x = screenLocation.x + tooltipSrcRect.x;
            if( sBounds.y + sBounds.height - (screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height)
                > screenLocation.y + tooltipSrcRect.y - sBounds.y ) {
                res.y = screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height;
            } else {
                res.y = screenLocation.y + tooltipSrcRect.y - tipSize.height;
            }

            return res;
        }

        @Override
        public void invokeUserAction(final MouseEvent me) {
            SwingUtilities.invokeLater( new Runnable() {
                @Override
                public void run() {
                    if( null != me ) {
                        /*ElementJavadoc doc = getDocumentation( me.getPoint() );
                        JavadocTopComponent tc = JavadocTopComponent.findInstance();
                        if( null != tc ) {
                            tc.open();
                            tc.setJavadoc( doc );
                            tc.requestActive();
                        }*/
                    }
                }
            });
        }

        //#123940 start
        private boolean inHierarchy;
        private boolean doExpandAll;

        @Override
        public void addNotify() {
            super.addNotify();

            inHierarchy = true;

            if (doExpandAll) {
                super.expandAll();
                doExpandAll = false;
            }
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            inHierarchy = false;
            this.toolTipManager.hideTipWindow();
        }

        @Override
        public void expandAll() {
            super.expandAll();

            if (!inHierarchy) {
                doExpandAll = true;
            }
        }
        //#123940 end

    }

}
