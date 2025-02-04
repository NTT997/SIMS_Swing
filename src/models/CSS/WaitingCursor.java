package Models.CSS;

import java.awt.Component;
import java.awt.Cursor;

public class WaitingCursor {
	public static void setWaitingCursor(Component component) {
		Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
		component.setCursor(waitCursor);
	}

	// Phương thức để đặt con trỏ mặc định
	public static void setDefaultCursor(Component component) {
		Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		component.setCursor(defaultCursor);
	}
}
