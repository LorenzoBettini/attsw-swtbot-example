package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SampleMenuTest {

	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void initBot() {
		bot = new SWTWorkbenchBot();
		// Welcome view prevents for reaching the toolbar
		// so we need to close it
		closeWelcomePage();
	}

	private static void closeWelcomePage() {
		for (SWTBotView view : bot.views()) {
			if (view.getTitle().equals("Welcome")) {
				view.close();
			}
		}
	}

	@AfterClass
	public static void afterClass() {
		bot.resetWorkbench();
	}

	@Test
	public void testSampleMenu() {
		bot.menu("Sample Menu").menu("Sample Command").click();
		assertDialog();
	}

	@Test
	public void testToolbar() {
		bot.toolbarButtonWithTooltip("Say hello world (Ctrl+6)").click();
		assertDialog();
	}

	@Test
	public void testShortcut() throws ParseException {
		bot.activeShell().pressShortcut(Keystrokes.MOD1, KeyStroke.getInstance("6"));
		assertDialog();
	}

	private void assertDialog() {
		SWTBotShell dialog = bot.shell("Menu");
		dialog.activate();
		bot.label("Hello, Eclipse world");
		bot.button("OK").click();
		bot.waitUntil(shellCloses(dialog));
	}
}
