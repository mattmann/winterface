package com.github.snoblind.winterface.nashorn;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Window;

public final class WindowUtils {

	private WindowUtils() {
	}

	public static void centerOnScreen(final Window window) {
		final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		final GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
		final Dimension screenSize = window.getToolkit().getScreenSize();
		final Insets screenInsets = window.getToolkit().getScreenInsets(graphicsConfiguration);
		final int x = (int) Math.round((0.5) * (screenSize.width - screenInsets.left - screenInsets.right - window.getWidth()));
		final int y = (int) Math.round((0.5) * (screenSize.height - screenInsets.top - screenInsets.bottom - window.getHeight()));
		window.setLocation(x, y);
	}

	public static void centerOnScreenAndShow(final Window window) {
		centerOnScreen(window);
		window.setVisible(true);
	}
}
