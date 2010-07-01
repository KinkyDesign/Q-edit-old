
package qedit.tools;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.Scrollable;
import org.jscroll.JScrollDesktopPane;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class ScrollableDesktopPane extends JDesktopPane implements Scrollable{

    JScrollDesktopPane js;
    public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  public int getScrollableUnitIncrement(Rectangle r, int axis, int dir) {
    return 50;
  }

  public int getScrollableBlockIncrement(Rectangle r, int axis, int dir) {
    return 50;
  }

  public boolean getScrollableTracksViewportWidth() {
    return true;
  }

  public boolean getScrollableTracksViewportHeight() {
    return true;
  }


}
