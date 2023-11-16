// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeConstListNoEmpty extends SomeConstList {

    private SomeConstList SomeConstList;
    private SomeConst SomeConst;

    public SomeConstListNoEmpty (SomeConstList SomeConstList, SomeConst SomeConst) {
        this.SomeConstList=SomeConstList;
        if(SomeConstList!=null) SomeConstList.setParent(this);
        this.SomeConst=SomeConst;
        if(SomeConst!=null) SomeConst.setParent(this);
    }

    public SomeConstList getSomeConstList() {
        return SomeConstList;
    }

    public void setSomeConstList(SomeConstList SomeConstList) {
        this.SomeConstList=SomeConstList;
    }

    public SomeConst getSomeConst() {
        return SomeConst;
    }

    public void setSomeConst(SomeConst SomeConst) {
        this.SomeConst=SomeConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SomeConstList!=null) SomeConstList.accept(visitor);
        if(SomeConst!=null) SomeConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SomeConstList!=null) SomeConstList.traverseTopDown(visitor);
        if(SomeConst!=null) SomeConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SomeConstList!=null) SomeConstList.traverseBottomUp(visitor);
        if(SomeConst!=null) SomeConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SomeConstListNoEmpty(\n");

        if(SomeConstList!=null)
            buffer.append(SomeConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeConst!=null)
            buffer.append(SomeConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeConstListNoEmpty]");
        return buffer.toString();
    }
}
