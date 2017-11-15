package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorViewTest extends AbstractTest {

	@BeforeClass
	public static void initView() {
		// Open our view using the Eclipse Show View dialog
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell dialog = bot.shell("Show View");
		dialog.activate();
		// our view is in Sample Category
		bot.tree().expandNode("Sample Category").
			getNode("Calculator View").select();
		bot.button("Open").click();
		bot.waitUntil(shellCloses(dialog));
	}

	@AfterClass
	public static void closeView() {
		bot.viewByTitle("Calculator View").close();
	}

	@Test
	public void testViewStructure() {
		SWTBotView view = bot.viewByTitle("Calculator View");
		view.bot().textWithLabel("Input");
		view.bot().textWithLabel("Output");
		view.bot().button("Double");
	}

	@Test
	public void testViewBehavior() {
		SWTBotView view = bot.viewByTitle("Calculator View");
		view.bot().textWithLabel("Input").setText("3");
		view.bot().button("Double").click();
		assertEquals("6", view.bot().textWithLabel("Output").getText());
	}

}