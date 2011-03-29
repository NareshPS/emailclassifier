import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.FileInputFormat;

import aMapReduce.EMailClassifierMapper;
import aMapReduce.EMailClassifierReducer;


public class EMailClassifier {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(EMailClassifier.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(conf, "/home/Naresh/LifeParser/Input");
		FileOutputFormat.setOutputPath(conf, new Path("/home/Naresh/LifeParser/Output"));

		conf.setMapperClass(EMailClassifierMapper.class);
		conf.setCombinerClass(EMailClassifierReducer.class);

		conf.setReducerClass(EMailClassifierReducer.class);

		client.setConf(conf);
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
