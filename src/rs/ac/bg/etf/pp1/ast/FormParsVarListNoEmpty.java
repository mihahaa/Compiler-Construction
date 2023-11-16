// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class FormParsVarListNoEmpty extends FormParsVarList {

    private FormParsVarList FormParsVarList;
    private Type Type;
    private SomeForm SomeForm;

    public FormParsVarListNoEmpty (FormParsVarList FormParsVarList, Type Type, SomeForm SomeForm) {
        this.FormParsVarList=FormParsVarList;
        if(FormParsVarList!=null) FormParsVarList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.SomeForm=SomeForm;
        if(SomeForm!=null) SomeForm.setParent(this);
    }

    public FormParsVarList getFormParsVarList() {
        return FormParsVarList;
    }

    public void setFormParsVarList(FormParsVarList FormParsVarList) {
        this.FormParsVarList=FormParsVarList;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsVarList!=null) FormParsVarList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(SomeForm!=null) SomeForm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsVarList!=null) FormParsVarList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SomeForm!=null) SomeForm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsVarList!=null) FormParsVarList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SomeForm!=null) SomeForm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsVarListNoEmpty(\n");

        if(FormParsVarList!=null)
            buffer.append(FormParsVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        buffer.append(tab);
        buffer.append(") [FormParsVarListNoEmpty]");
        return buffer.toString();
    }
}
