package com.tangentiallyrelated.plugin;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.BiFunction;

public class TreeFolder {

    /**
     * For ease of use, lookup table for operators
     */
    private static HashMap<JCTree.Tag, BiFunction<JCTree.JCLiteral, JCTree.JCLiteral, JCTree.JCLiteral>> interpreters;

    static {
        interpreters = new HashMap<>();
        interpreters.put(JCTree.Tag.PLUS,
                (l, r) -> createIntLiteral(((Integer)l.getValue()) + ((Integer)r.getValue())));
        interpreters.put(JCTree.Tag.MUL,
                (l, r) -> createIntLiteral(((Integer)l.getValue()) * ((Integer)r.getValue())));
    }

    /**
     * Create a new JCTree.JCLiteral with value {@code value}
     * @param value
     * @return
     */
    private static JCTree.JCLiteral createIntLiteral(int value){
        try {
            Constructor<JCTree.JCLiteral> constructor;
            constructor = JCTree.JCLiteral.class.getDeclaredConstructor(TypeTag.class, Object.class);
            constructor.setAccessible(true);
            return constructor.newInstance(TypeTag.INT, value);
        } catch (NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Test if the operator is associative---here operators are specified with
     * the {@code JCTree.Tag} type.
     * @param tag
     * @return
     */
    private static boolean tagIsAssociative(JCTree.Tag tag){
        switch (tag){
            case PLUS:
            case MUL:
                return true;
            default:
                return false;
        }
    }

    /**
     * Test if this is right foldable
     * @param tree AST node to test
     * @return {@code true} if we can bold this and {@code false} otherwise
     */
    private static boolean isRightFoldable(JCTree.JCBinary tree){
        final JCTree.Tag tag = tree.getTag();
        final JCTree.JCExpression lhs = tree.lhs;
        final JCTree.JCExpression rhs = tree.rhs;
        return tagIsAssociative(tag) && interpreters.containsKey(tag)
                                     && rhs instanceof JCTree.JCLiteral
                                     && lhs instanceof JCTree.JCBinary
                                     && ((JCTree.JCLiteral)((JCTree.JCBinary)lhs).rhs).typetag == TypeTag.INT
                                     && ((JCTree.JCLiteral) rhs).typetag == TypeTag.INT;
    }

    /**
     * Perform a right constant fold if possible, otherwise do nothing
     * @param tree
     */
    static void foldr(JCTree.JCBinary tree){

        if (isRightFoldable(tree))
        {
            final JCTree.Tag tag = tree.getTag();
            final JCTree.JCBinary lhs = (JCTree.JCBinary)tree.lhs;
            final JCTree.JCLiteral rhs = (JCTree.JCLiteral)tree.rhs;
            final BiFunction<JCTree.JCLiteral, JCTree.JCLiteral, JCTree.JCLiteral> fn = interpreters.get(tag);

            if (lhs.getTag() == tag && lhs.rhs instanceof JCTree.JCLiteral){
                final JCTree.JCLiteral lrLit = (JCTree.JCLiteral)lhs.rhs;
                final JCTree.JCExpression llExpr = lhs.lhs;
                final JCTree.JCLiteral newLiteral = fn.apply(lrLit, rhs);
                if (newLiteral == null){
                   return;
                }
                tree.lhs = llExpr;
                tree.rhs = newLiteral;
            }
        }
    }
}
