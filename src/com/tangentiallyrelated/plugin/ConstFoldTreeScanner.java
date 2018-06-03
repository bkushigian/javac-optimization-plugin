package com.tangentiallyrelated.plugin;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstFoldTreeScanner extends TreeScanner{

    private final Context context;

    public ConstFoldTreeScanner(Context context){
        this.context = context;
    }

    @Override
    public void visitBinary(JCTree.JCBinary tree) {
        System.out.println("visitBinary: " + tree);
        super.visitBinary(tree);
    }
}
