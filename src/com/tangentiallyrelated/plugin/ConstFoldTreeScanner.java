package com.tangentiallyrelated.plugin;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Context;

public class ConstFoldTreeScanner extends TreeScanner{

    private final Context context;

    public ConstFoldTreeScanner(Context context){
        this.context = context;
    }

    @Override
    public void visitMethodDef(JCTree.JCMethodDecl tree) {
        System.out.println("visitMethodDef: " + tree.name);
        super.visitMethodDef(tree);
    }

    @Override
    public void visitBinary(JCTree.JCBinary tree) {
        System.out.println("    visitBinary: " + tree);
        super.visitBinary(tree);
        TreeFolder.foldr(tree);
        System.out.println("  revisitBinary: " + tree);
    }
}
