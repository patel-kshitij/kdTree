import java.util.Set;

public interface  Searchable {
    public boolean build( Set<Point> points );
    public boolean add( Point point );
    public boolean find( Point point );
    public int size();
    public Set<Point> findInRange( Point minCorner, Point maxCorner );

}
