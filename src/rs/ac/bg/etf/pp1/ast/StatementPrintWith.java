// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class StatementPrintWith extends Statement {

    private Expr Expr;
    private Integer printnum;

    public StatementPrintWith (Expr Expr, Integer printnum) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.printnum=printnum;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Integer getPrintnum() {
        return printnum;
    }

    public void setPrintnum(Integer printnum) {
        this.printnum=printnum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrintWith(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+printnum);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrintWith]");
        return buffer.toString();
    }
}
