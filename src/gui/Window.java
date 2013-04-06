package gui;

import objects.Thing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: brad
 * Date: 4/4/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Window extends JFrame {
    int[][] map;
    ImagePanel imagePanel;

    private JMenuBar menubar;
    private JMenu file;
    private JMenuItem loadMap;

    private JFileChooser fileChooser;

    public Window() {
        initComponents();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initComponents() {

        menubar = new JMenuBar();
        file = new JMenu("File");
        loadMap = new JMenuItem("Load Map");

        fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".map") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Map files";
            }
        });

        menubar.add(file);
        file.add(loadMap);

        this.setJMenuBar(menubar);

        imagePanel = new ImagePanel();

        add(imagePanel);

        try {
            Thing.playerImg = ImageIO.read(getClass().getResource("/images/abc.jpeg"));
            Thing.enemyImg = ImageIO.read(getClass().getResource("/images/def.jpeg"));
            Thing.bombImg = ImageIO.read(getClass().getResource("/images/ghi.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return;
        }

        loadMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setCurrentDirectory(new File(getClass().getResource("/maps/").getFile()));
                int val = fileChooser.showOpenDialog(imagePanel);
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        createMap(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        });

        pack();
    }

    public void createMap(File file) throws IOException {
        ArrayList<int[]> mapList = new ArrayList<int[]>();
        Scanner scan = new Scanner(file, "UTF-8");
        while (scan.hasNext()) {
            String str = scan.nextLine();
            Scanner line = new Scanner(str);
            int[] buffer = new int[str.length()];
            int i = 0;

            while (line.hasNext()) {
                buffer[i++] = Integer.parseInt(line.next());
            }

            int[] row = new int[i];

            System.arraycopy(buffer, 0, row, 0, i);

            mapList.add(row);
        }

        map = mapList.toArray(new int[0][]);

        // TODO: Destroy world before removing panel

        remove(imagePanel);

        imagePanel = new ImagePanel(map);

        add(imagePanel);

        pack();
    }
}
