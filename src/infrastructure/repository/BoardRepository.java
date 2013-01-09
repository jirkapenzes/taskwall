package infrastructure.repository;

import com.db4o.ObjectContainer;
import infrastructure.entity.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 7.12.12
 * Time: 23:23
 */
public class BoardRepository extends Repository<Board> {

    public BoardRepository(ObjectContainer objectContainer1) {
        super(objectContainer1);
    }

    public List<Board> getAll() {
        return new ArrayList<Board>(getObjectContainer().query(Board.class));
    }
}
