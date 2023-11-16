// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeVarListNoEmpty extends SomeVarList {

    private SomeVarList SomeVarList;
    private SomeVar SomeVar;

    public SomeVarListNoEmpty (SomeVarList SomeVarList, SomeVar SomeVar) {
        this.SomeVarList=SomeVarList;
        if(SomeVarList!=null) SomeVarList.setParent(this);
        this.SomeVar=SomeVar;
        if(SomeVar!=null) SomeVar.setParent(this);
    }

    public SomeVarList getSomeVarList() {
        return SomeVarList;
    }

    public void setSomeVarList(SomeVarList SomeVarList) {
        this.SomeVarList=SomeVarList;
    }

    public SomeVar getSomeVar() {
        return SomeVar;
    }

    public void setSomeVar(SomeVar SomeVar) {
        this.SomeVar=SomeVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SomeVarList!=null) SomeVarList.accept(visitor);
        if(SomeVar!=null) SomeVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SomeVarList!=null) SomeVarList.traverseTopDown(visitor);
        if(SomeVar!=null) SomeVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SomeVarList!=null) SomeVarList.traverseBottomUp(visitor);
        if(SomeVar!=null) SomeVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SomeVarListNoEmpty(\n");

        if(SomeVarList!=null)
            buffer.append(SomeVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeVar!=null)
            buffer.append(SomeVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeVarListNoEmpty]");
        return buffer.toString();
    }
}
