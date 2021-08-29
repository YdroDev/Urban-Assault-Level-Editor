package Dialogs;

import State.EditorState;

import javax.swing.*;

public class TypMapGenerator {

    public void generate() {
        if(EditorState.horizontalSectors > 0 && EditorState.verticalSectors > 0) {
            if(JOptionPane.showConfirmDialog(null,"This will change typ_map on every sector based on current set. Are you sure you want to execute this?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                for(int i = 0, rand = 0, fail = 1; i < (EditorState.horizontalSectors * EditorState.verticalSectors); i++) {
                    fail = 1;
                    if(EditorState.set == 1) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 53 && rand < 59) fail = 1;
                            if(rand > 59 && rand < 66) fail = 1;
                            if(rand > 82 && rand < 95) fail = 1;
                            if(rand > 104 && rand < 110) fail = 1;
                            if(rand > 113 && rand < 120) fail = 1;
                            if(rand > 121 && rand < 130) fail = 1;
                            if(rand > 141 && rand < 150) fail = 1;
                            if(rand > 189 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 228) fail = 1;
                            if(rand > 236 && rand < 239) fail = 1;
                        }
                    }else if(EditorState.set == 2) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 24 && rand < 27) fail = 1;
                            if(rand > 104 && rand < 110) fail = 1;
                            if(rand > 113 && rand < 118) fail = 1;
                            if(rand > 131 && rand < 133) fail = 1;
                            if(rand > 133 && rand < 150) fail = 1;
                            if(rand > 195 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 210) fail = 1;
                            if(rand > 225 && rand < 228) fail = 1;
                            if(rand > 230 && rand < 239) fail = 1;
                        }
                    }else if(EditorState.set == 3) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 49 && rand < 59) fail = 1;
                            if(rand > 59 && rand < 66) fail = 1;
                            if(rand > 82 && rand < 100) fail = 1;
                            if(rand > 104 && rand < 110) fail = 1;
                            if(rand > 113 && rand < 121) fail = 1;
                            if(rand > 121 && rand < 130) fail = 1;
                            if(rand > 141 && rand < 150) fail = 1;
                            if(rand > 189 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 228) fail = 1;
                            if(rand > 230 && rand < 239) fail = 1;
                        }
                    }else if(EditorState.set == 4) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 49 && rand < 59) fail = 1;
                            if(rand > 60 && rand < 66) fail = 1;
                            if(rand > 82 && rand < 100) fail = 1;
                            if(rand > 104 && rand < 110) fail = 1;
                            if(rand > 113 && rand < 121) fail = 1;
                            if(rand > 121 && rand < 130) fail = 1;
                            if(rand > 141 && rand < 150) fail = 1;
                            if(rand > 189 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 228) fail = 1;
                            if(rand > 230 && rand < 239) fail = 1;
                        }
                    }else if(EditorState.set == 5) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 95 && rand < 97) fail = 1;
                            if(rand > 116 && rand < 118) fail = 1;
                            if(rand > 131 && rand < 133) fail = 1;
                            if(rand > 137 && rand < 150) fail = 1;
                            if(rand > 191 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 210) fail = 1;
                            if(rand > 225 && rand < 228) fail = 1;
                            if(rand > 230 && rand < 239) fail = 1;
                        }
                    }else if(EditorState.set == 6) {
                        while(fail == 1){
                            rand = (int)(Math.random() * (255 + 1));
                            fail = 0;
                            if(rand > 49 && rand < 59) fail = 1;
                            if(rand > 59 && rand < 66) fail = 1;
                            if(rand > 82 && rand < 95) fail = 1;
                            if(rand > 104 && rand < 110) fail = 1;
                            if(rand > 113 && rand < 121) fail = 1;
                            if(rand > 121 && rand < 130) fail = 1;
                            if(rand > 141 && rand < 150) fail = 1;
                            if(rand > 189 && rand < 198) fail = 1;
                            if(rand > 205 && rand < 207) fail = 1;
                            if(rand > 208 && rand < 228) fail = 1;
                            if(rand > 235 && rand < 239) fail = 1;
                        }
                    }
                    EditorState.typ_map.set(i, rand);
                }
            }
        }
    }
}
