package com.tangentiallyrelated.plugin;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.util.Context;

public class OptimizationPlugin implements Plugin {

    @Override
    public String getName() {
        return "OptimizationPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        task.addTaskListener(new OptimizationTaskListener(args));
    }
}
