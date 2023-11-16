// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeVarEmpty extends SomeVar {

    private String ename;

    public SomeVarEmpty (String ename) {
        this.ename=ename;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename=ename;
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
        buffer.append("SomeVarEmpty(\n");

        buffer.append(" "+tab+ename);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeVarEmpty]");
        return buffer.toString();
    }
}
