package ru.job4j.zip;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Args.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 07.02.2019.
 */
public class Args {
    /**
     * Command line parser.
     */
    private CommandLine parser;

    /**
     * Constructor with array parameters command line.
     * @param args
     */
    public Args(String[] args) {
        Options options = new Options();
        Option optionDirectory = Option.builder("d")
                .required()
                .hasArg()
                .desc("directory for packaging")
                .build();
        Option optionOutput = Option.builder("o")
                .required()
                .hasArg()
                .desc("output name package")
                .build();
        Option optionExclude = Option.builder("e")
                .hasArgs()
                .valueSeparator(',')
                .desc("exclude file from packaging")
                .build();
        options.addOption(optionDirectory);
        options.addOption(optionOutput);
        options.addOption(optionExclude);
        try {
            parser = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }
    }

    /**
     * Get directory for packaging.
     * @return path directory.
     */
   public String getDirectory() {
       return parser.getOptionValue("d");
   }

    /**
     * Get extension for excluding from packaging.
     * @return list extension.
     */
   public List<String> getExcule() {
       return parser.getOptionValue("e") != null ? Arrays.asList(parser.getOptionValues("e")) : new ArrayList<>();
   }

    /**
     * Get output package file.
     * @return path output file.
     */
    public String getOutput() {
        return this.parser.getOptionValue("o");
   }
}