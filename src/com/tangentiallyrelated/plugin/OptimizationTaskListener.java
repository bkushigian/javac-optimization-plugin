package com.tangentiallyrelated.plugin;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Context;

public class OptimizationTaskListener implements TaskListener {
    private TreeScanner visitor;

    boolean printAST = false;
    boolean foldConsts = true;

    /**
     *
     * @param args
     */
    public OptimizationTaskListener(String ... args){
        handleArgs(args);
    }

    private void handleArgs(String[] args) {
        for (String arg : args){
            if ("print-ast".equals(arg)){
                printAST = true;
            } else if ("no-fold-constants".equals(args)){
                foldConsts = false;
            } else {
                System.err.println("Unknown option " + arg + "... ignoring");
            }
        }
    }

    @Override
    public void started(TaskEvent e) {}

    @Override
    public void finished(TaskEvent e) {
        if (e.getKind() == TaskEvent.Kind.PARSE){
            if (printAST) {
                visitor = new ASTPrintTreeScanner();
                visitor.scan((JCTree) e.getCompilationUnit());
            }
            if (foldConsts) {
                visitor = new ConstFoldTreeScanner();
                visitor.scan((JCTree)e.getCompilationUnit());
            }
        }
    }
}
