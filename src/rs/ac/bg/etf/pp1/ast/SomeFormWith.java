// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeFormWith extends SomeForm {

    private String fwname;

    public SomeFormWith (String fwname) {
        this.fwname=fwname;
    }

    public String getFwname() {
        return fwname;
    }

    public void setFwname(String fwname) {
        this.fwname=fwname;
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
        buffer.append("SomeFormWith(\n");

        buffer.append(" "+tab+fwname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeFormWith]");
        return buffer.toString();
    }
}
