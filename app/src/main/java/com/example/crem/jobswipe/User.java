package com.example.crem.jobswipe;

import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eric on 3/1/15.
 */
public class User extends ParseUser{
    private Object profPic;
    private String description;
    private String jobDescription;
    private String tags;
    private ArrayList<String> tagArr;
    private Object mileRadius;

    public User(String username, String password) {

    }

    private void tagsToArr() {
        String[] tagsArr = tags.replaceAll(" ","").split(",");
        for (String str : tagsArr)
            tagArr.add(str);
    }

    public Object getProfPic() { return profPic; }
    public String getDescription() { return description; }
    public String getJobDescription() { return jobDescription; }
    public ArrayList<String> getTags() { return tagArr; }
    public Object getMileRadius() { return mileRadius; }
}
