package com.thomasbonderup.manning.milestone1.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class MyCommand extends Command {
    public MyCommand() {
        super("hello", "Prints a command");
    }

    @Override
    public void configure(Subparser subparser) {
        // The name of our command is "hello" and the description printed is
        // "Prints a greeting"
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("The user of the program");
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        System.out.println("Hello " + namespace.getString("user"));
    }
}
