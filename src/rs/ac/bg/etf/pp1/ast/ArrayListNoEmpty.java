// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class ArrayListNoEmpty extends ArrayList {

    private ArrayList ArrayList;
    private Expr Expr;

    public ArrayListNoEmpty (ArrayList ArrayList, Expr Expr) {
        this.ArrayList=ArrayList;
        if(ArrayList!=null) ArrayList.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ArrayList getArrayList() {
        return ArrayList;
    }

    public void setArrayList(ArrayList ArrayList) {
        this.ArrayList=ArrayList;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayList!=null) ArrayList.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayList!=null) ArrayList.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayList!=null) ArrayList.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayListNoEmpty(\n");

        if(ArrayList!=null)
            buffer.append(ArrayList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayListNoEmpty]");
        return buffer.toString();
    }
}
