// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class ActParsExprListNoEmpty extends ActParsExprList {

    private ActParsExprList ActParsExprList;
    private Expr Expr;

    public ActParsExprListNoEmpty (ActParsExprList ActParsExprList, Expr Expr) {
        this.ActParsExprList=ActParsExprList;
        if(ActParsExprList!=null) ActParsExprList.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActParsExprList getActParsExprList() {
        return ActParsExprList;
    }

    public void setActParsExprList(ActParsExprList ActParsExprList) {
        this.ActParsExprList=ActParsExprList;
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
        if(ActParsExprList!=null) ActParsExprList.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsExprList!=null) ActParsExprList.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsExprList!=null) ActParsExprList.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsExprListNoEmpty(\n");

        if(ActParsExprList!=null)
            buffer.append(ActParsExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsExprListNoEmpty]");
        return buffer.toString();
    }
}
