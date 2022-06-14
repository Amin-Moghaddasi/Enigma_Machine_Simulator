import java.util.*;
import java.lang.*;
class String_Tools {
    public StringBuilder Shift(String input, int steps) { 
        /* Shifts input String according to given steps */
        int len = input.length();
        StringBuilder output=new StringBuilder(len);
        if(steps>=len){
            int steps1 = steps%len;
            output.append(input, steps1, len);
            output.append(input, 0, steps1);
        }
        else{
            output.append(input, steps, len);
            output.append(input, 0, steps);
        }
        return output;
    }
}
class Enigma {
    public StringBuilder Reflected_Message(StringBuilder msg, String Reflector) { 
        /* Passes a String through a refletor and return the reflected message */
        StringBuilder orig = new StringBuilder();
        orig.append(msg);
        StringBuilder ref = new StringBuilder();
        ref.append(Reflector);
        StringBuilder reflected_msg = new StringBuilder();
        for (int i=0;i<orig.length();i++) {
            for (int j=0;j<ref.length();j++) {
                if (orig.charAt(i) == ref.charAt(j)) {
                    int k = ref.length() - j - 1;
                    char c = ref.charAt(k);
                    reflected_msg.insert(i,c);
                }
            }
        }
        return reflected_msg;
    }
    public StringBuilder Decoded_or_Encoded(StringBuilder Message, String Standard_Rotor, int[] key) {
        /* Encodes given message String according to given key and default rotor */
        String_Tools st = new String_Tools();
        int[] shift_counter = { 0, 0, 0, 0, 0 };
        int[] index_keeper = new int[5];
        char c, c1, c2, c3, c4, c5 = 0;
        int i, i1,i2,i3,len;
        len = Message.length();
        for(i2=0;i2<5;i2++)
        key[i2]=(key[i2]%84);
        StringBuilder coded1 = new StringBuilder();
        StringBuilder coded2 = new StringBuilder();
        StringBuilder r1 = st.Shift(Standard_Rotor, key[0]);
        StringBuilder r2 = st.Shift(Standard_Rotor, key[1]);
        StringBuilder r3 = st.Shift(Standard_Rotor, key[2]);
        StringBuilder r4 = st.Shift(Standard_Rotor, key[3]);
        StringBuilder r5 = st.Shift(Standard_Rotor, key[4]);
        System.arraycopy(key, 0, shift_counter, 0, key.length);
        /* Algorithm of pushing message through rotors (half of progress)*/
            for (i=0;i<len; i++) {
                for (i1 = 0; i1 < Standard_Rotor.length(); i1++) {
                    if (Message.charAt(i) == Standard_Rotor.charAt(i1)) {
                        c = Standard_Rotor.charAt(i1);
                        index_keeper[4] = r5.indexOf(String.valueOf(c));
                        r5.delete(0,r5.length());
                        r5.append(st.Shift(Standard_Rotor, shift_counter[4]));
                        shift_counter[4]=(shift_counter[4]%84)+1;
                        if (shift_counter[4] == r5.length()){
                            shift_counter[4]=(shift_counter[4]%84)+1;
                            shift_counter[3]=(shift_counter[3]%84)+1;
                        }
                        if (shift_counter[3] == r4.length()){
                            shift_counter[3]=(shift_counter[3]%84)+1;
                            shift_counter[2]=(shift_counter[2]%84)+1;
                        }
                        if (shift_counter[2] == r3.length()){
                            shift_counter[2]=(shift_counter[2]%84)+1;
                            shift_counter[1]=(shift_counter[1]%84)+1;
                        }
                        if (shift_counter[1] == r2.length()){
                            shift_counter[1]=(shift_counter[1]%84)+1;
                            shift_counter[0]=(shift_counter[0]%84)+1;
                        }
                        if (shift_counter[0] == r1.length()){
                            shift_counter[0]=(shift_counter[0]%84)+1;
                        }
                        c1 = Standard_Rotor.charAt(index_keeper[4]);
                        index_keeper[3] = r4.indexOf(String.valueOf(c1));
                        r4.delete(0,r4.length());
                        r4.append(st.Shift(Standard_Rotor, shift_counter[3]));
                        c2 = Standard_Rotor.charAt(index_keeper[3]);
                        index_keeper[2] = r3.indexOf(String.valueOf(c2));
                        r3.delete(0,r3.length());
                        r3.append(st.Shift(Standard_Rotor, shift_counter[2]));
                        c3 = Standard_Rotor.charAt(index_keeper[2]);
                        index_keeper[1] = r2.indexOf(String.valueOf(c3));
                        r2.delete(0,r2.length());
                        r2.append(st.Shift(Standard_Rotor, shift_counter[1]));
                        c4 = Standard_Rotor.charAt(index_keeper[1]);
                        index_keeper[0] = r1.indexOf(String.valueOf(c4));
                        c5 = r1.charAt(index_keeper[0]);
                        r1.delete(0,r1.length());
                        r1.append(st.Shift(Standard_Rotor, shift_counter[0]));
                    }
                }
                coded1.append(c5);
                /* Gets message passed trhough rotors  */
            }
                int [] key2 = new int [5];
                for(i3=0;i3<5;i3++)
                key2[i3]=shift_counter[i3];
            StringBuilder trans = new StringBuilder();
            trans.append(Reflected_Message(coded1, Standard_Rotor));
            /* Pushes half-encoded/decoded message through reflector */
            /* Second half of pushing processed message through rotors to complete the encoding/decoding process  */
            for (i = 0; i <trans.length(); i++) {
                for (i1 = 0; i1 < Standard_Rotor.length(); i1++) {
                    if (trans.charAt(i) == Standard_Rotor.charAt(i1)) {
                        c = Standard_Rotor.charAt(i1);
                        index_keeper[0] = r1.indexOf(String.valueOf(c));
                        r1.delete(0,r1.length());
                        r1.append(st.Shift(Standard_Rotor, shift_counter[0]));
                        shift_counter[4]=(shift_counter[4]%84)+1;;
                        if (shift_counter[4] == r5.length()){
                            shift_counter[4]=(shift_counter[4]%84)+1;
                            shift_counter[3]=(shift_counter[3]%84)+1;
                        }
                        if (shift_counter[3] == r4.length()){
                            shift_counter[3]=(shift_counter[3]%84)+1;
                            shift_counter[2]=(shift_counter[2]%84)+1;
                        }
                        if (shift_counter[2] == r3.length()){
                            shift_counter[2]=(shift_counter[2]%84)+1;
                            shift_counter[1]=(shift_counter[1]%84)+1;
                        }
                        if (shift_counter[1] == r2.length()){
                            shift_counter[1]=(shift_counter[1]%84)+1;
                            shift_counter[0]=(shift_counter[0]%84)+1;
                        }
                        if (shift_counter[0] == r1.length()){
                            shift_counter[0]=(shift_counter[0]%84)+1;
                        }
                        c1 = Standard_Rotor.charAt(index_keeper[0]);
                        index_keeper[1] = r2.indexOf(String.valueOf(c1));
                        r2.delete(0,r2.length());
                        r2.append(st.Shift(Standard_Rotor, shift_counter[1]));
                        c2 = Standard_Rotor.charAt(index_keeper[1]);
                        index_keeper[2] = r3.indexOf(String.valueOf(c2));
                        r3.delete(0,r3.length());
                        r3.append(st.Shift(Standard_Rotor, shift_counter[2]));
                        c3 = Standard_Rotor.charAt(index_keeper[2]);
                        index_keeper[3] = r2.indexOf(String.valueOf(c3));
                        r4.delete(0,r4.length());
                        r4.append(st.Shift(Standard_Rotor, shift_counter[3]));
                        c4 = Standard_Rotor.charAt(index_keeper[3]);
                        index_keeper[4] = r4.indexOf(String.valueOf(c4));
                        c5 = r5.charAt(index_keeper[4]);
                        r5.delete(0,r5.length());
                        r5.append(st.Shift(Standard_Rotor, shift_counter[4]));
                    }
                }
                coded2.append(c5);
                coded1.delete(0, coded1.length());
            }
            StringBuilder rotor_State = new StringBuilder ();
            for (int counter=0;counter<5;counter ++){
                rotor_State.append(shift_counter[counter]);
                rotor_State.append(".");
            }
            coded2.append("$$$$$$$$ Current rotor state is :");
            coded2.append(rotor_State);
        return coded2;
        /* Returns encoded/decoded version of given message, using given key */
    }
}
class Enigma_Machine {
       public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Enigma enigma = new Enigma();
        int[] key = new int[5];
        StringBuilder msg = new StringBuilder();
        StringBuilder encoded = new StringBuilder();
        String message;
        String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789./()[], ;:%_'+-=*&#@!?";
        System.out.println("Enter your message to Encode/Decode :");
        message = scan.nextLine();
        msg.append(message);
        System.out.println("Enter your KEY (each section should be between 0 and 84) :");
        for(int g=0;g<5;g++) 
            key[g] = scan.nextInt();
        encoded = enigma.Decoded_or_Encoded(msg,temp,key);
        System.out.print("Original Message : ");
        System.out.println(message);
        System.out.print("Original Message Length : ");
        System.out.println(message.length());
        System.out.print("Encoded/Decoded Message: $");
        System.out.println(encoded.toString());
        System.out.print("Encoded/Decoded Message Length : ");
        String enc = encoded.substring(0,encoded.indexOf("$"));
        System.out.println(enc.length());
    }
}
