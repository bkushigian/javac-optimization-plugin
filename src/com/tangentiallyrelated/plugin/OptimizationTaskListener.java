package com.tangentiallyrelated.plugin;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;

public class OptimizationTaskListener implements TaskListener {
    private TreeScanner visitor;

    boolean printAST = false;
    boolean foldConsts = true;
    boolean printASTAfterFolds = false;

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
            } else if ("no-folds".equals(args)){
                foldConsts = false;
            } else if("print-ast-after-folds".equals(arg)){
                printASTAfterFolds = true;
            } else if ("usage".equals(arg) || "help".equals(arg)){
                System.out.println("OptimizationTaskListener Usage: \n" +
                "  Invoke with either: \n" +
                "      javac -Xplugin:OptimizationPlugin -cp classpath/with/plugin/compilation/base\n" +
                "      javac \"-Xplugin:OptimizationPlugin with space separated args\" -cp classpath/with/plugin/compilation/base\n" +
                "  Optional args include: \n" +
                "      print-ast: Print the original AST\n" +
                "      print-ast-after-folds: Print the AST after right folding--no effect if folding is disabled\n" +
                "      no-folds: Disable folding optimization\n" +
                "      usage | help: This message\n");
                System.exit(0);
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
                if (printASTAfterFolds){
                    visitor = new ASTPrintTreeScanner();
                    visitor.scan((JCTree) e.getCompilationUnit());
                }
            }
        }
    }
}
