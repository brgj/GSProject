package gui;

import objects.Thing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Frame that holds the ImagePanel and creates the interface of the game.
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

    /**
     * Initializes the interface for use.
     */
    public void initComponents() {

        menubar = new JMenuBar();
        file = new JMenu("File");
        loadMap = new JMenuItem("Load Map...");

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
            Thing.playerImg = ImageIO.read(getClass().getResource("/images/Player.jpg"));
            Thing.playerDeadImg = ImageIO.read(getClass().getResource("/images/Player_Dead.jpg"));
            Thing.enemyImg = ImageIO.read(getClass().getResource("/images/Enemy.jpg"));
            Thing.enemyDeadImg = ImageIO.read(getClass().getResource("/images/Enemy_Dead.jpg"));
            Thing.bomb1Img = ImageIO.read(getClass().getResource("/images/bomb1.png"));
            Thing.bomb2Img = ImageIO.read(getClass().getResource("/images/bomb2.png"));
            Thing.bomb3Img = ImageIO.read(getClass().getResource("/images/bomb3.png"));
            Thing.explosionImg = ImageIO.read(getClass().getResource("/images/explosion.gif"));
            Thing.fireUpImg = ImageIO.read(getClass().getResource("/images/fire_up.PNG"));
            Thing.bombUpImg = ImageIO.read(getClass().getResource("/images/bomb_up.PNG"));

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

    /**
     * Creates a map based on the input file.
     * @param file
     * @throws IOException
     */
    public void createMap(File file) throws IOException {
        ArrayList<int[]> mapList = new ArrayList<int[]>();
        Scanner scan = new Scanner(file, "UTF-8");
        int wallColour;
        int groundColour;
        while (scan.hasNext()) {
            String str = scan.nextLine();
            if(str.equals("*"))
                break;
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

        if(scan.hasNext()) {
            groundColour = Integer.parseInt(scan.next(), 16);
            wallColour = Integer.parseInt(scan.next(), 16);
        } else {
            groundColour = 0xFFFFFF;
            wallColour = 0;
        }

        map = mapList.toArray(new int[0][]);

        remove(imagePanel);

        imagePanel = new ImagePanel(map, groundColour, wallColour);

        add(imagePanel);

        pack();
    }
}
