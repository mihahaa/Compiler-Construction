// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class NumberConst extends SomeConst {

    private String nname;
    private Integer nval;

    public NumberConst (String nname, Integer nval) {
        this.nname=nname;
        this.nval=nval;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname=nname;
    }

    public Integer getNval() {
        return nval;
    }

    public void setNval(Integer nval) {
        this.nval=nval;
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
        buffer.append("NumberConst(\n");

        buffer.append(" "+tab+nname);
        buffer.append("\n");

        buffer.append(" "+tab+nval);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberConst]");
        return buffer.toString();
    }
}
