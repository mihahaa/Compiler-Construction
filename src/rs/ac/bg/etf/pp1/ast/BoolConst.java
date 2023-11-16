// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class BoolConst extends SomeConst {

    private String bname;
    private Boolean bval;

    public BoolConst (String bname, Boolean bval) {
        this.bname=bname;
        this.bval=bval;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname=bname;
    }

    public Boolean getBval() {
        return bval;
    }

    public void setBval(Boolean bval) {
        this.bval=bval;
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
        buffer.append("BoolConst(\n");

        buffer.append(" "+tab+bname);
        buffer.append("\n");

        buffer.append(" "+tab+bval);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst]");
        return buffer.toString();
    }
}
