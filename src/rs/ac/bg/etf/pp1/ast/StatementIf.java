// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class StatementIf extends Statement {

    private IfDummy IfDummy;
    private IfCondition IfCondition;
    private Statement Statement;
    private NoElseDummy NoElseDummy;

    public StatementIf (IfDummy IfDummy, IfCondition IfCondition, Statement Statement, NoElseDummy NoElseDummy) {
        this.IfDummy=IfDummy;
        if(IfDummy!=null) IfDummy.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.NoElseDummy=NoElseDummy;
        if(NoElseDummy!=null) NoElseDummy.setParent(this);
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

    public NoElseDummy getNoElseDummy() {
        return NoElseDummy;
    }

    public void setNoElseDummy(NoElseDummy NoElseDummy) {
        this.NoElseDummy=NoElseDummy;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfDummy!=null) IfDummy.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(NoElseDummy!=null) NoElseDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfDummy!=null) IfDummy.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(NoElseDummy!=null) NoElseDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfDummy!=null) IfDummy.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(NoElseDummy!=null) NoElseDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIf(\n");

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

        if(NoElseDummy!=null)
            buffer.append(NoElseDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIf]");
        return buffer.toString();
    }
}
