package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.BeforeClass;
import org.junit.Test;

public class SampleViewTest extends AbstractTest {

	@BeforeClass
	public static void initView() throws InterruptedException {
		// Open our view using the Eclipse Show View dialog
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell dialog = bot.shell("Show View");
		dialog.activate();
		// our view is in Sample Category
		bot.tree().expandNode("Sample Category").getNode("Sample View").select();
		bot.button("Open").click();
		bot.waitUntil(shellCloses(dialog));
	}

	@Test
	public void testViewTree() {
		SWTBotView view = bot.viewByTitle("Sample View");
		view.bot().tree().getTreeItem("Root").expand();
		view.bot().tree().getTreeItem("Root").getNode("Parent 1").expand();
		view.bot().tree().getTreeItem("Root").getNode("Parent 1").
			getNode("Leaf 1").select();
	}

}