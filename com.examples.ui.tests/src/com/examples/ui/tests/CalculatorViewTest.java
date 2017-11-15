package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorViewTest extends AbstractTest {

	@BeforeClass
	public static void initView() throws InterruptedException {
		// Open our view using the Eclipse Show View dialog
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell dialog = bot.shell("Show View");
		dialog.activate();
		// our view is in Sample Category
		bot.tree().expandNode("Sample Category").
			getNode("Calculator View").select();
		// this won't work in Neon, since that button was "OK"
		// bot.button("Open").click();
		// we achieve the same goal by simulating pressing ENTER:
		dialog.pressShortcut(Keystrokes.CR);
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