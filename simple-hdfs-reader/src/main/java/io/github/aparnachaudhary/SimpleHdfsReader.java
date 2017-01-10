package io.github.aparnachaudhary;


import java.net.URI;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;


/**
 * @author achaudha
 * @since 10.01.2017
 */
public class SimpleHdfsReader {

    public static void main(final String[] args) throws Exception {
        //name of the user having write permission on HDFS
        System.setProperty("HADOOP_USER_NAME", "hadoop");

        // Path that we need to create in HDFS. Just like Unix/Linux file systems, HDFS file system starts with "/"
        final Path path = new Path("/demo1/HelloWorld.txt");

        // Creates anonymous sub class of DistributedFileSystem to allow calling initialize as DFS will not be usable otherwise
        try (final DistributedFileSystem dFS = new DistributedFileSystem() {
            {
                initialize(new URI("hdfs://localhost:9000/"), new Configuration());
            }
        };
             // Gets input stream for input path using DFS instance
             final FSDataInputStream streamReader = dFS.open(path);
             // Wraps input stream into Scanner to use high level and sophisticated methods
             final Scanner scanner = new Scanner(streamReader)) {

            System.out.println("File Contents: ");
            // Reads tutorials information from file using Scanner
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}