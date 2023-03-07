/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Evenements;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewEvenement extends ListCell<Evenements> {
    
    
     @Override
     public void updateItem(Evenements e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            EvenementItemController data = new EvenementItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
