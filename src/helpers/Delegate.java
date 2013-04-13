package helpers;

/**
 * The Delegate class is used by the World to obtain keystrokes, call paint, and modify the background level image.
 */
public interface Delegate {

    public int getInput();

    public void repaint();

    public void setBackgroundImage(int[][] map);
}
