package cn.itcast.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GenerateLog {
    public static String[] url_paths = {
            "article/112.html",
            "article/113.html",
            "article/114.html",
            "article/115.html",
            "article/116.html",
            "article/117.html",
            "article/118.html",
            "article/119.html",
            "video/821",
            "tag/list"
    };
    public static String[] ip_splices =
            {"102","71","145","33","67","54","164","121"};
    public static String[] status_codes = {"200","404","500"};
    public static void main(String[] args)
            throws Exception {
        //运行jar文件时，通过参数指定日志数据的输出目录
        String path = args[0];
        File file = new File(path);
        while (true){
            FileOutputStream fos = new FileOutputStream(file, true);
            String content = generateLog()+"\n";
            fos.write(content.getBytes());
            //间隔1秒钟生成1条日志数据
            TimeUnit.SECONDS.sleep(1);
            fos.close();
        }
    }
    //随机生成IP地址
    public static String randomIp(){
        int ipNum;
        String ip = "";
        for (int i=0; i<4; i++){
            ipNum = new Random().nextInt(8);
            ip += "."+ip_splices[ipNum];
        }
        return ip.substring(1);
    }
    //随机生成状态码
    public static String randomCode(){
        int codeNum = new Random().nextInt(3);
        return status_codes[codeNum];
    }
    //随机生成URL
    public static String randomUrl(){
        int urlNum = new Random().nextInt(10);
        return url_paths[urlNum];
    }
    //随机生成时间
    public static String getDate() throws ParseException {
        Random random = new Random();
        String date = new String();
        DateFormat dateFormat =
                new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        String days = String.valueOf(random.nextInt(30)+1);
        String hour = String.valueOf(random.nextInt(24));
        String minute = String.valueOf(random.nextInt(60));
        String second = String.valueOf(random.nextInt(60));
        date = days + "/"
                + "Aug" + "/"
                + "2022" + ":"
                + hour + ":"
                + minute + ":"
                + second;
        Date parseDate = dateFormat.parse(date);
        String formatDate = dateFormat.format(parseDate);
        return formatDate;
    }
    //生成日志方法
    public static String generateLog() throws Exception {
        String url = randomUrl();
        String ip = randomIp();
        String code = randomCode();
        String date = getDate();
        String log = ip+"\t"+
                "["+date+"\t"+"+0800"+"]"
                +"\t"+"\"GET /"+url+" HTTP/1.1\""
                +"\t"+code;
        return log;
    }
}
