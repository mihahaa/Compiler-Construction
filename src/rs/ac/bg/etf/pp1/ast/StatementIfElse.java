// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class StatementIfElse extends Statement {

    private IfDummy IfDummy;
    private IfCondition IfCondition;
    private Statement Statement;
    private ElseDummy ElseDummy;
    private Statement Statement1;

    public StatementIfElse (IfDummy IfDummy, IfCondition IfCondition, Statement Statement, ElseDummy ElseDummy, Statement Statement1) {
        this.IfDummy=IfDummy;
        if(IfDummy!=null) IfDummy.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseDummy=ElseDummy;
        if(ElseDummy!=null) ElseDummy.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
    }

    public IfDummy getIfDummy() {
        return IfDummy;
    }

    public void setIfDummy(IfDummy IfDummy) {
        this.IfDummy=IfDummy;
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseDummy getElseDummy() {
        return ElseDummy;
    }

    public void setElseDummy(ElseDummy ElseDummy) {
        this.ElseDummy=ElseDummy;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfDummy!=null) IfDummy.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseDummy!=null) ElseDummy.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfDummy!=null) IfDummy.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseDummy!=null) ElseDummy.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfDummy!=null) IfDummy.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseDummy!=null) ElseDummy.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIfElse(\n");

        if(IfDummy!=null)
            buffer.append(IfDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseDummy!=null)
            buffer.append(ElseDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIfElse]");
        return buffer.toString();
    }
}
