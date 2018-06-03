package com.tangentiallyrelated.plugin;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Context;

public class OptimizationTaskListener implements TaskListener {
    private final TreeScanner treeVisitor;

    public OptimizationTaskListener(Context context){
        this.treeVisitor = new ConstFoldTreeScanner(context);
    }

    @Override
    public void started(TaskEvent e) {}

    @Override
    public void finished(TaskEvent e) {
        if (e.getKind() == TaskEvent.Kind.PARSE){
            treeVisitor.scan((JCTree)e.getCompilationUnit());
        }
    }
}
