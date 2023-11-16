// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class FactorMethod extends Factor {

    private MethodName MethodName;
    private ActParsHelper ActParsHelper;

    public FactorMethod (MethodName MethodName, ActParsHelper ActParsHelper) {
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
        this.ActParsHelper=ActParsHelper;
        if(ActParsHelper!=null) ActParsHelper.setParent(this);
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public ActParsHelper getActParsHelper() {
        return ActParsHelper;
    }

    public void setActParsHelper(ActParsHelper ActParsHelper) {
        this.ActParsHelper=ActParsHelper;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodName!=null) MethodName.accept(visitor);
        if(ActParsHelper!=null) ActParsHelper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
        if(ActParsHelper!=null) ActParsHelper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        if(ActParsHelper!=null) ActParsHelper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorMethod(\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsHelper!=null)
            buffer.append(ActParsHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorMethod]");
        return buffer.toString();
    }
}
