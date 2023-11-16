// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class MapDummy implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private Designator Designator;
    private Designator Designator1;
    private String mapname;

    public MapDummy (Designator Designator, Designator Designator1, String mapname) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
        this.mapname=mapname;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname=mapname;
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
        if(Designator!=null) Designator.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MapDummy(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+mapname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MapDummy]");
        return buffer.toString();
    }
}
