/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ENTITE;

/**
 *
 * @author ychen
 */
public enum RoleUSER {
    ADMIN,MEDECIN,PATIENT,PERSONNEL;
    
    @Override
    public String toString() {
        return this.name(); 
    }
}
