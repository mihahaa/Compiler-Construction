// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private SomeVar SomeVar;
    private SomeVarList SomeVarList;

    public VarDecl (Type Type, SomeVar SomeVar, SomeVarList SomeVarList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.SomeVar=SomeVar;
        if(SomeVar!=null) SomeVar.setParent(this);
        this.SomeVarList=SomeVarList;
        if(SomeVarList!=null) SomeVarList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public SomeVar getSomeVar() {
        return SomeVar;
    }

    public void setSomeVar(SomeVar SomeVar) {
        this.SomeVar=SomeVar;
    }

    public SomeVarList getSomeVarList() {
        return SomeVarList;
    }

    public void setSomeVarList(SomeVarList SomeVarList) {
        this.SomeVarList=SomeVarList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(SomeVar!=null) SomeVar.accept(visitor);
        if(SomeVarList!=null) SomeVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SomeVar!=null) SomeVar.traverseTopDown(visitor);
        if(SomeVarList!=null) SomeVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SomeVar!=null) SomeVar.traverseBottomUp(visitor);
        if(SomeVarList!=null) SomeVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeVar!=null)
            buffer.append(SomeVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeVarList!=null)
            buffer.append(SomeVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
