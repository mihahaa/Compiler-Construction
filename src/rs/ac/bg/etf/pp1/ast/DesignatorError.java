// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class DesignatorError extends Designator {

    private DesignatorName DesignatorName;
    private Expr Expr;
    private Expr Expr1;
    private Expr Expr2;
    private ArrayList ArrayList;

    public DesignatorError (DesignatorName DesignatorName, Expr Expr, Expr Expr1, Expr Expr2, ArrayList ArrayList) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
        this.Expr2=Expr2;
        if(Expr2!=null) Expr2.setParent(this);
        this.ArrayList=ArrayList;
        if(ArrayList!=null) ArrayList.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public Expr getExpr2() {
        return Expr2;
    }

    public void setExpr2(Expr Expr2) {
        this.Expr2=Expr2;
    }

    public ArrayList getArrayList() {
        return ArrayList;
    }

    public void setArrayList(ArrayList ArrayList) {
        this.ArrayList=ArrayList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
        if(Expr2!=null) Expr2.accept(visitor);
        if(ArrayList!=null) ArrayList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
        if(Expr2!=null) Expr2.traverseTopDown(visitor);
        if(ArrayList!=null) ArrayList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        if(Expr2!=null) Expr2.traverseBottomUp(visitor);
        if(ArrayList!=null) ArrayList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorError(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr2!=null)
            buffer.append(Expr2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayList!=null)
            buffer.append(ArrayList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorError]");
        return buffer.toString();
    }
}
