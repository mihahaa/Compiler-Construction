// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class CharConst extends SomeConst {

    private String cname;
    private Character cval;

    public CharConst (String cname, Character cval) {
        this.cname=cname;
        this.cval=cval;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname=cname;
    }

    public Character getCval() {
        return cval;
    }

    public void setCval(Character cval) {
        this.cval=cval;
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
        buffer.append("CharConst(\n");

        buffer.append(" "+tab+cname);
        buffer.append("\n");

        buffer.append(" "+tab+cval);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConst]");
        return buffer.toString();
    }
}
