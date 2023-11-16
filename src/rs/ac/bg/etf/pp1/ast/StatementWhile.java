// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class StatementWhile extends Statement {

    private WhileDummy WhileDummy;
    private Cond Cond;
    private Statement Statement;

    public StatementWhile (WhileDummy WhileDummy, Cond Cond, Statement Statement) {
        this.WhileDummy=WhileDummy;
        if(WhileDummy!=null) WhileDummy.setParent(this);
        this.Cond=Cond;
        if(Cond!=null) Cond.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public WhileDummy getWhileDummy() {
        return WhileDummy;
    }

    public void setWhileDummy(WhileDummy WhileDummy) {
        this.WhileDummy=WhileDummy;
    }

    public Cond getCond() {
        return Cond;
    }

    public void setCond(Cond Cond) {
        this.Cond=Cond;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileDummy!=null) WhileDummy.accept(visitor);
        if(Cond!=null) Cond.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileDummy!=null) WhileDummy.traverseTopDown(visitor);
        if(Cond!=null) Cond.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileDummy!=null) WhileDummy.traverseBottomUp(visitor);
        if(Cond!=null) Cond.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementWhile(\n");

        if(WhileDummy!=null)
            buffer.append(WhileDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Cond!=null)
            buffer.append(Cond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementWhile]");
        return buffer.toString();
    }
}
