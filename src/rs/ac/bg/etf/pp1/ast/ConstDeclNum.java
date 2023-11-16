// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclNum extends ConstDecl {

    private Type Type;
    private SomeConst SomeConst;
    private SomeConstList SomeConstList;

    public ConstDeclNum (Type Type, SomeConst SomeConst, SomeConstList SomeConstList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.SomeConst=SomeConst;
        if(SomeConst!=null) SomeConst.setParent(this);
        this.SomeConstList=SomeConstList;
        if(SomeConstList!=null) SomeConstList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public SomeConst getSomeConst() {
        return SomeConst;
    }

    public void setSomeConst(SomeConst SomeConst) {
        this.SomeConst=SomeConst;
    }

    public SomeConstList getSomeConstList() {
        return SomeConstList;
    }

    public void setSomeConstList(SomeConstList SomeConstList) {
        this.SomeConstList=SomeConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(SomeConst!=null) SomeConst.accept(visitor);
        if(SomeConstList!=null) SomeConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SomeConst!=null) SomeConst.traverseTopDown(visitor);
        if(SomeConstList!=null) SomeConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SomeConst!=null) SomeConst.traverseBottomUp(visitor);
        if(SomeConstList!=null) SomeConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclNum(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeConst!=null)
            buffer.append(SomeConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeConstList!=null)
            buffer.append(SomeConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclNum]");
        return buffer.toString();
    }
}
