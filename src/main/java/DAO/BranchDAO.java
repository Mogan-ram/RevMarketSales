package DAO;

import Model.Branch;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BranchDAO {
    private static final Logger logger = LoggerFactory.getLogger(BranchDAO.class);

//    Function to get the all available branches
    public List<Branch> getAllBranches(){
        List<Branch> branches = new ArrayList<>();
        logger.info("Trying to fetch all branches from db");
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM branch");
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching all branches from database");
            while (rs.next()) {
                branches.add(
                        new Branch(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3)

                        )
                );

            }
            logger.info("Branches fetched Successfully");
        }catch(SQLException e){
            logger.error("Error fetching branches", e);
        }
        return branches;
    }

//    Most profitable branch
    public String topBranch(){
        String res = null;
        logger.info("Tyring to fetch profitable branch");
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("Select branch_name from sales_by_branch order by sales desc limit 1;");
        ResultSet rs = preparedStatement.executeQuery()){
            if(rs.next()){
            res = rs.getString(1);
            logger.info("Profitable branch fetched successfully");
            }
        } catch (SQLException e) {
            logger.warn("Error while fetching the top branch"+e);
            throw new RuntimeException("Error while fetching top branch"+e);
        }
        return res;
    }

//    profitable city
    public String topCity(){
     String branch = topBranch();
     if(branch==null) {
         logger.warn("City couldn't be found since branch was empty");
         return null;
     }
     String city = null;
     String query = "select city from branch where branch_name= ?";
     try(Connection conn = DBConnection.getConnection();
     PreparedStatement preparedStatement = conn.prepareStatement(query);
     ){
         preparedStatement.setString(1,branch);
         try(ResultSet rs = preparedStatement.executeQuery()){
         if(rs.next()){
             city = rs.getString(1);
             logger.info("Profitable city retrieved");
         }
         }
     } catch (SQLException e) {
         logger.error("Error while fetching the top city"+e);
         throw new RuntimeException("Error while fetching top city"+e);
     }
     return  city;
    }
}
