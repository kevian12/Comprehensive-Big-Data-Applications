package cn.itcast.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class WebLogDriver {
    public static void main(String[] args) throws IOException,
            InterruptedException, ClassNotFoundException {
        DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String date = dateFormat.format(new Date());
        Configuration configuration = new Configuration();
        configuration.set("mapreduce.input.fileinputformat.input.dir.recursive","true");
        Job job = Job.getInstance(configuration);
        job.setInputFormatClass(CombineTextInputFormat.class);
        job.setJarByClass(WebLogDriver.class);
        job.setMapperClass(WebLogMap.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("/weblog"));
        FileOutputFormat.setOutputPath(job,new Path("/output/weblog/"+date));
        Boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
