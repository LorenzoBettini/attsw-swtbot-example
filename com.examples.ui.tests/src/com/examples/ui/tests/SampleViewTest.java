package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SampleViewTest {

	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void initBot() throws InterruptedException {
		bot = new SWTWorkbenchBot();
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