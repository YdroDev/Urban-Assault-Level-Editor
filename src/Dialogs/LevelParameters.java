package Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LevelParameters implements WindowListener {
    private JDialog dialog;
    private String[] skies = new String[72];
    private String[] movies = new String[12];
    private String[] musics = new String[6];

    LevelParameters(Window owner) {
        dialog = new JDialog(owner, "Level parameters", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.addWindowListener(this);

        skies[0] = "1998_01"; skies[1] = "1998_02"; skies[2] = "1998_03"; skies[3] = "1998_05"; skies[4] = "1998_06"; skies[5] = "Am_1"; skies[6] = "Am_2"; skies[7] = "Am_3"; skies[8] = "ARZ1"; skies[9] = "ASKY2"; skies[10] = "BRAUN1"; skies[11] = "CT6"; skies[12] = "H"; skies[13] = "H7"; skies[14] = "HAAMITT1"; skies[15] = "HAAMITT4"; skies[16] = "MOD2"; skies[17] = "MOD4"; skies[18] = "MOD5"; skies[19] = "MOD7"; skies[20] = "MOD8";
        skies[21] = "MOD9"; skies[22] = "MODA"; skies[23] = "MODB"; skies[24] = "Nacht1"; skies[25] = "NACHT2"; skies[26] = "NEWTRY5"; skies[27] = "NOSKY"; skies[28] = "NT1"; skies[29] = "NT2"; skies[30] = "NT3"; skies[31] = "NT5"; skies[32] = "NT6"; skies[33] = "NT7"; skies[34] = "NT8"; skies[35] = "NT9"; skies[36] = "NTA"; skies[37] = "S3_1"; skies[38] = "S3_4"; skies[39] = "SMOD1"; skies[40] = "SMOD2";
        skies[41] = "SMOD3"; skies[42] = "SMOD4"; skies[43] = "SMOD5"; skies[44] = "SMOD6"; skies[45] = "SMOD7"; skies[46] = "SMOD8"; skies[47] = "STERNE"; skies[48] = "wow1"; skies[49] = "wow5"; skies[50] = "wow7"; skies[51] = "wow8"; skies[52] = "wow9"; skies[53] = "wowa"; skies[54] = "wowb"; skies[55] = "wowc"; skies[56] = "wowd"; skies[57] = "wowe"; skies[58] = "wowf"; skies[59] = "wowh"; skies[60] = "wowi";
        skies[61] = "wowj"; skies[62] = "x1"; skies[63] = "x2"; skies[64] = "x4"; skies[65] = "x5"; skies[66] = "x7"; skies[67] = "x8"; skies[68] = "x9"; skies[69] = "xa"; skies[70] = "xb"; skies[71] = "xc";

        movies[0] = "none"; movies[1] = "Intro"; movies[2] = "Tutorial 1"; movies[3] = "Tutorial 2"; movies[4] = "Tutorial 3"; movies[5] = "Ghorkov"; movies[6] = "Taerkasten"; movies[7] = "Mykonian"; movies[8] = "Sulgogar"; movies[9] = "Black sect"; movies[10] = "Lose"; movies[11] = "Win";

        musics[0] = "none"; musics[1] = "Track 2"; musics[2] = "Track 3"; musics[3] = "Track 4"; musics[4] = "Track 5"; musics[5] = "Track 6";
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
