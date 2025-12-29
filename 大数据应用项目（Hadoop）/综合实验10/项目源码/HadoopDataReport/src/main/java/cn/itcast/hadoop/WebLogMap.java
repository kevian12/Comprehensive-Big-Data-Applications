package cn.itcast.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
public class WebLogMap extends Mapper<
        LongWritable, Text,Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        DateFormat dateFormatLog =
                new SimpleDateFormat(
                        "dd/MMM/yyyy:HH:mm:ss",
                        Locale.ENGLISH);
        DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String logData = value.toString();
        String[] splitLogData = logData.split("\t");
        String ip = splitLogData[0];
        String date = "";
        String request =
                splitLogData[3].substring(1,splitLogData[3].indexOf(" "));
        String url = splitLogData[3].substring(
                splitLogData[3].indexOf(" "),
                splitLogData[3].lastIndexOf(" ")).trim();
        String code = splitLogData[4];
        try {
            date = dateFormat.format(
                    dateFormatLog.parse(
                            splitLogData[1].substring(1)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        request = ip + "," + date + "," + request + "," + url + "," + code;
        context.write(new Text(request),NullWritable.get());
    }
}