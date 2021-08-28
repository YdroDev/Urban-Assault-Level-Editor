package Dialogs;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import main.EditorState;
import main.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

public class LevelParameters implements WindowListener, ActionListener, ChangeListener {
    private final MainWindow owner;
    private final JDialog dialog;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private final String[] skies = new String[72];
    private final String[] eventLoops = new String[4];
    private final String[] movies = new String[12];
    private final String[] musics = new String[6];
    private final JLabel[] skyFrame = new JLabel[72];
    private final BufferedImage[] sky = new BufferedImage[73];
    private final String[] sets = new String[6];

    private JComboBox<String> setList;
    private JComboBox<String> movieList;
    private JComboBox<String> eventLoopList;
    private JComboBox<String> skyList;
    private JComboBox<String> musicList;
    private JButton playMusic;
    private AdvancedPlayer musicPlayer;
    private BufferedInputStream bisPlayer;
    private SwingWorker audioThread;
    private JTextField minBreakValue;
    private JSlider minBreakSlider;
    private JTextField maxBreakValue;
    private JSlider maxBreakSlider;
    private JPanel setPanel;
    private JPanel eventLoopPanel;
    private JPanel moviePanel;
    private JPanel skyPanel;
    private JPanel musicPanel;
    private JButton saveParams;
    private JButton cancelParams;

    private int selectedSkyIndex = 0;
    private int selectedMinBreak = 0;
    private int selectedMaxBreak = 0;
    private int selectedMovieIndex = 0;
    private int selectedEventLoopIndex = 0;
    private int selectedMusicIndex = 0;
    private int selectedSetIndex = 0;
    private boolean closed = true;

    public LevelParameters(MainWindow owner) {
        this.owner = owner;
        dialog = new JDialog(this.owner, "Level parameters", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

        sets[0] = "1"; sets[1] = "2"; sets[2] = "3"; sets[3] = "4"; sets[4] = "5"; sets[5] = "6";

        eventLoops[0] = "none"; eventLoops[1] = "1"; eventLoops[2] = "2"; eventLoops[3] = "3";

        skies[0] = "1998_01"; skies[1] = "1998_02"; skies[2] = "1998_03"; skies[3] = "1998_05"; skies[4] = "1998_06"; skies[5] = "Am_1"; skies[6] = "Am_2"; skies[7] = "Am_3"; skies[8] = "ARZ1"; skies[9] = "ASKY2"; skies[10] = "BRAUN1"; skies[11] = "CT6"; skies[12] = "H"; skies[13] = "H7"; skies[14] = "HAAMITT1"; skies[15] = "HAAMITT4"; skies[16] = "MOD2"; skies[17] = "MOD4"; skies[18] = "MOD5"; skies[19] = "MOD7"; skies[20] = "MOD8";
        skies[21] = "MOD9"; skies[22] = "MODA"; skies[23] = "MODB"; skies[24] = "Nacht1"; skies[25] = "NACHT2"; skies[26] = "NEWTRY5"; skies[27] = "NOSKY"; skies[28] = "NT1"; skies[29] = "NT2"; skies[30] = "NT3"; skies[31] = "NT5"; skies[32] = "NT6"; skies[33] = "NT7"; skies[34] = "NT8"; skies[35] = "NT9"; skies[36] = "NTA"; skies[37] = "S3_1"; skies[38] = "S3_4"; skies[39] = "SMOD1"; skies[40] = "SMOD2";
        skies[41] = "SMOD3"; skies[42] = "SMOD4"; skies[43] = "SMOD5"; skies[44] = "SMOD6"; skies[45] = "SMOD7"; skies[46] = "SMOD8"; skies[47] = "STERNE"; skies[48] = "wow1"; skies[49] = "wow5"; skies[50] = "wow7"; skies[51] = "wow8"; skies[52] = "wow9"; skies[53] = "wowa"; skies[54] = "wowb"; skies[55] = "wowc"; skies[56] = "wowd"; skies[57] = "wowe"; skies[58] = "wowf"; skies[59] = "wowh"; skies[60] = "wowi";
        skies[61] = "wowj"; skies[62] = "x1"; skies[63] = "x2"; skies[64] = "x4"; skies[65] = "x5"; skies[66] = "x7"; skies[67] = "x8"; skies[68] = "x9"; skies[69] = "xa"; skies[70] = "xb"; skies[71] = "xc";

        movies[0] = "none"; movies[1] = "Intro"; movies[2] = "Tutorial 1"; movies[3] = "Tutorial 2"; movies[4] = "Tutorial 3"; movies[5] = "Ghorkov"; movies[6] = "Taerkasten"; movies[7] = "Mykonian"; movies[8] = "Sulgogar"; movies[9] = "Black sect"; movies[10] = "Lose"; movies[11] = "Win";

        musics[0] = "none"; musics[1] = "Track 2"; musics[2] = "Track 3"; musics[3] = "Track 4"; musics[4] = "Track 5"; musics[5] = "Track 6";
    }

    public void render(){
        removeDialog();
        dialog.setSize(700,500);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());

        setPanel = new JPanel();
        setPanel.setLayout(new GridBagLayout());
        setList = new JComboBox<String>(sets);
        JLabel setText = new JLabel("Set: ");
        skyPanel = new JPanel();
        skyPanel.setLayout(new GridBagLayout());
        skyList = new JComboBox<String>(skies);
        JLabel skyText = new JLabel("Sky: ");
        setPanel.setBorder(BorderFactory.createTitledBorder("Select sector set"));
        musicPanel = new JPanel();
        musicPanel.setLayout(new GridBagLayout());
        musicList = new JComboBox<String>(musics);
        playMusic = new JButton("Play");
        moviePanel = new JPanel();
        moviePanel.setLayout(new GridBagLayout());
        moviePanel.setBorder(BorderFactory.createTitledBorder("Select movie"));
        movieList = new JComboBox<String>(movies);
        eventLoopPanel = new JPanel();
        eventLoopPanel.setLayout(new GridBagLayout());
        eventLoopPanel.setBorder(BorderFactory.createTitledBorder("Select event loop"));
        eventLoopList = new JComboBox<String>(eventLoops);
        JLabel minBreakText = new JLabel("Minimum break:");
        minBreakSlider = new JSlider(0, 1000000, selectedMinBreak);
        minBreakSlider.setPreferredSize(new Dimension(224,20));
        minBreakValue = new JTextField(5);
        minBreakValue.setText(Integer.toString(selectedMinBreak));
        JLabel maxBreakText = new JLabel("Maximum break:");
        maxBreakSlider = new JSlider(0, 1000000, selectedMaxBreak);
        maxBreakSlider.setPreferredSize(new Dimension(224,20));
        maxBreakValue = new JTextField(5);
        maxBreakValue.setText(Integer.toString(selectedMaxBreak));
        saveParams = new JButton("Save");
        cancelParams = new JButton("Cancel");

        if(closed){
            selectedSetIndex = EditorState.set - 1;
            selectedMovieIndex = 0;
            for(int i = 0; i < movies.length; i++) {
                if(movies[i] == EditorState.movie) selectedMovieIndex = i;
            }
            selectedEventLoopIndex = EditorState.eventLoop;
            for(int i = 0; i < skies.length; i++) {
                if(skies[i] == EditorState.sky) selectedSkyIndex = i;
            }
            selectedMusicIndex = 0;
            if(EditorState.music > 0) selectedMusicIndex = EditorState.music - 1;
            selectedMinBreak = 0;
            if(EditorState.min_break > 0) selectedMinBreak = EditorState.min_break;
            selectedMaxBreak = 0;
            if(EditorState.max_break > 0) selectedMaxBreak = EditorState.max_break;
        }
        closed = false;

        constraints.insets = new Insets(2,20,2,4);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        setPanel.add(setText, constraints);
        constraints.gridx = 1;
        constraints.insets = new Insets(2,1,2,30);
        setList.setSelectedIndex(selectedSetIndex);
        setPanel.add(setList, constraints);
        setList.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialog.add(setPanel, constraints);

        constraints.gridx = 0;
        movieList.setSelectedIndex(selectedMovieIndex);
        moviePanel.add(movieList, constraints);
        movieList.addActionListener(this);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        dialog.add(moviePanel, constraints);

        constraints.gridx = 0;
        constraints.insets = new Insets(2,35,2,35);
        eventLoopList.setSelectedIndex(selectedEventLoopIndex);
        eventLoopPanel.add(eventLoopList, constraints);
        eventLoopList.addActionListener(this);
        constraints.gridx = 2;
        constraints.insets = new Insets(2,1,2,20);
        dialog.add(eventLoopPanel, constraints);

        skyPanel.setBorder(BorderFactory.createTitledBorder("Select level sky"));
        constraints.insets = new Insets(2,20,2,4);
        constraints.gridx = 0;
        constraints.gridy = 0;
        skyPanel.add(skyText, constraints);
        constraints.gridx = 1;
        constraints.insets = new Insets(2,1,2,20);
        skyList.setSelectedIndex(selectedSkyIndex);
        skyPanel.add(skyList, constraints);
        skyList.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridheight = 2;
        try {
            sky[selectedSkyIndex] = ImageIO.read(this.getClass().getResourceAsStream("/img/sky-images/"+skies[selectedSkyIndex]+".jpg"));
            sky[selectedSkyIndex] = resizeMap(400, 200, sky[selectedSkyIndex]);
            skyFrame[selectedSkyIndex] = new JLabel(new ImageIcon(sky[selectedSkyIndex]));
        }catch(IOException ex) {
            System.out.println("The sky "+skies[selectedSkyIndex]+".jpg couldn't be loaded");
        }
        skyPanel.add(skyFrame[selectedSkyIndex], constraints);

        musicPanel.setBorder(BorderFactory.createTitledBorder("Select music"));
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2,1,2,2);
        musicList.setSelectedIndex(selectedMusicIndex);
        musicPanel.add(musicList, constraints);
        musicList.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        musicPanel.add(playMusic, constraints);
        playMusic.addActionListener(this);
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.insets = new Insets(20,1,2,2);
        musicPanel.add(minBreakText, constraints);
        constraints.gridx = 1;
        constraints.insets = new Insets(20,85,2,2);
        minBreakValue.setText(Integer.toString(selectedMinBreak));
        musicPanel.add(minBreakValue, constraints);
        constraints.insets = new Insets(2,1,2,2);
        constraints.gridx = 0;
        constraints.gridy = 2;
        musicPanel.add(minBreakSlider, constraints);
        minBreakSlider.addChangeListener(this);
        constraints.gridy = 3;
        musicPanel.add(maxBreakText, constraints);
        constraints.gridx = 1;
        constraints.insets = new Insets(20,85,2,2);
        musicPanel.add(maxBreakValue, constraints);
        constraints.insets = new Insets(2,1,2,2);
        constraints.gridy = 4;
        constraints.gridx = 0;
        musicPanel.add(maxBreakSlider, constraints);
        maxBreakSlider.addChangeListener(this);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        dialog.add(skyPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        dialog.add(musicPanel, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(2,6,2,7);
        dialog.add(saveParams, constraints);
        saveParams.addActionListener(this);
        constraints.insets = new Insets(80,1,2,2);
        dialog.add(cancelParams, constraints);
        cancelParams.addActionListener(this);
        constraints.insets = new Insets(0,0,0,0);

        dialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == setList) {
            selectedSetIndex = setList.getSelectedIndex();
        }
        if(e.getSource() == movieList) {
            selectedMovieIndex = movieList.getSelectedIndex();
        }
        if(e.getSource() == eventLoopList) {
            selectedEventLoopIndex = eventLoopList.getSelectedIndex();
        }
        if(e.getSource() == skyList) {
            selectedSkyIndex = skyList.getSelectedIndex();
            render();
        }
        if(e.getSource() == musicList) {
            selectedMusicIndex = musicList.getSelectedIndex();
        }
        if(e.getSource() == playMusic) {
            initMusic();
            audioThread.execute();
        }
        if(e.getSource() == saveParams) {
            try
            {
                EditorState.set = selectedSetIndex + 1;
                if(selectedMovieIndex > 0) EditorState.movie = movies[selectedMovieIndex];
                else EditorState.movie = "";
                EditorState.eventLoop = selectedEventLoopIndex;
                EditorState.sky = skies[selectedSkyIndex];
                if(selectedMusicIndex > 0) EditorState.music = selectedMusicIndex + 1;
                else EditorState.music = 0;
                selectedMinBreak = Integer.parseInt(minBreakValue.getText());
                selectedMaxBreak = Integer.parseInt(maxBreakValue.getText());
                if(selectedMinBreak < 0) selectedMinBreak = -selectedMinBreak;
                if(selectedMinBreak > 1000000) selectedMinBreak = 1000000;
                if(selectedMaxBreak < 0) selectedMaxBreak = -selectedMaxBreak;
                if(selectedMaxBreak > 1000000) selectedMaxBreak = 1000000;
                EditorState.min_break = selectedMinBreak;
                EditorState.max_break = selectedMinBreak;
                if(musicPlayer != null)musicPlayer.close();
                removeDialog();
                closed = true;
                dialog.setVisible(false);
                this.owner.updateEditor();
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,"Values must be numbers", "Wrong value", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancelParams) {
            if(musicPlayer != null)musicPlayer.close();
            removeDialog();
            closed = true;
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == dialog) {
            if(musicPlayer != null)musicPlayer.close();
            removeDialog();
            closed = true;
            dialog.setVisible(false);
        }
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == minBreakSlider) {
            minBreakValue.setText(Integer.toString(minBreakSlider.getValue()));
            selectedMinBreak = Integer.parseInt(minBreakValue.getText());
        }
        if(e.getSource() == maxBreakSlider) {
            maxBreakValue.setText(Integer.toString(maxBreakSlider.getValue()));
            selectedMaxBreak = Integer.parseInt(maxBreakValue.getText());
        }
    }
    @SuppressWarnings("rawtypes")
    public void initMusic() {
        audioThread = new SwingWorker() {

            protected Object doInBackground() throws Exception {
                if(musicPlayer != null)musicPlayer.close();

                switch(selectedMusicIndex) {
                    case 0:
                        musicPlayer = null;
                        break;
                    case 1:
                        bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-2.mp3"));
                        try {
                            musicPlayer = new AdvancedPlayer(bisPlayer);
                            musicPlayer.play();
                        }catch(JavaLayerException ex) {
                            System.out.println("can't play track-2.mp3");
                        }
                        break;
                    case 2:
                        bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-3.mp3"));
                        try {
                            musicPlayer = new AdvancedPlayer(bisPlayer);
                            musicPlayer.play();
                        }catch(JavaLayerException ex) {
                            System.out.println("can't play track-3.mp3");
                        }
                        break;
                    case 3:
                        bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-4.mp3"));
                        try {
                            musicPlayer = new AdvancedPlayer(bisPlayer);
                            musicPlayer.play();
                        }catch(JavaLayerException ex) {
                            System.out.println("can't play track-4.mp3");
                        }
                        break;
                    case 4:
                        bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-5.mp3"));
                        try {
                            musicPlayer = new AdvancedPlayer(bisPlayer);
                            musicPlayer.play();
                        }catch(JavaLayerException ex) {
                            System.out.println("can't play track-5.mp3");
                        }
                        break;
                    case 5:
                        bisPlayer = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/track-6.mp3"));
                        try {
                            musicPlayer = new AdvancedPlayer(bisPlayer);
                            musicPlayer.play();
                        }catch(JavaLayerException ex) {
                            System.out.println("can't play track-6.mp3");
                        }
                        break;
                }

                return null;
            }


        };
    }
    void removeDialog() {
        if(cancelParams != null) dialog.remove(cancelParams);
        if(saveParams != null) dialog.remove(saveParams);
        if(musicPanel != null) dialog.remove(musicPanel);
        if(skyPanel != null) dialog.remove(skyPanel);
        if(eventLoopPanel != null) dialog.remove(eventLoopPanel);
        if(moviePanel != null) dialog.remove(moviePanel);
        if(setPanel != null) dialog.remove(setPanel);
    }
    public BufferedImage resizeMap(int newW, int newH, BufferedImage img) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        img = dimg;
        return img;
    }
}
