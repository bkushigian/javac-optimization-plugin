package com.tangentiallyrelated.plugin;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;

public class ASTPrintTreeScanner extends TreeScanner {

    ASTPrinter printer;
    public ASTPrintTreeScanner(){
        printer = new ASTPrinter();
    }

    @Override
    public void visitTopLevel(JCTree.JCCompilationUnit tree) {
        printer.push("top-level { " + "package : " + tree.packge + " }");
        printer.newline();
        super.visitTopLevel(tree);
        printer.pop();
        System.out.println(printer.toString());
        printer.clear();
    }

    @Override
    public void visitImport(JCTree.JCImport tree) {
        printer.push("import "  + tree.qualid);
        printer.pop();
    }

    @Override
    public void visitAssignop(JCTree.JCAssignOp tree) {
        printer.push("assign-op");
        super.visitAssignop(tree);
        printer.pop();
    }

    @Override
    public void visitUnary(JCTree.JCUnary tree) {
        printer.push("unary " + tree.getTag().toString() + " ");
        super.visitUnary(tree);
        printer.pop();
    }

    @Override
    public void visitAssign(JCTree.JCAssign tree) {
        printer.push("=");
        super.visitAssign(tree);
        printer.pop();
    }

    @Override
    public void visitBinary(JCTree.JCBinary tree) {
        printer.push("bin-op "  + tree.getTag().toString());
        printer.newline();
        super.visitBinary(tree);
        printer.pop();
    }

    @Override
    public void visitTypeCast(JCTree.JCTypeCast tree) {
        super.visitTypeCast(tree);
    }

    @Override
    public void visitReference(JCTree.JCMemberReference tree) {
        printer.push("ref " + tree.name);
        super.visitReference(tree);
        printer.pop();
    }

    @Override
    public void visitIdent(JCTree.JCIdent tree) {
        printer.push("ident " + tree.name);
        super.visitIdent(tree);
        printer.pop();
    }

    @Override
    public void visitLiteral(JCTree.JCLiteral tree) {
        String value = tree.value instanceof String ? "\"" + tree.value + "\"" : tree.value.toString();
        printer.push("literal " + value + ":" + tree.typetag.toString());
        super.visitLiteral(tree);
        printer.pop();
    }

    @Override
    public void visitBlock(JCTree.JCBlock tree) {
        printer.push("block " + "{ " + "is-static : " + tree.isStatic() + " }");
        printer.newline();
        super.visitBlock(tree);
        printer.pop();
    }

    @Override
    public void visitDoLoop(JCTree.JCDoWhileLoop tree) {
        printer.push("do-loop ");
        super.visitDoLoop(tree);
        printer.pop();
    }

    @Override
    public void visitWhileLoop(JCTree.JCWhileLoop tree) {
        printer.push("while ");
        super.visitWhileLoop(tree);
        printer.pop();
    }

    @Override
    public void visitForLoop(JCTree.JCForLoop tree) {
        printer.push("for ");
        super.visitForLoop(tree);
        printer.pop();
    }

    @Override
    public void visitForeachLoop(JCTree.JCEnhancedForLoop tree) {
        printer.push("for-each " );
        printer.newline();
        super.visitForeachLoop(tree);
        printer.pop();
    }

    @Override
    public void visitCase(JCTree.JCCase tree) {
        super.visitCase(tree);
    }

    @Override
    public void visitTry(JCTree.JCTry tree) {
        super.visitTry(tree);
    }

    @Override
    public void visitCatch(JCTree.JCCatch tree) {
        super.visitCatch(tree);
    }

    @Override
    public void visitBreak(JCTree.JCBreak tree) {
        printer.push("break");
        super.visitBreak(tree);
        printer.pop();
    }

    @Override
    public void visitContinue(JCTree.JCContinue tree) {
        super.visitContinue(tree);
    }

    @Override
    public void visitReturn(JCTree.JCReturn tree) {
        printer.push("return ");
        super.visitReturn(tree);
        printer.pop();
    }

    @Override
    public void visitThrow(JCTree.JCThrow tree) {
        super.visitThrow(tree);
    }

    @Override
    public void visitNewClass(JCTree.JCNewClass tree) {
        printer.push("new-class " );
        super.visitNewClass(tree);
        printer.pop();
    }

    @Override
    public void visitParens(JCTree.JCParens tree) {
        printer.push("parens ");
        super.visitParens(tree);
        printer.pop();
    }

    @Override
    public void visitClassDef(JCTree.JCClassDecl tree) {
        printer.push("class-def: " + tree.name);
        printer.newline();
        super.visitClassDef(tree);
        printer.pop();
    }

    @Override
    public void visitLabelled(JCTree.JCLabeledStatement tree) {
        printer.push("labelled ");
        super.visitLabelled(tree);
        printer.pop();
    }

    @Override
    public void visitAnnotation(JCTree.JCAnnotation tree) {
        printer.push("annotation " );
        super.visitAnnotation(tree);
        printer.pop();
    }

    @Override
    public void visitMethodDef(JCTree.JCMethodDecl tree) {
        printer.push("method-def " + tree.name + " { " + "params : [" + tree.params + "]," + " returns : " + tree.restype + ", modifiers : " + tree.mods + "}");
        printer.newline();
        super.visitMethodDef(tree);
        printer.pop();
    }

    @Override
    public void visitVarDef(JCTree.JCVariableDecl tree) {
        printer.push("var-def " + tree.name + ":" + tree.vartype  + " ");
        super.visitVarDef(tree);
        printer.pop();
    }

    @Override
    public void visitSkip(JCTree.JCSkip tree) {
        super.visitSkip(tree);
    }

    @Override
    public void visitIf(JCTree.JCIf tree) {
        printer.push("if: ");
        super.visitIf(tree);
        printer.pop();
    }

}
