package algo;

/**
 * Created by ssiddiqu on 2/1/18.
 */
public class IsUniqueString {
    public static boolean isUnique(String str) {
        if((str==null)||(str.isEmpty()))
            return true;
        for(int i=0; i< str.length();i++) {
            char cmp = str.charAt(i);
            // no need to check the last character
            for(int j=i+1; j< str.length();j++) {
                if(cmp == str.charAt(j))
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str ="abcdef";
        System.out.println("String "+str+" is unique ="+isUnique(str));
        str ="abcdefa";
        System.out.println("String "+str+" is unique ="+isUnique(str));
    }
}
