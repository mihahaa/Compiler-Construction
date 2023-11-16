// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class SomeVarWithout extends SomeVar {

    private String woname;

    public SomeVarWithout (String woname) {
        this.woname=woname;
    }

    public String getWoname() {
        return woname;
    }

    public void setWoname(String woname) {
        this.woname=woname;
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
        buffer.append("SomeVarWithout(\n");

        buffer.append(" "+tab+woname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SomeVarWithout]");
        return buffer.toString();
    }
}
