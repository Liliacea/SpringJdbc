import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CRUDao<T,R> {

    public T insert(T t);

    public T delete(T t);

    public T update(T t);

    public void createTable();
    T findById(R id);

    public List<T> select();
    public T insertMap(T t);

}
