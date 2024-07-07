/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import TDA.List;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Michelle
 */
public class Usuario implements Serializable{    
    private String nickname;
    private String password;    
    private List<Vehiculo> vehiculosFavoritos;
    private static final long serialVersionUID = 3422012L;
    
    public Usuario(String nickname, String password) {        
        this.nickname = nickname;
        this.password = password;        
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Vehiculo> getVehiculosFavoritos() {
        return vehiculosFavoritos;
    }

    public void setVehiculosFavoritos(List<Vehiculo> vehiculosFavoritos) {
        this.vehiculosFavoritos = vehiculosFavoritos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nickname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.nickname, other.nickname);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nickname=" + nickname + '}';
    }    
}
