package com.tangentiallyrelated.plugin;

/**
 * A helper class to add structured AST representation to a string builder
 */
class ASTPrinter {
    /**
     * Current depth into the AST
     */
    private int depth = 0;

    private StringBuilder sb = new StringBuilder();

    /**
     * Should we print a newline at the next chance? Used by {@code indent()}
     */
    private boolean newline = true;

    private final String indentString = "    ";

    public int getDepth() { return depth; }

    /**
     * Reset the printer
     * @return
     */
    void clear(){
        sb = new StringBuilder();
        newline = true;
        depth = 0;
    }

    /**
     * Mark a newline to be printed at the next chance
     */
    void newline(){
        newline = true;
    }

    /**
     * If a newline is ready to be printed (i.e. {@code newline()} is true), print it
     * and indent. Otherwise, do nothing.
     */
    private void indent(){
        if (newline){
            sb.append("\n");
            newline = false;
            for (int i = 0; i < depth; ++i){
                sb.append(indentString);
            }
        }
    }

    /**
     * Push an AST node by passing a string representative (i.e. "literal 32:INT")
     * @param s
     */
    void push(String s){
        indent();
        sb.append('(');
        sb.append(s);
        ++depth;
    }

    /**
     * Pop from a node
     */
    void pop(){
        sb.append(")");
        newline();
        --depth;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
