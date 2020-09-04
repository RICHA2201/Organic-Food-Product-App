package com.example.bigbasket;

import android.widget.TextView;

public class Model
{
    String Title;
    String Image;
    String Prize;
    String Pid;
       String Quantity;


    public Model(String title, String image, String prize, String pid, String quantity ) {
        Title = title;
        Image = image;
        Prize = prize;
        Pid = pid;
        Quantity = quantity;

    }




    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPid() {
        return Pid;
    }



    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }

    public Model() {
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
