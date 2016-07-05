package project.persistence.entities;

import javax.persistence.Entity;
import java.util.ArrayList;
import org.springframework.util.AutoPopulatingList;
/**
 * Created by gudkj on 11/24/2015.
 */

public class ResultWrapper {

    public ResultWrapper() {}

    private ArrayList<IdHolder> idHolders = new ArrayList<IdHolder>();

    public ArrayList<IdHolder> getIdHolders() { return idHolders; }

    public void setIdHolders(ArrayList<IdHolder> idHolders) { this.idHolders = idHolders; }
}
