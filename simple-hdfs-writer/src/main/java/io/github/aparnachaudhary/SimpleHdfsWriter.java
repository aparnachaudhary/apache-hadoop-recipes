package io.github.aparnachaudhary;

import java.io.PrintWriter;
import java.net.URI;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

/**
 * @author aparna
 * @since 10.01.2017
 */
public class SimpleHdfsWriter {

    public static void main(final String[] args) throws Exception {
        //name of the user having write permission on HDFS
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        // Path that we need to create in HDFS. Just like Unix/Linux file systems, HDFS file system starts with "/"
        final Path path = new Path("/demo1/HelloWorld.txt");

        // Uses try with resources in order to avoid close calls on resources
        // Creates anonymous sub class of DistributedFileSystem to allow calling initialize as DFS will not be usable otherwise
        try (final DistributedFileSystem dFS = new DistributedFileSystem() {
            {
                final String hdfsHost = System.getProperty("hdfsHost", "localhost");
                final String hdfsPort = System.getProperty("hdfsPort", "9000");
                final String hdfsUrl = String.format("hdfs://%s:%s/", hdfsHost, hdfsPort);
                initialize(new URI(hdfsUrl), new Configuration());
            }
        };
             // Gets output stream for input path using DFS instance
             final FSDataOutputStream streamWriter = dFS.create(path);
             // Wraps output stream into PrintWriter to use high level and sophisticated methods
             final PrintWriter writer = new PrintWriter(streamWriter)) {
            // Writes tutorials information to file using print writer
            writer.println("First Text in HDFS: " + new Date());

            System.out.println("File Written to HDFS successfully!");
        }
    }
}
