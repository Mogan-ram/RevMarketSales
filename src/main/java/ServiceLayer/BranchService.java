package ServiceLayer;

import DAO.BranchDAO;
import Model.Branch;

import java.util.List;

public class BranchService {
    public BranchDAO branchDAO;

    public BranchService() {
        branchDAO = new BranchDAO();
    }

    public BranchService(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    public List<Branch> getAllBranch(){
        return branchDAO.getAllBranches();
    }
    public String topBranch(){
        return branchDAO.topBranch();
    }
    public String topCity(){
        return branchDAO.topCity();
    }
}
