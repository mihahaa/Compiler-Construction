// generated with ast extension for cup
// version 0.8
// 27/5/2023 20:15:31


package rs.ac.bg.etf.pp1.ast;

public class StatementMap extends Statement {

    private MapDummy MapDummy;
    private Expr Expr;
    private MapEndDummy MapEndDummy;

    public StatementMap (MapDummy MapDummy, Expr Expr, MapEndDummy MapEndDummy) {
        this.MapDummy=MapDummy;
        if(MapDummy!=null) MapDummy.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MapEndDummy=MapEndDummy;
        if(MapEndDummy!=null) MapEndDummy.setParent(this);
    }

    public MapDummy getMapDummy() {
        return MapDummy;
    }

    public void setMapDummy(MapDummy MapDummy) {
        this.MapDummy=MapDummy;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MapEndDummy getMapEndDummy() {
        return MapEndDummy;
    }

    public void setMapEndDummy(MapEndDummy MapEndDummy) {
        this.MapEndDummy=MapEndDummy;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MapDummy!=null) MapDummy.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(MapEndDummy!=null) MapEndDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MapDummy!=null) MapDummy.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MapEndDummy!=null) MapEndDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MapDummy!=null) MapDummy.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MapEndDummy!=null) MapEndDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementMap(\n");

        if(MapDummy!=null)
            buffer.append(MapDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MapEndDummy!=null)
            buffer.append(MapEndDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementMap]");
        return buffer.toString();
    }
}
