// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private SomeForm SomeForm;
    private FormParsVarList FormParsVarList;

    public FormPars (Type Type, SomeForm SomeForm, FormParsVarList FormParsVarList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.SomeForm=SomeForm;
        if(SomeForm!=null) SomeForm.setParent(this);
        this.FormParsVarList=FormParsVarList;
        if(FormParsVarList!=null) FormParsVarList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public SomeForm getSomeForm() {
        return SomeForm;
    }

    public void setSomeForm(SomeForm SomeForm) {
        this.SomeForm=SomeForm;
    }

    public FormParsVarList getFormParsVarList() {
        return FormParsVarList;
    }

    public void setFormParsVarList(FormParsVarList FormParsVarList) {
        this.FormParsVarList=FormParsVarList;
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
        if(SomeForm!=null) SomeForm.accept(visitor);
        if(FormParsVarList!=null) FormParsVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SomeForm!=null) SomeForm.traverseTopDown(visitor);
        if(FormParsVarList!=null) FormParsVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SomeForm!=null) SomeForm.traverseBottomUp(visitor);
        if(FormParsVarList!=null) FormParsVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SomeForm!=null)
            buffer.append(SomeForm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsVarList!=null)
            buffer.append(FormParsVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}
