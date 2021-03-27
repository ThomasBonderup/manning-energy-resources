package com.thomasbonderup.manning.milestone1.cli;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

public class ConsumerCommand extends EnvironmentCommand {


    protected ConsumerCommand(Application application, String name, String description) {
        super(application, name, description);
    }

    @Override
    protected void run(Environment environment, Namespace namespace, Configuration configuration) throws Exception {

    }
}