package algo;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ssiddiqu on 2/5/18.
 */
public class ItemOrderSort {
    public enum COLOR {
        RED,
        BLACK,
        YELLOW,
        ORANGE,
        BLUE,
        GREEN
    };
    static int compare(COLOR one, COLOR two) {
        int o = one.ordinal();
        int t = two.ordinal();
        return o-t;
    }
    static COLOR[] corder = {COLOR.RED,COLOR.BLACK,COLOR.YELLOW, COLOR.ORANGE,COLOR.BLUE,COLOR.RED};
    public static void sortColors(COLOR[] colors) {
        if((colors==null)||(colors.length==0))
            return;
        int gIndx = 0;
        for(COLOR clr: corder) {
            // all colors < gIndx have been sorted
            for(int i=gIndx; i<colors.length;i++) {
                if(clr==colors[i]) {
                    COLOR tmp = colors[i];
                    colors[i] = colors[gIndx];
                    colors[gIndx] = tmp;
                    gIndx++;
                }
            }
            if(gIndx>=colors.length-1)
                break;
        }
    }
    public static void bubbleSort(COLOR[] colors) {
        for(int i=colors.length-1;i>0; i--) {
            // elements to the right of i are sorted
            for(int j=0; j<i;j++) {
                if (compare(colors[j],colors[j+1])<0) {
                    COLOR tmp = colors[j+1];
                    colors[j+1] = colors[j];
                    colors[j] = tmp;
                }
            }
        }
    }
    public static String toString(COLOR[] colors) {
        StringBuilder strb = new StringBuilder();
        for(COLOR clr:colors) {
            strb.append(clr);
            strb.append(",");
        }
        return strb.toString();
    }
    public static void main(String[] args) {
        COLOR[] colors = new COLOR[]{COLOR.BLACK, COLOR.RED, COLOR.ORANGE,COLOR.BLUE};
        sortColors(colors);
        System.out.println(toString(colors));
        bubbleSort(colors);
        System.out.println(toString(colors));

    }

}
