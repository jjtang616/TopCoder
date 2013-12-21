import java.util.*;
public class BinaryCode {
    public String[] decode(String message) {
        String[] res=new String[2];
        res[0]=single_test(message,'0');
        res[1]=single_test(message,'1');
        return res;
    }

    private String single_test(String msg,char first){
        int msg_size=msg.length();
        char [] result=new char[msg_size];
        result[0]=first;
        if(msg_size>1){
            result[1]=(char)(msg.charAt(0)-result[0]+'0');
            if(result[1]>'1'||result[1]<'0')
                return "NONE";
        }
        if(msg_size>2){
            for(int i=2;i<msg_size;i++){
                result[i]=(char)(msg.charAt(i-1)-result[i-1]-result[i-2]+2*'0');
                if(result[i]>'1'||result[i]<'0')
                    return "NONE";
            }
        }
        //check
        if(msg_size==1){
            if(result[0]!=msg.charAt(0)){
                return "NONE";
            }
        }
        else if(msg_size==2){
            if((result[0]+result[1])!=(msg.charAt(1)+'0')){
                return "NONE";
            }
        }
        else if(msg_size>=3){
            if((result[msg_size-3]+result[msg_size-2]+result[msg_size-1])!=(msg.charAt(msg_size-1)+2*'0')){
                return "NONE";
            }
        }
       return new String(result);
    }

}


// Powered by FileEdit
// Powered by CodeProcessor
