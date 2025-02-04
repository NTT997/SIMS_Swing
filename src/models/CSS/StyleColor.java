package Models.CSS;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

public class StyleColor {
	public static Color FormColor() {
		Color backgroundColor = Color.decode("#3c6382");
		float transparency = 0.6f;

		Color colorWithTransparency = new Color(backgroundColor.getRed(), backgroundColor.getGreen(),
				backgroundColor.getBlue(), (int) (transparency * 255));
		return colorWithTransparency;
	}

	public static Color TitleColor() {
		return Color.decode("#ffffff");
	}

	public static Color btnNoBacground() {
		Color backgroundColor = Color.decode("#f7d794");
		float transparency = 1f;

		Color colorWithTransparency = new Color(backgroundColor.getRed(), backgroundColor.getGreen(),
				backgroundColor.getBlue(), (int) (transparency * 255));
		return colorWithTransparency;
	}

	public static Color BtnBackground() {
		return Color.decode("#f5cd79");
	}
}
