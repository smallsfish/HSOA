package com.hassdata.hserp.util;

import com.hassdata.hserp.timer.Task;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GreyStartServlet extends HttpServlet {
    static int count = 0;

    // Servlet的init方法会在Tomcat启动的时候执行
    @Override
    public void init() throws ServletException {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Task taskRun = SpringContextUtil.getBean(Task.class);
                    taskRun.task();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //定时任务每次执行打印日志
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的20:00:00执行，
        calendar.set(year, month, day, 20, 00, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);

        //int period = 60 * 1000;
        //每天的date时刻执行task，每隔60秒重复执行
        //timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        timer.schedule(task, date);
    }
}
