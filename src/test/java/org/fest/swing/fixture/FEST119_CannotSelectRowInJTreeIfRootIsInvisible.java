/*
 * Created on May 19, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.TestGroups.BUG;
import static org.fest.swing.test.core.TestGroups.GUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.swing.TestTree;
import org.fest.swing.test.swing.TestWindow;
import org.testng.annotations.*;

/**
 * Test case for bug <a href="http://jira.codehaus.org/browse/FEST-119" target="_blank">FEST-119</a>.
 *
 * @author Alex Ruiz
 */
@Test(groups = { BUG, GUI })
public class FEST119_CannotSelectRowInJTreeIfRootIsInvisible {

  private MyWindow window;
  private FrameFixture frameFixture;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeMethod public void setUp() {
    window = MyWindow.createNew();
    frameFixture = new FrameFixture(window);
    frameFixture.show();
  }

  @AfterMethod public void tearDown() {
    frameFixture.cleanUp();
  }

  public void shouldSelectRowWhenRootIsInvisible() {
    JTreeFixture treeFixture = frameFixture.tree();
    treeFixture.selectPath("branch1");
    treeFixture.requireSelection(0);
    treeFixture.selectPath("branch1/branch1.1");
    treeFixture.requireSelection(1);
    treeFixture.selectRow(0);
    treeFixture.requireSelection("branch1");
    treeFixture.selectRow(1);
    treeFixture.requireSelection("branch1/branch1.1");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    private static final Dimension TREE_SIZE = new Dimension(200, 100);

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final TestTree tree = new TestTree(nodes());

    private static TreeModel nodes() {
      MutableTreeNode root =
        node("root",
            node("branch1",
                node("branch1.1",
                    node("branch1.1.1"),
                    node("branch1.1.2")
                ),
                node("branch1.2")
            ),
            node("branch2")
        );
      return new DefaultTreeModel(root);
    }

    private static MutableTreeNode node(String text, MutableTreeNode...children) {
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(text);
      for (MutableTreeNode child : children) node.add(child);
      return node;
    }

    private MyWindow() {
      super(FEST119_CannotSelectRowInJTreeIfRootIsInvisible.class);
      tree.setRootVisible(false);
      add(decorate(tree));
      setPreferredSize(new Dimension(600, 400));
    }

    private static Component decorate(JTree tree) {
      JScrollPane scrollPane = new JScrollPane(tree);
      scrollPane.setPreferredSize(TREE_SIZE);
      return scrollPane;
    }
  }

}
