package org.mall.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class sasdfafdsaf {


    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat format=new   SimpleDateFormat("yyyy-MM-dd");
//        Calendar start = Calendar.getInstance();
//        Calendar end = Calendar.getInstance();
//        try {
//            start.setTime(format.parse("2020-04-23"));
//            end.setTime(new Date());
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//        while(start.before(end))
//        {
//            Date date =  start.getTime();
//            System.out.println(date);
//            start.add(Calendar.DAY_OF_MONTH,1);
//        }


        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -30);
        Date dateFor30Ago = calendar1.getTime();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date old = sdf.parse("2020-07-08");


        if (dateFor30Ago.getTime() > old.getTime()) {
            System.out.println(dateFor30Ago);
            System.out.println("yes");
        }

    }
}
