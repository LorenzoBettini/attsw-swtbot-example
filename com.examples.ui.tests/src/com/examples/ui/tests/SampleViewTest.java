package com.examples.ui.tests;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;

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
	public void test() {
		// fictitious test just to run this class as a JUnit test
		// and check that the @BeforeClass method can be executed
		// without failure.
	}

}