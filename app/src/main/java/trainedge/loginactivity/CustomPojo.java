package trainedge.loginactivity;

import java.util.ArrayList;

/**
 * Created by hp on 14-Apr-17.
 */

public class CustomPojo {
    private String name;
    private String time,content;
    private ArrayList<CustomPojo> customPojo =new ArrayList<>();

    public CustomPojo() {

    }
    //getting tvDesc value
    public String getContent(){return content;}
    //setting tvDesc value
    public void setContent(String content){this.content=content;}

    public String getTime(){return time;}
    public void setTime(String time){this.time=time;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
