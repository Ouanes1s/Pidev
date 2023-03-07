/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.CommentEvenement;
import entity.Evenements;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author 21627
 */
public class CommentService {
    
    
        
    private final Connection cnx;

    private static CommentService instance;
    
    EvenementService es = new EvenementService();
        public CommentService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static CommentService getInstance()
    {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance; 
    }
    
        public void addComment(CommentEvenement c) throws SQLDataException, SQLException {
        
                String query ="INSERT INTO `comment`( `idevenement`, `iduser`, `commentaire`) VALUES (?,?,?)";
 
                PreparedStatement st;
        
        
                st = cnx.prepareStatement(query);
                st.setInt(1,c.getIdEvt().getId());
                st.setInt(2,c.getId().getId());
                st.setString(3,c.getComment());
                st.executeUpdate();

        
        
    }

    public void modifieComment(CommentEvenement c) throws SQLDataException {
       
         try {
             PreparedStatement pst=cnx.prepareStatement("UPDATE `comment` SET `commentaire`=? WHERE `idcomment` = ?");
             pst.setString(1,c.getComment());
             pst.setInt(2,c.getIdComment());
             pst.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    public void deleteComment(int idComment) throws SQLDataException {
    try {
             Statement st=cnx.createStatement();
             String req= "DELETE FROM `comment` WHERE `idcomment` ="+idComment;  
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
    public List<CommentEvenement> getAllComment() throws SQLDataException {
         try {
             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment";
             Statement st=cnx.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(es.get_Evenment(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                 return list;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }
    }

  



    public User findUserById(int id) {
          try {
     
        
             String req="SELECT * FROM `user` WHERE (id_user =" + String.valueOf(id) + ")";
             Statement st=cnx.createStatement();
        
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 
                 User us = new User();
                 us.setId(rs.getInt(1));
                 us.setNom(rs.getString(2));        
                return us; 
               
                        
                         }
              System.err.println("hello ");
                 return null;
             
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       }
    
    

    public ObservableList<CommentEvenement> getAllCommentByEvent(Evenements e) throws SQLDataException {
     try {

             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment where idevenement = "+String.valueOf(e.getId());
             Statement st=cnx.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(es.get_Evenment(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                              ObservableList lc = FXCollections.observableArrayList(list);

                 return lc;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }    }

    public CommentEvenement finCommentById(int id) {
    try {
             CommentEvenement cmt=new CommentEvenement();
             int c=0;
             String req="select * from comment where idcomment="+id;
             Statement st=cnx.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 cmt.setIdComment(rs.getInt(1));
                 cmt.setIdEvt(es.get_Evenment(rs.getInt(2)));
                 cmt.setId(findUserById(rs.getInt(3)));
                 cmt.setComment(rs.getString(4));
               
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return cmt;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }    }
  public int propre_comment(int id_u,int id_c) throws SQLException
         {
             
              String query = "select * from comment where ( iduser = "+id_u +" and idcomment = "+id_c+" )";
                    Statement st  = cnx.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                  return 1;
                     
                     }
             
             return 0;
         }

    public Object findEvenementById(int idE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    }
    
    

