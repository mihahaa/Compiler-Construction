// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeFormEmpty extends SomeForm {

    private String fename;

    public SomeFormEmpty (String fename) {
        this.fename=fename;
    }

    public String getFename() {
        return fename;
    }

    public void setFename(String fename) {
        this.fename=fename;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SomeFormEmpty(\n");

        buffer.append(" "+tab+fename);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeFormEmpty]");
        return buffer.toString();
    }
}
